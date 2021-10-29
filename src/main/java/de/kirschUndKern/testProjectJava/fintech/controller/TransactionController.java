package de.kirschUndKern.testProjectJava.fintech.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.kirschUndKern.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.kirschUndKern.testProjectJava.fintech.modell.PageResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionRequest;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsForCustomerResponse;
import de.kirschUndKern.testProjectJava.fintech.modell.TransactionsFullResponse;
import de.kirschUndKern.testProjectJava.fintech.service.AccountService;
import de.kirschUndKern.testProjectJava.fintech.service.TransactionService;

@RestController
public class TransactionController {
  
  private TransactionService transactionService;
  private AccountService accountService;
  
  public TransactionController (
    TransactionService transactionService,
    AccountService accountService
  ){
    this.transactionService = transactionService;
    this.accountService = accountService;
  }

  @PostMapping("/transactions/{id}")
  public TransactionsFullResponse newTransactions(
    @PathVariable (name = "id") String customerId,
    @RequestBody TransactionRequest transactionRequest
  )throws BankAccountNotFoundException{
    TransactionsFullResponse newTransaction = transactionService.processNewTransaction(customerId, transactionRequest);
    accountService.processNewTransaction(newTransaction);
    return newTransaction;
  };

  @GetMapping("/transactions")
  public List<TransactionsFullResponse> findAllTransactions(
    @RequestParam(name = "date") String date 
  ){
    return transactionService.getAllTransactionsBy(date);
  };


  @GetMapping("/transactions/{id}")
  public PageResponse<TransactionsForCustomerResponse> getAllTransactions(
    @RequestParam(name = "pageno", defaultValue = "1") Integer pageNo,
    @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize,
    @RequestParam(name = "sortby", defaultValue = "sender_firstname") String sortBy,
    @PathVariable(name = "id") String customerId
  ) throws Exception{
    return transactionService.getPagedTransactions(customerId, pageNo, pageSize, sortBy);
  }
  
}
