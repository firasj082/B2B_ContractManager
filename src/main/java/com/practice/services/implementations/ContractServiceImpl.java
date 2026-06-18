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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteContract(Long id) {
        contractRepo.deleteById(id);
        log.info("Contract With the ID:{} Was Deleted Successfully", id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public List<ContractDTO> findAll() {
        return contractRepo.findAll().stream()
                .map(contractMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public List<ContractDTO> findByValueGreaterThan(double amount) {
        return contractRepo.findByValueGreaterThan(amount).stream()
                .map(contractMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public List<ContractDTO> findContractsExpiringIn() {
        return contractRepo.findContractsExpiringIn(LocalDateTime.now().plusDays(30)).stream()
                .map(contractMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public List<ContractDTO> findContractsExpiringIn(int days) {
        return contractRepo.findContractsExpiringIn(LocalDateTime.now().plusDays(days)).stream()
                .map(contractMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public long countContracts(){
        return contractRepo.count();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Override
    public ContractDTO getContractById(Long id) {

        return contractMapper.toDTO(contractRepo.findById(id)
                .orElseThrow(() -> new BusinessLogicException("Contract By The Id:" + id + " Not Found")));
    }
}
