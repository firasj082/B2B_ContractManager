package com.practice.controllers;

import com.practice.dto.CompanyDTO;
import com.practice.services.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {this.companyService = companyService;}

    @PostMapping
    public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO) {

        return companyService.saveCompany(companyDTO);
    }
}
