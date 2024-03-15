package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.vehicleEntities.Model;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByModelName(String name);
    List<Model> findByType(Type type);
    Model findByModelName(String name);
}
