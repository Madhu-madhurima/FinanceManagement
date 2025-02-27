package com.example.FinanceManagement.Controller;

import com.example.FinanceManagement.Model.Transaction;
import com.example.FinanceManagement.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction added successfully");
    }

    @GetMapping("/viewAll/{userId}")
    public ResponseEntity<List<Transaction>> viewAllTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/viewExpenseSummary")
    public ResponseEntity<List<Map<String, String>>> viewExpenseSummary(@RequestBody Map<String, String> request) {
        Long userId = Long.parseLong(request.get("userId"));
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        List<Map<String, String>> expenseSummary = transactions.stream()
                .filter(t -> "Expense".equalsIgnoreCase(t.getTransactionType()))
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, String> summary = new HashMap<>();
                    summary.put("Category", entry.getKey());
                    summary.put("Total Expense", String.valueOf(entry.getValue()));
                    return summary;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(expenseSummary);
    }
}
