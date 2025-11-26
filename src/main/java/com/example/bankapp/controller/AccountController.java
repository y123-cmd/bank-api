

package com.example.bankapp.controller;

import com.example.bankapp.pojo.AccountDto;
import com.example.bankapp.pojo.DepositDto;
import com.example.bankapp.pojo.TransactionDto;
import com.example.bankapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "Handles account creation, deposits, balance checks, and money transfers")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ------------------- CREATE ACCOUNT -------------------
    @Operation(summary = "Create a new account")
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto dto) {
        try {
            AccountDto created = accountService.createAccount(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ------------------- DEPOSIT -------------------
    @Operation(summary = "Deposit money", description = "Deposits money into an existing account")
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<?> deposit(
            @PathVariable Long accountId,
            @RequestBody DepositDto dto
    ) {
        try {
            String result = accountService.deposit(accountId, dto.getAmount());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ------------------- GET BALANCE -------------------
    @Operation(summary = "Check account balance", description = "Returns account balance object")
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<?> getBalance(@PathVariable Long accountId) {
        try {
            AccountDto balance = accountService.getBalance(accountId);
            return ResponseEntity.ok(balance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ------------------- VIEW BALANCE AS TEXT -------------------
    @Operation(summary = "View balance in plain text", description = "Returns balance in text format")
    @GetMapping("/{accountId}/view-balance")
    public ResponseEntity<?> viewBalance(@PathVariable Long accountId) {
        try {
            String result = accountService.viewBalance(accountId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ------------------- SEND MONEY -------------------
    @Operation(summary = "Send money", description = "Transfers money from one account to another")
    @PostMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransactionDto dto) {
        try {
            String result = accountService.sendMoney(dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}