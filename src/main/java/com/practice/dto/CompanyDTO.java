package com.practice.dto;

import jakarta.validation.constraints.NotNull;

public record CompanyDTO (

    @NotNull(message = "Company must have a name")
    String name,
    @NotNull(message = "Company must have an address")
    String address
){}
