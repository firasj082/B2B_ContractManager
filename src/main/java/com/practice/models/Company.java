package com.practice.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private String address;

    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private UUID taxId;

    @PrePersist
    public void generateTaxId() {
        if (this.taxId == null) {
            this.taxId = UUID.randomUUID();
        }
    }
}
