package com.ergun.vehicleDealership.business.rules.abstracts;

public interface BuyerRulesService {
    boolean checkIfTypeExists(String type);
    boolean checkIfUserExists(String username);
}
