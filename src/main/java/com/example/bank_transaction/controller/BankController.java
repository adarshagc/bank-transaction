package com.example.bank_transaction.controller;

import com.example.bank_transaction.model.Account;
import com.example.bank_transaction.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService service;

    @PostMapping("/create")
    public Account create(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable int id) {
        return service.getAccount(id);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam int from,
                           @RequestParam int to,
                           @RequestParam double amount) {

        service.transferMoney(from, to, amount);
        return "Money Transferred Successfully";
    }
}