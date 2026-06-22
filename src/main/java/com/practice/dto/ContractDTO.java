package com.practice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ContractDTO (

    @NotNull(message = "Contract title is required")
    String title,
    @Positive
    double value,
    @NotNull(message = "Start date is required")
    LocalDateTime startDate,
    @NotNull(message = "End date is required")
    LocalDateTime endDate,
    @NotNull(message = "CompanyID is required")
    Long companyId
){}
