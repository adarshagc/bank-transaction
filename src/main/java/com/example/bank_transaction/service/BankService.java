package com.example.bank_transaction.service;

import com.example.bank_transaction.model.Account;
import com.example.bank_transaction.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    @Autowired
    private AccountRepository repository;

    public Account createAccount(Account account) {
        return repository.save(account);
    }

    public Account getAccount(int id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void transferMoney(int from, int to, double amount) {

        Account sender = repository.findById(from).orElseThrow();
        Account receiver = repository.findById(to).orElseThrow();

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        repository.save(sender);

        // failure test
        if (amount > 10000) {
            throw new RuntimeException("Transfer limit exceeded");
        }

        receiver.setBalance(receiver.getBalance() + amount);
        repository.save(receiver);
    }
}