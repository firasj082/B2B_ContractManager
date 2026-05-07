package com.practice.services;

import com.practice.dto.CompanyDTO;
import com.practice.mappers.CompanyMapper;
import com.practice.models.Company;
import com.practice.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public CompanyService(CompanyRepository companyRepository) {this.companyRepository = companyRepository;}

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.toEntity(companyDTO);
        Company saved = companyRepository.save(company);
        logger.info("Company with ID: {} and Name: {} Created Successfully", saved.getId(), saved.getName());
        return CompanyMapper.toDTO(saved);
    }
}