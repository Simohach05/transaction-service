package com.greenwealth.transaction_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal; //never we use 'double' in money
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount; //precision bank
    private LocalDateTime timestamp;
}

