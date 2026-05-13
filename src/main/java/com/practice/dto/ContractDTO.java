package com.practice.dto;

import java.time.LocalDateTime;

public record ContractDTO (

    String title,
    double value,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Long companyId
){}
