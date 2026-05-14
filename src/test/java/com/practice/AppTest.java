package com.practice;

import com.practice.dto.ContractDTO;
import com.practice.exceptions.BusinessLogicException;
import com.practice.mappers.ContractMapper;
import com.practice.models.Contract;
import com.practice.repositories.ContractRepository;
import com.practice.services.implementations.ContractServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ContractMapper contractMapper;

    @InjectMocks
    private ContractServiceImpl contractService;

    @Test
    void shouldReturnAllContracts() {

    Contract contract = new Contract();

    ContractDTO contractDTO = new ContractDTO(
            "test",
            100,
            LocalDateTime.of(2026, 5, 7, 0, 30),
            LocalDateTime.of(2026, 5, 7, 0, 30),
            1L
    );

    when(contractRepository.findAll()).thenReturn(List.of(contract));
    when(contractMapper.toDTO(contract)).thenReturn(contractDTO);

    assertEquals(List.of(contractDTO), contractService.findAll());
    }

    @Test
    void countContracts() {

        when(contractRepository.count()).thenReturn(5L);

        Long result = contractService.countContracts();

        assertEquals(5L, result);
    }

    @Test
    void getContractById() {

        Contract contract = new Contract();
        when(contractRepository.findById(10L)).thenReturn(Optional.of(contract));

        ContractDTO contractDTO = new ContractDTO(
                "test",
                100,
                LocalDateTime.of(2026, 5, 7, 0, 30),
                LocalDateTime.of(2026, 5, 7, 0, 30),
                1L
        );

        when(contractMapper.toDTO(contract)).thenReturn(contractDTO);

        assertEquals(contractDTO, contractService.getContractById(10L));
    }

    @Test
    void notFound() {

        when(contractRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BusinessLogicException.class, () -> contractService.getContractById(99L));
    }

    @Test
    void saveContract() {

        Contract contract = new Contract();
        contractRepository.save(contract);

        verify(contractRepository).save(any());
    }
}
