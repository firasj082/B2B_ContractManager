package com.practice.services;

import com.practice.dto.ContractDTO;
import com.practice.exceptions.BusinessLogicException;
import com.practice.mappers.ContractMapper;
import com.practice.models.Company;
import com.practice.models.Contract;
import com.practice.repositories.CompanyRepository;
import com.practice.repositories.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepo;
    private final CompanyRepository companyRepo;
    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);

    public ContractService(ContractRepository contractRepo, CompanyRepository companyRepo) {
        this.contractRepo = contractRepo;
        this.companyRepo = companyRepo;
    }

    public ContractDTO saveContract(ContractDTO contractDTO) {

        if(contractDTO.getEndDate().isBefore(contractDTO.getStartDate())) {
            logger.error("Invalid Contract Dates Start Date: {}, End Date: {}", contractDTO.getStartDate(), contractDTO.getEndDate());
            throw new BusinessLogicException("End date cant be before start date");
        }
        Company company = companyRepo.findById(contractDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        Contract contract = ContractMapper.toEntity(contractDTO, company);
        Contract saved = contractRepo.save(contract);
        logger.info("Created a Contract With the ID: {} and Title: {}",saved.getId() ,saved.getTitle());
        return ContractMapper.toDTO(saved);
    }

    public void deleteContract(Long id) {
        contractRepo.deleteById(id);
        logger.info("Contract With the ID:{} Was Deleted Successfully", id);
    }

    public List<ContractDTO> findAll() {
        return contractRepo.findAll().stream()
                .map(ContractMapper::toDTO)
                .toList();
    }

    public List<ContractDTO> findByValueGreaterThan(double amount) {
        return contractRepo.findByValueGreaterThan(amount).stream()
                .map(ContractMapper::toDTO)
                .toList();
    }

    public List<ContractDTO> findContractsExpiringIn() {
        return contractRepo.findContractsExpiringIn(LocalDate.now().plusDays(30)).stream()
                .map(ContractMapper::toDTO)
                .toList();
    }

    public List<ContractDTO> findContractsExpiringIn(int days) {
        return contractRepo.findContractsExpiringIn(LocalDate.now().plusDays(days)).stream()
                .map(ContractMapper::toDTO)
                .toList();
    }
}
