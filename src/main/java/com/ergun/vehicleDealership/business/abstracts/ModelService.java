package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddModelRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetModelResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface ModelService {
    DataResult<List<GetModelResponse>> getAllByType(String typeName);
    Result add(AddModelRequest addModelRequest);
    Result delete(int id);
}
