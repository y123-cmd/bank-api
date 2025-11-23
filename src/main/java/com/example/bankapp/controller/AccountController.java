
package com.example.bankapp.controller;

import com.example.bankapp.pojo.AccountDto;
import com.example.bankapp.pojo.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "Handles deposits, balance checks and money transfers")
public class AccountController {

    // Deposit money
    @Operation(summary = "Deposit money", description = "Deposits money into an account")
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(
            @PathVariable Long accountId,
            @RequestBody TransactionDto transactionDto
    ) {
        return ResponseEntity.ok("Deposited " + transactionDto.getAmount() +
                " to account " + accountId);
    }

    // Check balance
    @Operation(summary = "Check account balance", description = "Returns the account balance")
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<AccountDto> getBalance(@PathVariable Long accountId) {
        // Dummy logic
        AccountDto account = new AccountDto(accountId, 5000.00);
        return ResponseEntity.ok(account);
    }

    // View balance (same endpoint but returning plain message)
    @Operation(summary = "View balance (string version)", description = "Returns balance in a text format")
    @GetMapping("/{accountId}/view-balance")
    public ResponseEntity<String> viewBalance(@PathVariable Long accountId) {
        return ResponseEntity.ok("The balance for account " + accountId + " is 5000.00");
    }

    // Send money
    @Operation(summary = "Send money", description = "Transfers money from one account to another")
    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(@RequestBody TransactionDto dto) {
        return ResponseEntity.ok(
                "Sent " + dto.getAmount() +
                        " from account " + dto.getFromAccountId() +
                        " to account " + dto.getToAccountId()
        );
    }
}