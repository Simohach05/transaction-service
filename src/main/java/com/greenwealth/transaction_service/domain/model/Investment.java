package com.greenwealth.transaction_service.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
