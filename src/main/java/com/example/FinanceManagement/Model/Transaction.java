package com.example.FinanceManagement.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String transactionType;
    private String category;
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();

    public Transaction() {}

    public Transaction(Long id, Long userId, String transactionType, String category, Double amount, Date transactionDate) {
        this.id = id;
        this.userId = userId;
        this.transactionType = transactionType;
        this.category = category;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
}
