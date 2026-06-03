package com.practice.services.implementations;

import com.practice.dto.CompanyDTO;
import com.practice.mappers.CompanyMapper;
import com.practice.models.Company;
import com.practice.repositories.CompanyRepository;
import com.practice.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
@Primary
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        Company saved = companyRepository.save(company);
        log.info("Company with ID: {} and Name: {} Created Successfully", saved.getId(), saved.getName());
        return companyMapper.toDTO(saved);
    }
}
