package com.ergun.vehicleDealership.business.rules.abstracts;

public interface UserRulesService {
    boolean checkIfEmailExists(String email);
    boolean checkIfUsernameExists(String username);
}
