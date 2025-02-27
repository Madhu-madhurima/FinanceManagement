package com.example.FinanceManagement.Repository;

import com.example.FinanceManagement.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Fetch transactions by userId
    List<Transaction> findByUserId(Long userId);

    @Query(value = "SELECT * FROM transaction WHERE user_id = ?1", nativeQuery = true)
    List<Transaction> getTransactionsByUserId(Long userId);
}
