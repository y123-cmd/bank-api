package com.example.bankapp.service;

import com.example.bankapp.model.BankAccount;

import java.util.Collection;

public interface AccountService {

    BankAccount createAccount(BankAccount account);

    BankAccount getAccount(Long id);

    Collection<BankAccount> getAllAccounts();

    BankAccount deposit(Long id, double amount);

    BankAccount withdraw(Long id, double amount);

    BankAccount getAccountByName(String name);
}
