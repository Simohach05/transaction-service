package com.greenwealth.transaction_service.application.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}