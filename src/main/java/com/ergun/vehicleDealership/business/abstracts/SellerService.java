package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddSellerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateSellerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface SellerService {
    DataResult<List<GetSellerResponse>> getAll();
    DataResult<GetSellerResponse> getSellerByUsername(String username);
    DataResult<List<GetVehicleResponse>> getVehicleOfSeller(String username);
    Result add(AddSellerRequest addSellerRequest);
    Result update(UpdateSellerRequest updateSellerRequest);
    Result delete(Long id);
}
