package com.practice.services;

import com.practice.dto.ContractDTO;
import java.util.List;

public interface ContractService {

    ContractDTO saveContract(ContractDTO contractDTO);
    void deleteContract(Long id);
    List<ContractDTO> findAll();
    List<ContractDTO> findByValueGreaterThan(double amount);
    List<ContractDTO> findContractsExpiringIn();
    List<ContractDTO> findContractsExpiringIn(int days);
}
