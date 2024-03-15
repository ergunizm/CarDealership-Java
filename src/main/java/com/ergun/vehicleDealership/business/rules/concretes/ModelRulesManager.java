package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.ModelRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.ModelRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelRulesManager implements ModelRulesService {
    private TypeRepository typeRepository;
    private ModelRepository modelRepository;
    @Override
    public boolean checkIfTypeExist(String type) {
        return typeRepository.existsByTypeName(type);
    }

    @Override
    public boolean checkIfModelExist(String model) {
        return modelRepository.existsByModelName(model);
    }
}
