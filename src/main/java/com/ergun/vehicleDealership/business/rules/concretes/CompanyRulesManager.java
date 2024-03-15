package com.ergun.vehicleDealership.business.rules.concretes;

import com.ergun.vehicleDealership.business.rules.abstracts.CompanyRulesService;
import com.ergun.vehicleDealership.dataAccess.abstracts.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyRulesManager implements CompanyRulesService {
    private CompanyRepository companyRepository;
    @Override
    public boolean checkIfCompanyExists(String name) {
        return companyRepository.existsByCompanyName(name);
    }
}
