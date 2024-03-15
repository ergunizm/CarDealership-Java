package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.VehicleRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.ModelRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.entities.vehicleEntities.Model;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleRulesManager implements VehicleRulesService {
    private TypeRepository typeRepository;
    private ModelRepository modelRepository;
    @Override
    public boolean checkIfTypeExists(String type) {
        return typeRepository.existsByTypeName(type);
    }

    @Override
    public boolean checkIfModelExists(String model) {
        return modelRepository.existsByModelName(model);
    }

    @Override
    public boolean checkIfTypeModelDuoExists(Type type, Model model) {
        return modelRepository.findByType(type).contains(model);
    }
}
