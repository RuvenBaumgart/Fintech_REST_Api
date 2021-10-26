package de.kirschUndKern.testProjectJava.fintech.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;

import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.service.TransactionService;

@RestController
public class TransactionController {
  
  private TransactionService transactionService;
  
  public TransactionController (
    TransactionService transactionService
  ){
    this.transactionService = transactionService;
  }

  @PostMapping("/transactions/{id}")
  public List<TransactionsFullResponse> createNewTransactions(
    @PathVariable (name = "id") String customerId,
    @RequestBody TransactionRequest transactionRequest
  )throws BankAccountNotFoundException{
    return transactionService.createNewTransactionForCustomer(customerId, transactionRequest);
  }
}
