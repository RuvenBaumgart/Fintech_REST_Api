package de.demo.testProjectJava.fintech.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.demo.testProjectJava.fintech.dto.request.TransactionRequest;
import de.demo.testProjectJava.fintech.dto.response.PageResponse;
import de.demo.testProjectJava.fintech.dto.response.TransactionsForCustomerResponse;
import de.demo.testProjectJava.fintech.dto.response.TransactionsFullResponse;
import de.demo.testProjectJava.fintech.exceptions.BankAccountNotFoundException;
import de.demo.testProjectJava.fintech.service.TransactionService;

@RestController
public class TransactionController {
  
  private TransactionService transactionService;
  
  public TransactionController (
    TransactionService transactionService

  ){
    this.transactionService = transactionService;

  }

  @PostMapping("/transactions")
  public TransactionsFullResponse newTransactions(
    @RequestBody TransactionRequest transactionRequest
  )throws BankAccountNotFoundException{
    TransactionsFullResponse newTransactions = transactionService.processNewTransaction(transactionRequest);
    return newTransactions;
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
