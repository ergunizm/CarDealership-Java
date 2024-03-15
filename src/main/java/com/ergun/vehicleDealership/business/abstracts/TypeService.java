package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddTypeRequest;
import com.ergun.vehicleDealership.core.utilities.results.Result;

public interface TypeService {
    Result add(AddTypeRequest addTypeRequest);
    Result delete(int id);
}
