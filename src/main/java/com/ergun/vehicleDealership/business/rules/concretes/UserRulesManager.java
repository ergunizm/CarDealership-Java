package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.UserRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRulesManager implements UserRulesService {
    private UserRepository userRepository;
    @Override
    public boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
