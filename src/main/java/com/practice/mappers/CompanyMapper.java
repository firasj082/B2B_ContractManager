package com.practice.mappers;

import com.practice.dto.CompanyDTO;
import com.practice.models.Company;

public class CompanyMapper {

    public static Company toEntity(CompanyDTO companyDTO) {

        Company company = new Company();
        company.setAddress(companyDTO.getAddress());
        company.setName(companyDTO.getName());
        return company;
    }

    public static CompanyDTO toDTO(Company company) {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setAddress(company.getAddress());
        return companyDTO;
    }
}
