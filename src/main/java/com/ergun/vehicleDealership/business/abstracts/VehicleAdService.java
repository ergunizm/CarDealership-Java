package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleAdRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleAdResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface VehicleAdService {
    Result add(AddVehicleAdRequest addVehicleAdRequest);
    Result delete(Long id);
    DataResult<List<GetVehicleAdResponse>> getAll();
}
