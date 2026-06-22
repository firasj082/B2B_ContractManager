package com.practice.mappers;

import com.practice.dto.CompanyDTO;
import com.practice.models.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);

    List<CompanyDTO> toDTOList(List<Company> companies);
    List<Company> toEntityList(List<CompanyDTO> companyDTOs);
}
