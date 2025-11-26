
package com.example.bankapp.service.Impl;


import com.example.bankapp.pojo.AccountDto;
import com.example.bankapp.pojo.TransactionDto;
import com.example.bankapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private final Map<Long, AccountDto> accounts = new HashMap<>();

    /**
     * Helper: ensures the account exists, otherwise throws exception
     */
    private AccountDto getAccountOrThrow(Long accountId) {
        AccountDto account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account " + accountId + " does not exist");
        }
        return account;
    }

    // ------------------- CREATE ACCOUNT -------------------
    @Override
    public AccountDto createAccount(AccountDto dto) {
        Long accountId = dto.getAccountId();

        if (accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account " + accountId + " already exists");
        }

        AccountDto newAccount = new AccountDto(accountId, 0.0); // start balance at 0
        accounts.put(accountId, newAccount);
        return newAccount;
    }

    // ------------------- DEPOSIT -------------------
    @Override
    public String deposit(Long accountId, Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
        AccountDto account = getAccountOrThrow(accountId);
        account.setBalance(account.getBalance() + amount);
        return "Deposited " + amount + " to account " + accountId;
    }

    // ------------------- GET BALANCE -------------------
    @Override
    public AccountDto getBalance(Long accountId) {
        return getAccountOrThrow(accountId);
    }

    // ------------------- VIEW BALANCE AS TEXT -------------------
    @Override
    public String viewBalance(Long accountId) {
        AccountDto account = getAccountOrThrow(accountId);
        return "Balance for account " + accountId + " is " + account.getBalance();
    }

    // ------------------- SEND MONEY -------------------
    @Override
    public String sendMoney(TransactionDto dto) {
        if (dto.getAmount() == null || dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than 0");
        }

        AccountDto fromAccount = getAccountOrThrow(dto.getFromAccountId());
        AccountDto toAccount   = getAccountOrThrow(dto.getToAccountId());

        if (fromAccount.getBalance() < dto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in account " + dto.getFromAccountId());
        }

        fromAccount.setBalance(fromAccount.getBalance() - dto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + dto.getAmount());

        return "Sent " + dto.getAmount() +
                " from account " + dto.getFromAccountId() +
                " to account " + dto.getToAccountId();
    }
}