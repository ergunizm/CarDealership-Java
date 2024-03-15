package com.ergun.vehicleDealership.business.rules.abstracts;

import com.ergun.vehicleDealership.entities.vehicleEntities.Model;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;

public interface VehicleRulesService {
    boolean checkIfTypeExists(String type);
    boolean checkIfModelExists(String model);
    boolean checkIfTypeModelDuoExists(Type type, Model model);
}
