package com.example.bankapp.pojo;

public class AccountDto {
    private Long accountId;
    private Double balance;

    public AccountDto() {}

    public AccountDto(Long accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // Correct getter
    public Long getAccountId() {
        return accountId;
    }

    // Correct setter
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
