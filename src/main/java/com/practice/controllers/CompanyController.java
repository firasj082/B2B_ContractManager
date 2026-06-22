package com.practice.controllers;

import com.practice.dto.CompanyDTO;
import com.practice.responses.ApiResponse;
import com.practice.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CompanyDTO>> saveCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        CompanyDTO saved = companyService.saveCompany(companyDTO);
        ApiResponse<CompanyDTO> response = new ApiResponse<>(true, "The Company Is Saved Successfully", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
