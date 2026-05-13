package com.practice.repositories;

import com.practice.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByValueGreaterThan(double amount);

    @Query("select c from Contract c where c.endDate <= :currentDate")
    List<Contract> findContractsExpiringIn(@Param("currentDate") LocalDateTime date);
}
