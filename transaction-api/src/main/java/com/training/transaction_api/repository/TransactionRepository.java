package com.training.transaction_api.repository;

import com.training.transaction_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountNameOrToAccountName(String fromAccountName, String toAccountName);
}
