package com.example.bankapp.service;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    private final AccountRepository repo;

    // Constructor injection: Spring will inject AccountRepository automatically
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public BankAccount createAccount(Long id, String holderName) {
        if (repo.existsById(id)) {
            throw new IllegalArgumentException("Account with id " + id + " already exists");
        }
        BankAccount acc = new BankAccount(id, holderName, 0.0);
        return repo.save(acc);
    }

    public BankAccount getAccount(Long id) {
        return repo.findById(id);
    }

    public Collection<BankAccount> getAllAccounts() {
        return repo.findAll();
    }

    public BankAccount deposit(Long id, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        BankAccount acc = repo.findById(id);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        acc.setBalance(acc.getBalance() + amount);
        return repo.save(acc);
    }

    public BankAccount withdraw(Long id, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        BankAccount acc = repo.findById(id);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        if (acc.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
        acc.setBalance(acc.getBalance() - amount);
        return repo.save(acc);
    }
}
