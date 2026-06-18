package com.practice.mappers;

import com.practice.dto.CompanyDTO;
import com.practice.models.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);
}
