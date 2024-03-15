package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddCompanyRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface CompanyService {
    DataResult<List<GetSellerResponse>> getAllSellersOfCompany(String companyName);
    Result add(AddCompanyRequest addCompanyRequest);
    Result delete(Long id);
}
