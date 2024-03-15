package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.TypeService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddTypeRequest;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.Result;
import com.ergun.vehicleDealership.core.utilities.results.SuccessResult;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TypeManager implements TypeService {
    private ModelMapperService mms;
    private TypeRepository typeRepository;
    @Override
    public Result add(AddTypeRequest addTypeRequest) {
        Type type = mms.forRequest().map(addTypeRequest, Type.class);
        typeRepository.save(type);

        return new SuccessResult("Type is added!");
    }

    @Override
    public Result delete(int id) {
        typeRepository.deleteById(id);
        return new SuccessResult("Type is deleted!");
    }
}
