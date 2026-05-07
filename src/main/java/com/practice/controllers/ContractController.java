package com.practice.controllers;


import com.practice.dto.ContractDTO;
import com.practice.services.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDTO saveContract(@RequestBody ContractDTO contractDTO){
        return contractService.saveContract(contractDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
    }

    @GetMapping
    public List<ContractDTO> findAll() {
        return this.contractService.findAll();
    }

}
