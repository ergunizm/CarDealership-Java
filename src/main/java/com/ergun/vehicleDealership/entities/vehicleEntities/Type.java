package com.ergun.vehicleDealership.entities.vehicleEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "types")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Type {     //car, helicopter, plane
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", unique = true)
    private String typeName;
    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Model> models;
}
