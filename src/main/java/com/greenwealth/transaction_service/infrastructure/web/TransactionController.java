package com.greenwealth.transaction_service.infrastructure.web;

// On importe les classes depuis nos autres dossiers
import com.greenwealth.transaction_service.application.InvestmentService;
import com.greenwealth.transaction_service.domain.model.Transaction;
import com.greenwealth.transaction_service.domain.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository repository;

    @GetMapping
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    private final InvestmentService investementService; //injection from service
    @PostMapping
    public Transaction create(@RequestBody Transaction transaction){
        transaction.setTimestamp(LocalDateTime.now());
        // save transaction for have an ID
        Transaction savedTransaction = repository.save(transaction);

        // invistisment traitement related to this saved transaction
        investementService.processInvestment(savedTransaction);

        return savedTransaction;
    }
}