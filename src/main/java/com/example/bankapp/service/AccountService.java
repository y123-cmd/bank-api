
package com.example.bankapp.service;

import com.example.bankapp.pojo.AccountDto;
import com.example.bankapp.pojo.TransactionDto;

public interface AccountService {

    String deposit(Long accountId, Double amount);

    AccountDto getBalance(Long accountId);

    AccountDto createAccount(AccountDto dto);

    String viewBalance(Long accountId);

    String sendMoney(TransactionDto dto);
}
