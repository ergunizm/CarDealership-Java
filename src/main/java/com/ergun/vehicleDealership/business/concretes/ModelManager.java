package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.ModelService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddModelRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetModelResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.ModelRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.ModelRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.entities.vehicleEntities.Model;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelMapperService mms;
    private ModelRepository modelRepository;
    private TypeRepository typeRepository;
    private ModelRulesService modelRulesService;

    @Override
    public DataResult<List<GetModelResponse>> getAllByType(String typeName) {
        if(!typeRepository.existsByTypeName(typeName)){
            return new ErrorDataResult<>("You entered wrong type!");
        }
        Type type = typeRepository.findByTypeName(typeName);
        List<Model> models = modelRepository.findByType(type);
        if(models.isEmpty()){
            return new ErrorDataResult<>("No models found for this type!");
        }
        List<GetModelResponse> modelResponses = models.stream().map(
                model -> mms.forResponse().map(model, GetModelResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetModelResponse>>("Models are fetched!", modelResponses);
    }

    @Override
    public Result add(AddModelRequest addModelRequest) {
        if(modelRulesService.checkIfModelExist(addModelRequest.getModelName())){
            return new ErrorResult("Model already exists!");
        }
        Model model = mms.forRequest().map(addModelRequest, Model.class);
        if(!modelRulesService.checkIfTypeExist(addModelRequest.getTypeName())){
            return new ErrorResult("You entered wrong type!");
        }
        Type type = typeRepository.findByTypeName(addModelRequest.getTypeName());
        model.setType(type);
        modelRepository.save(model);

        return new SuccessResult("Model is added!");
    }

    @Override
    public Result delete(int id) {
        modelRepository.deleteById(id);
        return new SuccessResult("Model is deleted!");
    }
}
