package com.ergun.vehicleDealership.business.abstracts;

import com.ergun.vehicleDealership.business.dtos.responses.GetUserResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;

import java.util.List;

public interface UserService {
    DataResult<GetUserResponse> getByUsername(String username);
    DataResult<List<GetUserResponse>> getAll();
}
