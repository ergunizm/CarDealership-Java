package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.SellerRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.CompanyRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.SellerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerRulesManager implements SellerRulesService {
    private CompanyRepository companyRepository;
    private SellerRepository sellerRepository;
    private UserRepository userRepository;
    @Override
    public boolean checkIfCompanyExists(Long cid) {
        return companyRepository.existsById(cid);
    }
}
