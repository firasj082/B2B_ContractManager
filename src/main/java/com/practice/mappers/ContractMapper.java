package com.practice.mappers;

import com.practice.dto.ContractDTO;
import com.practice.models.Company;
import com.practice.models.Contract;

public class ContractMapper {
    public static Contract toEntity(ContractDTO contractDTO, Company company) {

        Contract contract = new Contract();
        contract.setValue(contractDTO.getValue());
        contract.setTitle(contractDTO.getTitle());
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setCompany(company);
        return contract;
    }

    public static ContractDTO toDTO(Contract contract) {

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setValue(contract.getValue());
        contractDTO.setTitle(contract.getTitle());
        contractDTO.setStartDate(contract.getStartDate());
        contractDTO.setEndDate(contract.getEndDate());
        contractDTO.setCompanyId(contract.getCompany().getId());
        return contractDTO;
    }
}
