package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.BuyerRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.BuyerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuyerRulesManager implements BuyerRulesService {
    private TypeRepository typeRepository;
    private BuyerRepository buyerRepository;
    private UserRepository userRepository;
    @Override
    public boolean checkIfTypeExists(String type) {
        return typeRepository.existsByTypeName(type);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return buyerRepository.existsByUser(userRepository.findByUsername(username));
    }
}
