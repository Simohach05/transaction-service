package com.greenwealth.transaction_service.application.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequest {
    @NotBlank(message = "the description it's imposed")
    private String description;
    @NotNull (message = "Amount it's imposed")
    @DecimalMin(value = "0.01", message = "Amount must be postive")
    private BigDecimal amount;

}
