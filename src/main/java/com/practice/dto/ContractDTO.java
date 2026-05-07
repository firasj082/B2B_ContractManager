package com.practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ContractDTO {

    private String title;
    private double value;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long companyId;
}
