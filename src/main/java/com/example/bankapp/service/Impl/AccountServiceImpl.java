

package com.example.bankapp.service.Impl;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.model.repository.AccountRepository;
import com.example.bankapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    // Constructor injection: Spring will inject AccountRepository automatically
    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }
    public BankAccount getAccountByName(String name) {
        return repo.findByHolderName(name);
    }

    public BankAccount createAccount(BankAccount account) {
        BankAccount acc = new BankAccount(
                account.getId(),
                account.getHolderName(),
                account.getBalance()
        );
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
