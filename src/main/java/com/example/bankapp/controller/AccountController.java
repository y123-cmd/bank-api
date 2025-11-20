package com.example.bankapp.controller;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.Impl.AccountServiceImpl;
import com.example.bankapp.service.AuditService;
import com.example.bankapp.service.MetricsService; // <-- Import MetricsService
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;
    private final AuditService auditService;
    private final MetricsService metricsService; // <-- Inject MetricsService

    // Inject AccountServiceImpl, AuditService, and MetricsService
    public AccountController(AccountServiceImpl service, AuditService auditService, MetricsService metricsService) {
        this.service = service;
        this.auditService = auditService;
        this.metricsService = metricsService;
    }

    // Create account
    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody BankAccount account) {
        BankAccount created = service.createAccount(account);
        auditService.log("Created account with ID: " + created.getId());
        metricsService.incrementAccountCreated(); // Increment account creation metric
        return ResponseEntity.ok(created);
    }

    // Deposit
    @PostMapping("/{id}/deposit")
    public ResponseEntity<BankAccount> deposit(@PathVariable Long id,
                                               @RequestParam double amount) {
        try {
            BankAccount acc = service.deposit(id, amount);
            auditService.log("Deposited " + amount + " to account ID: " + id);
            metricsService.incrementDeposit(); // Increment deposit metric
            return ResponseEntity.ok(acc);
        } catch (IllegalArgumentException e) {
            auditService.log("Failed deposit attempt to account ID: " + id);
            return ResponseEntity.badRequest().build();
        }
    }

    // Withdraw
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<BankAccount> withdraw(@PathVariable Long id,
                                                @RequestParam double amount) {
        try {
            BankAccount acc = service.withdraw(id, amount);
            auditService.log("Withdrew " + amount + " from account ID: " + id);
            metricsService.incrementWithdrawal(); // Increment withdrawal metric
            return ResponseEntity.ok(acc);
        } catch (IllegalArgumentException e) {
            auditService.log("Failed withdrawal attempt from account ID: " + id);
            return ResponseEntity.badRequest().build();
        }
    }

    // Get single account
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> get(@PathVariable Long id) {
        BankAccount acc = service.getAccount(id);
        if (acc == null) return ResponseEntity.notFound().build();
        auditService.log("Fetched account with ID: " + id);
        return ResponseEntity.ok(acc);
    }

    // Get all accounts
    @GetMapping
    public Collection<BankAccount> getAll() {
        Collection<BankAccount> accounts = service.getAllAccounts();
        auditService.log("Fetched all accounts, count: " + accounts.size());
        return accounts;
    }

    // Get account by name
    @GetMapping("/by-name/{name}")
    public ResponseEntity<BankAccount> getByName(@PathVariable String name) {
        BankAccount acc = service.getAccountByName(name);
        if (acc == null) return ResponseEntity.notFound().build();
        auditService.log("Fetched account by name: " + name);
        return ResponseEntity.ok(acc);
    }
}
