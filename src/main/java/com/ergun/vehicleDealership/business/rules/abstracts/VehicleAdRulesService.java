package com.ergun.vehicleDealership.business.rules.abstracts;

public interface VehicleAdRulesService {
    boolean checkIfSellerExists(Long sellerId);
    boolean checkIfVehicleExists(Long vehicleId);
    boolean checkIfVehicleExistsForSeller(Long vehicleId, Long sellerId);
    boolean checkIfAdExists(Long vehicleId);
}
