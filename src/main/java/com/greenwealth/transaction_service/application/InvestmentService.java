package com.greenwealth.transaction_service.application;

import com.greenwealth.transaction_service.domain.model.Investment;
import com.greenwealth.transaction_service.domain.model.Transaction;
import com.greenwealth.transaction_service.domain.repository.InvestmentRepository;
import com.greenwealth.transaction_service.infrastructure.messaging.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private final InvestmentRepository repository;
    private final KafkaProducer kafkaProducer;
    public void processInvestment(Transaction transaction) {
        // calculate RoundUP
        BigDecimal amount = transaction.getAmount();
        BigDecimal nextWholeNumber = amount.setScale(0, RoundingMode.UP);
        BigDecimal roundUp = nextWholeNumber.subtract(amount);

        // if the roundup > 0 then save it

        if(roundUp.compareTo(BigDecimal.ZERO) > 0) {
            Investment investment = new Investment();
            investment.setAmount(roundUp);
            investment.setTransaction(transaction);

            repository.save(investment); // we win money

            //kafka action
            String eventMessage = "New Investment " + roundUp + "MAD for Client ID"+ roundUp + transaction.getId();
            kafkaProducer.sendInvestmentEvent(eventMessage);;
        }
    }

    }

