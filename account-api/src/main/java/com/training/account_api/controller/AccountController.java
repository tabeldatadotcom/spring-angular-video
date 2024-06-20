package com.training.account_api.controller;

import com.training.account_api.entity.Account;
import com.training.account_api.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/account/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        List<Account> list = accountRepository.findAll();
        log.info("Response Account : {}", list);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody Account data
    ){
        System.out.println("id : " + data.getId() + " name : " + data.getName() + " address : " + data.getAddress() + " city : " + data.getCity());
        Account account = accountRepository.save(data);
        log.info("Request Account data : {}", data);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }



}
