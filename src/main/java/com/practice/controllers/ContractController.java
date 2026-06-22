package com.practice.controllers;


import com.practice.dto.ContractDTO;
import com.practice.responses.ApiResponse;
import com.practice.services.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<ContractDTO>> saveContract(@Valid @RequestBody ContractDTO contractDTO){
        ContractDTO created = contractService.saveContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Contract Created Successfully", created));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Contract Deleted Successfully", null);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ContractDTO>>> findAll() {
        ApiResponse<List<ContractDTO>> response = new ApiResponse<>(true, "All Contracts Are Fetched Successfully", contractService.findAll());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countContracts() {

        ApiResponse<Long> response = new ApiResponse<>(true, "Number Of Contracts", contractService.countContracts());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContractDTO>> getContractById(@PathVariable long id) {

        ApiResponse<ContractDTO> response = new ApiResponse<>(true, "Found Contract With The Id: " + id, contractService.getContractById(id));

        return ResponseEntity.ok(response);
    }
}
