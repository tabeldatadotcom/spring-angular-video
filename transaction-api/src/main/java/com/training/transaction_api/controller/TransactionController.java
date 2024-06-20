package com.training.transaction_api.controller;

import com.training.transaction_api.entity.Transaction;
import com.training.transaction_api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api-trx/transaction/v1")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        List<Transaction> list = transactionRepository.findAll();
        log.info("Response Transaction : {}", list);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<?> create(
            @RequestBody Transaction data
    ){
        Transaction save = transactionRepository.save(data);
        log.info("Request Transaction data : {}", data);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findByName(
            @RequestParam String accountName
    ){
        List<Transaction> list = transactionRepository.findByFromAccountNameOrToAccountName(accountName, accountName);
        log.info("Response Transaction by Name : {}", list);
        return ResponseEntity.ok(list);
    }

}
