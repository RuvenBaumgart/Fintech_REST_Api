package de.kirschUndKern.testProjectJava.fintech.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.kirschUndKern.testProjectJava.fintech.entities.CreditsEntity;
import de.kirschUndKern.testProjectJava.fintech.entities.CustomerEntity;
import de.kirschUndKern.testProjectJava.fintech.exceptions.CustomerNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.CreditRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.CreditResponse;
import de.kirschUndKern.testProjectJava.fintech.repositories.CreditsRepository;
import de.kirschUndKern.testProjectJava.fintech.repositories.CustomerRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CreditService {
  private final CustomerRepository customerRepository;
  private final CreditsRepository creditRepository;

  public CreditService(
    CustomerRepository customerRepository,
    CreditsRepository creditRepository
  ){
    this.customerRepository = customerRepository;
    this.creditRepository = creditRepository;
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

}
