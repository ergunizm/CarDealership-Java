package com.ergun.vehicleDealership.entities;

import com.ergun.vehicleDealership.entities.userEntities.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true)
    private String companyName;
    @OneToMany(mappedBy = "company")
    private List<Seller> companySellers;
}
