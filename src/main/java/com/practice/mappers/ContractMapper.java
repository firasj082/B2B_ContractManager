package com.practice.mappers;

import com.practice.dto.ContractDTO;
import com.practice.models.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "company", ignore = true)
    Contract toEntity(ContractDTO contractDTO);

    @Mapping(source = "company.id", target = "companyId")
    ContractDTO toDTO(Contract contract);
}
