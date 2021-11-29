package de.demo.testProjectJava.fintech.service;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.demo.testProjectJava.fintech.dto.request.CreditRequest;
import de.demo.testProjectJava.fintech.dto.request.CreditUpdateRequest;
import de.demo.testProjectJava.fintech.dto.request.TransactionRequest;
import de.demo.testProjectJava.fintech.dto.response.CreditResponse;
import de.demo.testProjectJava.fintech.entities.AccountEntity;
import de.demo.testProjectJava.fintech.entities.CreditsEntity;
import de.demo.testProjectJava.fintech.entities.CustomerEntity;
import de.demo.testProjectJava.fintech.entities.TransactionsEntity;
import de.demo.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.demo.testProjectJava.fintech.exceptions.CreditNotFoundException;
import de.demo.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.demo.testProjectJava.fintech.repositories.AccountRepository;
import de.demo.testProjectJava.fintech.repositories.CreditsRepository;
import de.demo.testProjectJava.fintech.repositories.CustomerRepository;

import java.util.List;
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

  public CreditResponse createUpdateCredit(String creditId, CreditUpdateRequest creditUpdateRequest) throws Exception {
    Optional<CreditsEntity> credit = creditRepository.findById(creditId);
    Optional<AccountEntity> sourceAccount = accountRepository.findById(creditUpdateRequest.getSourceAccountId());
        if(credit.isPresent()){
          if(sourceAccount.isPresent()){
              
            CreditResponse updatedCredit = createAndSaveUpdatedCredit(credit.get(), creditUpdateRequest);

            // If i would have a transaction interfaced than i could only give the transaction to the mehtod. THis would mean no need for mapping.
            TransactionsEntity savedTransaction = transactionService.saveNewTransaction(
              createTransactionRequestFrom(creditUpdateRequest)
            );

            accountService.updateAccounts(savedTransaction);

            return updatedCredit;
          } else {
            throw new BankAccountNotFoundException();
          }
    } else {
      throw new CreditNotFoundException("Credit with the id: " + creditId + " not found", HttpStatus.BAD_REQUEST);
    }
  }

  private TransactionRequest createTransactionRequestFrom(CreditUpdateRequest creditUpdateRequest) {
    return new TransactionRequest(
      creditUpdateRequest.getAmountInCent(),
      creditUpdateRequest.getSourceAccountId(),
      null,
      "Credit payoff"
    );
  }

  private CreditResponse createAndSaveUpdatedCredit(CreditsEntity credit,
      CreditUpdateRequest creditUpdateRequest) {
    CreditsEntity updatedCredit = new CreditsEntity(
      credit.getId(),
      credit.getCustomerId(),
      credit.getDataOfCreation(),
      credit.getOriginalTermInMonths(),
      credit.getRemainingTermInMonths(),
      credit.getOrignialCreditAmountInCents(),
      credit.getCurrentCreditAmountInCents() - creditUpdateRequest.getAmountInCent()
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
