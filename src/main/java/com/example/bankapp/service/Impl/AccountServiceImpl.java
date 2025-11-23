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

    private AccountDto getOrCreateAccount(Long accountId) {
        return accounts.computeIfAbsent(accountId, id -> new AccountDto(id, 0.0));
    }

    @Override
    public String deposit(Long accountId, Double amount) {
        AccountDto account = getOrCreateAccount(accountId);
        account.setBalance(account.getBalance() + amount);
        return "Deposited " + amount + " to account " + accountId;
    }

    @Override
    public AccountDto getBalance(Long accountId) {
        return getOrCreateAccount(accountId);
    }

    @Override
    public String viewBalance(Long accountId) {
        return "Balance for account " + accountId +
                " is " + getOrCreateAccount(accountId).getBalance();
    }

    @Override
    public String sendMoney(TransactionDto dto) {
        AccountDto fromAccount = getOrCreateAccount(dto.getFromAccountId());
        AccountDto toAccount   = getOrCreateAccount(dto.getToAccountId());

        if (fromAccount.getBalance() < dto.getAmount()) {
            return "Insufficient balance in account " + dto.getFromAccountId();
        }

        fromAccount.setBalance(fromAccount.getBalance() - dto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + dto.getAmount());

        return "Sent " + dto.getAmount() +
                " from account " + dto.getFromAccountId() +
                " to account " + dto.getToAccountId();
    }
}