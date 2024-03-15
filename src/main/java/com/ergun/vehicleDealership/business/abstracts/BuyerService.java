package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetBuyerResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface BuyerService {
    DataResult<List<GetBuyerResponse>> getAll();
    DataResult<List<GetBuyerResponse>> getByType(String type);
    DataResult<GetBuyerResponse> getByUsername(String username);
    Result add(AddBuyerRequest addBuyerRequest);
    Result update(UpdateBuyerRequest updateBuyerRequest);
    Result delete(Long id);
}
