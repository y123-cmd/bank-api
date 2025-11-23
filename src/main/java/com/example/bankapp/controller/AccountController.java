
package com.example.bankapp.controller;

import com.example.bankapp.pojo.AccountDto;
import com.example.bankapp.pojo.TransactionDto;
import com.example.bankapp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "Handles deposits, balance checks and money transfers")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Deposit money", description = "Deposits money into an account")
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(
            @PathVariable Long accountId,
            @RequestBody TransactionDto dto
    ) {
        return ResponseEntity.ok(accountService.deposit(accountId, dto.getAmount()));
    }

    @Operation(summary = "Check account balance", description = "Returns account balance object")
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<AccountDto> getBalance(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getBalance(accountId));
    }

    @Operation(summary = "View balance in plain text", description = "Returns balance in text format")
    @GetMapping("/{accountId}/view-balance")
    public ResponseEntity<String> viewBalance(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.viewBalance(accountId));
    }

    @Operation(summary = "Send money", description = "Transfers money from one account to another")
    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(@RequestBody TransactionDto dto) {
        return ResponseEntity.ok(accountService.sendMoney(dto));
    }
}