package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.VehicleAdRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.SellerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.VehicleAdRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.VehicleRepository;
import com.ergun.vehicleDealership.entities.vehicleEntities.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class VehicleAdRulesManager implements VehicleAdRulesService {
    private VehicleRepository vehicleRepository;
    private SellerRepository sellerRepository;
    private VehicleAdRepository vehicleAdRepository;

    @Override
    public boolean checkIfSellerExists(Long sellerId) {
        return sellerRepository.existsById(sellerId);
    }

    @Override
    public boolean checkIfVehicleExists(Long vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    @Override
    public boolean checkIfVehicleExistsForSeller(Long vehicleId, Long sellerId) {
        return Objects.equals(vehicleRepository.findSellerOfVehicleById(vehicleId), sellerId);
    }

    @Override
    public boolean checkIfAdExists(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get(); //already checked in function
        return vehicleAdRepository.existsByVehicle(vehicle);
    }
}
