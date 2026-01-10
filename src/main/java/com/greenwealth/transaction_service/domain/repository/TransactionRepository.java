package com.greenwealth.transaction_service.domain.repository;


import com.greenwealth.transaction_service.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
