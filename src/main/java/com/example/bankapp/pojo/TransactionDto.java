package com.example.bankapp.pojo;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String type; // e.g., "DEPOSIT", "WITHDRAW", "TRANSFER"
    private LocalDateTime timestamp;
    private String description;

    public TransactionDto() {
        this.timestamp = LocalDateTime.now();
    }

    public TransactionDto(Long fromAccountId, Long toAccountId, Double amount, String type, String description) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
