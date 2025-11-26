package com.example.bankapp.pojo;

public class DepositDto {
    private Double amount;

    public DepositDto() {}

    public DepositDto(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}