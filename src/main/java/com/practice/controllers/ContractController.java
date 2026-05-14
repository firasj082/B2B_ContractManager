package com.practice.controllers;


import com.practice.dto.ContractDTO;
import com.practice.responses.ApiResponse;
import com.practice.services.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${contract.base.path}")
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContractDTO>> saveContract(@RequestBody ContractDTO contractDTO){
        ContractDTO created = contractService.saveContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Contract Created Successfully", created));
    }

    @DeleteMapping("${id.operations.path}")
    public ResponseEntity<ApiResponse<Void>> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Contract Deleted Successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContractDTO>>> findAll() {
        ApiResponse<List<ContractDTO>> response = new ApiResponse<>(true, "All Contracts Are Fetched Successfully", contractService.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("${count.operations.path}")
    public ResponseEntity<ApiResponse<Long>> countContracts() {

        ApiResponse<Long> response = new ApiResponse<>(true, "Number Of Contracts", contractService.countContracts());
        return ResponseEntity.ok(response);
    }
}
