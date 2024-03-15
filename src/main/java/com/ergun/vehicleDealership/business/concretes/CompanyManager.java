package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.CompanyService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddCompanyRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.CompanyRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.CompanyRepository;
import com.ergun.vehicleDealership.entities.Company;
import com.ergun.vehicleDealership.entities.userEntities.Seller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyManager implements CompanyService {
    private ModelMapperService modelMapperService;
    private CompanyRepository companyRepository;
    private CompanyRulesService companyRulesService;

    @Override
    public DataResult<List<GetSellerResponse>> getAllSellersOfCompany(String companyName) {
        if(!companyRulesService.checkIfCompanyExists(companyName)){
            return new ErrorDataResult<>("You entered wrong name!");
        }
        Company company =  companyRepository.findByCompanyName(companyName);
        List<Seller> sellers = company.getCompanySellers();

        if(sellers.isEmpty()){
            return new ErrorDataResult<>("No sellers found for this name!");
        }

        List<GetSellerResponse> sellerResponses = sellers.stream().map(
                seller -> modelMapperService.forResponse().map(seller, GetSellerResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetSellerResponse>>("Sellers are fetched!", sellerResponses);
    }

    @Override
    public Result add(AddCompanyRequest addCompanyRequest) {
        Company c = modelMapperService.forRequest().map(addCompanyRequest, Company.class);
        companyRepository.save(c);

        return new SuccessResult("Company is added!");
    }

    @Override
    public Result delete(Long id) {
        companyRepository.deleteById(id);

        return new SuccessResult("Company is deleted!");
    }
}
