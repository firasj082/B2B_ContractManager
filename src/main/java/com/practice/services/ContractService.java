package com.practice.services;

import com.practice.exceptions.BusinessLogicException;
import com.practice.models.Contract;
import com.practice.repositories.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepo;

    public ContractService(ContractRepository contractRepo) {
        this.contractRepo = contractRepo;
    }

    public Contract saveContract(Contract contract) {

        if(contract.getEndDate().isBefore(contract.getStartDate())) {
            throw new BusinessLogicException("End date cant be before start date");
        }
        return contractRepo.save(contract);
    }

    public void deleteContract(Long id) {
        contractRepo.deleteById(id);
    }

    public List<Contract> findByValueGreaterThan(double amount) {
        return contractRepo.findByValueGreaterThan(amount);
    }

    public List<Contract> findContractsExpiringIn() {
        return contractRepo.findContractsExpiringIn(LocalDate.now().plusDays(30));
    }

    public List<Contract> findContractsExpiringIn(int days) {
        return contractRepo.findContractsExpiringIn(LocalDate.now().plusDays(days));
    }
}
