package de.kirschUndKern.testProjectJava.fintech.service;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

import de.kirschUndKern.testProjectJava.fintech.dto.request.CreditRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.request.CreditUpdateRequest;
import de.kirschUndKern.testProjectJava.fintech.dto.response.CreditResponse;
import de.kirschUndKern.testProjectJava.fintech.entities.AccountEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.TransactionsEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CreditNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.repositories.AccountRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CreditsRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CreditService {
  private final CustomerRepository customerRepository;
  private final CreditsRepository creditRepository;
  private final AccountRepository accountRepository;
  private final TransactionService transactionService;
  private final AccountService accountService;

  public CreditService(
    CustomerRepository customerRepository,
    CreditsRepository creditRepository,
    AccountRepository accountRepository,
    TransactionService transactionService,
    AccountService accountService
  ){
    this.customerRepository = customerRepository;
    this.creditRepository = creditRepository;
    this.accountRepository = accountRepository;
    this.transactionService = transactionService;
    this.accountService = accountService;
  }

  public CreditResponse createCreditForCustomer(String customerId, CreditRequest newCreditRequest) throws CustomerNotFoundException{
    Optional<CustomerEntity> customer = customerRepository.findById(customerId);
    if(customer.isPresent()){
      LocalDate creationDay = LocalDate.now();
      CreditsEntity newCredit = new CreditsEntity(
        UUID.randomUUID().toString(),
        customer.get().getId(),
        creationDay,
        newCreditRequest.getRuntimeInMonths(),
        calculateRemainingRuntime(creationDay, LocalDate.now(), newCreditRequest.getRuntimeInMonths()),
        newCreditRequest.getCreditAmountInCents(),
        newCreditRequest.getCreditAmountInCents()
      );
      return new CreditResponse(creditRepository.save(newCredit));
    } else {
      throw new CustomerNotFoundException("Customer with id: " + customerId +
       " not found", HttpStatus.BAD_REQUEST);
    }
  }


  public Long calculateRemainingRuntime(LocalDate creationDay, LocalDate currentDay, Long orignialRuntime){
    Long pastMonths =  ChronoUnit.MONTHS.between(creationDay, currentDay);
    return orignialRuntime - pastMonths;
  }

  public List<CreditResponse> getAllCreditsById(String customerId) {
    List<CreditsEntity> credits = creditRepository.findAllByCustomerId(customerId);
    List<CreditResponse> creditsResponse = credits.stream()
    .map(credit -> new CreditResponse(credit))
    .collect(Collectors.toList());
    return creditsResponse;
  }

  public CreditResponse createUpdateCredit(String id, CreditUpdateRequest creditUpdateRequest) throws Exception {
    Optional<CreditsEntity> credit = creditRepository.findById(id);
    Optional<AccountEntity> account = accountRepository.findById(creditUpdateRequest.getSourceAccountId());
        if(credit.isPresent()){
          if(account.isPresent()){
              
            CreditResponse updatedCredit = createAndSaveUpdatedCredit(credit.get(), creditUpdateRequest);
              
            TransactionsEntity newTransaction = transactionService.saveNewTransaction(
              creditUpdateRequest.getSourceAccountId(), 
              id, 
              creditUpdateRequest.getAmountInCent(),
              "Credit Payoff"
            );

            accountService.updateAccountBalance(
              creditUpdateRequest.getSourceAccountId(), 
              creditUpdateRequest.getAmountInCent() * -1, 
              newTransaction.getId()
            );

            return updatedCredit;
          } else {
            throw new BankAccountNotFoundException("Account with the id: " 
            + creditUpdateRequest.getSourceAccountId() 
            + "to payoff the credit not found", HttpStatus.BAD_REQUEST);
          }
    } else {
      throw new CreditNotFoundException("Credit with the id: " + id + " not found", HttpStatus.BAD_REQUEST);
    }
  }

  private CreditResponse createAndSaveUpdatedCredit(CreditsEntity credit,
      CreditUpdateRequest creditUpdateRequest) {
    CreditsEntity updatedCredit = new CreditsEntity(
      credit.getId(),
      credit.getCustomerId(),
      credit.getDataOfCreation(),
      credit.getOriginalTermInMonths(),
      credit.getRemainingTermInMonths(),
      credit.getOriginalCreditAmount(),
      credit.getCurrentCreditAmount() - creditUpdateRequest.getAmountInCent()
    );
    return new CreditResponse(
      creditRepository.save(updatedCredit)
      );
  }

  public List<CreditResponse> getAllCredits() {
    List<CreditsEntity> credits = creditRepository.findAllThatExceededOriginalTerm();
    return credits.stream().map(creditEntity -> new CreditResponse(creditEntity)).collect(Collectors.toList());
  }

}
