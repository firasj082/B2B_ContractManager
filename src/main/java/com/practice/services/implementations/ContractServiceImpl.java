package com.practice.services.implementations;

import com.practice.dto.ContractDTO;
import com.practice.exceptions.BusinessLogicException;
import com.practice.mappers.ContractMapper;
import com.practice.models.Company;
import com.practice.models.Contract;
import com.practice.repositories.CompanyRepository;
import com.practice.repositories.ContractRepository;
import com.practice.services.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Primary
public class ContractServiceImpl implements ContractService {


    private final ContractRepository contractRepo;
    private final CompanyRepository companyRepo;
    private final ContractMapper contractMapper;

    @Override
    public ContractDTO saveContract(ContractDTO contractDTO) {

        if(contractDTO.endDate().isBefore(contractDTO.startDate())) {
            log.error("Invalid Contract Dates Start Date: {}, End Date: {}", contractDTO.startDate(), contractDTO.endDate());
            throw new BusinessLogicException("End date cant be before start date");
        }
        Company company = companyRepo.findById(contractDTO.companyId())
                .orElseThrow(() -> new BusinessLogicException("Company not found"));
        Contract contract = contractMapper.toEntity(contractDTO);
        contract.setCompany(company);
        Contract saved = contractRepo.save(contract);
        log.info("Created a Contract With the ID: {} and Title: {}",saved.getId() ,saved.getTitle());
        return contractMapper.toDTO(saved);
    }

    @Override
    public void deleteContract(Long id) {
        Contract contract = contractRepo.findById(id)
                        .orElseThrow(() -> new BusinessLogicException("Contract with ID: " + id + "not found"));
        contractRepo.delete(contract);
        log.info("Contract With the ID:{} Was Deleted Successfully", id);
    }

    @Override
    public List<ContractDTO> findAll() {
        return contractMapper.toDTOList(contractRepo.findAll());
    }

    @Override
    public List<ContractDTO> findByValueGreaterThan(double amount) {
        List<Contract> contracts = contractRepo.findByValueGreaterThan(amount);
        return contractMapper.toDTOList(contracts);
    }

    @Override
    public List<ContractDTO> findContractsExpiringIn() {
        return contractMapper.toDTOList(contractRepo.findContractsExpiringIn(LocalDateTime.now().plusDays(30)));
    }

    @Override
    public List<ContractDTO> findContractsExpiringIn(int days) {
        return contractMapper.toDTOList(contractRepo.findContractsExpiringIn(LocalDateTime.now().plusDays(days)));
    }

    @Override
    public long countContracts(){
        return contractRepo.count();
    }

    @Override
    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepo.findById(id)
                .orElseThrow(() -> new BusinessLogicException("Contract with ID: " + id + " not found"));
        return contractMapper.toDTO(contract);
    }
}
