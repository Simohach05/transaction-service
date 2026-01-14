package com.greenwealth.transaction_service.infrastructure.web;

// On importe les classes depuis nos autres dossiers
import com.greenwealth.transaction_service.application.InvestmentService;
import com.greenwealth.transaction_service.application.dto.TransactionRequest;
import com.greenwealth.transaction_service.domain.model.Transaction;
import com.greenwealth.transaction_service.domain.repository.TransactionRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Transaction create(@Valid @RequestBody TransactionRequest request){
        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        // save transaction for have an ID
        Transaction savedTransaction = repository.save(transaction);

        // invistisment traitement related to this saved transaction
        investementService.processInvestment(savedTransaction);

        return savedTransaction;
    }
}