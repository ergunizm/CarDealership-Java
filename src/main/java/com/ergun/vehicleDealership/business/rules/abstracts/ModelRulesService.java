package com.ergun.vehicleDealership.business.rules.abstracts;

public interface ModelRulesService {
    boolean checkIfTypeExist(String type);
    boolean checkIfModelExist(String model);
}
