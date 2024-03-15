package com.ergun.vehicleDealership.dataAccess.abstracts;

import com.ergun.vehicleDealership.entities.vehicleEntities.Vehicle;
import com.ergun.vehicleDealership.entities.vehicleEntities.VehicleAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAdRepository extends JpaRepository<VehicleAdvertisement, Long> {
    boolean existsByVehicle(Vehicle vehicle);
}
