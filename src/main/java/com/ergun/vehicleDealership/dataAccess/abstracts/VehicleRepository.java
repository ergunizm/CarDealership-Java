package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import com.ergun.vehicleDealership.entities.vehicleEntities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT v.seller_id FROM vehicles v WHERE v.id = :vid", nativeQuery = true)
    Long findSellerOfVehicleById(@Param("vid") Long id);
    List<Vehicle> findByType(Type type);
}
