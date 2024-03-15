package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.Result;

import java.util.List;

public interface VehicleService {
    Result add(AddVehicleRequest addVehicleRequest);
    Result update(UpdateVehicleRequest updateVehicleRequest);
    Result delete(Long id);
    DataResult<List<GetVehicleResponse>> getAll();
    DataResult<List<GetVehicleResponse>> getByType(String typeName);
}
