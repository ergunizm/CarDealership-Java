package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    boolean existsByTypeName(String name);
    Type findByTypeName(String name);
}
