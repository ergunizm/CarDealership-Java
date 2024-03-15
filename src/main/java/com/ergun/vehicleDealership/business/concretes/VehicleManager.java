package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.VehicleService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.VehicleRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.ModelRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.SellerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.VehicleRepository;
import com.ergun.vehicleDealership.entities.userEntities.Seller;
import com.ergun.vehicleDealership.entities.vehicleEntities.Model;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import com.ergun.vehicleDealership.entities.vehicleEntities.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleManager implements VehicleService {
    private VehicleRepository vehicleRepository;
    private ModelRepository modelRepository;
    private TypeRepository typeRepository;
    private SellerRepository sellerRepository;
    private VehicleRulesService vehicleRulesService;
    private ModelMapperService mms;
    @Override
    public Result add(AddVehicleRequest addVehicleRequest) {
        Vehicle vehicle = mms.forRequest().map(addVehicleRequest, Vehicle.class);
        if(sellerRepository.findById(addVehicleRequest.getSellerId()).isEmpty()){
            return new ErrorResult("You entered wrong seller id!");
        }
        Seller seller = sellerRepository.findById(addVehicleRequest.getSellerId()).get();
        vehicle.setSeller(seller);
        if(!vehicleRulesService.checkIfTypeExists(addVehicleRequest.getTypeName())){
            return new ErrorResult("You entered wrong type!");
        }
        Type type = typeRepository.findByTypeName(addVehicleRequest.getTypeName());
        vehicle.setType(type);
        if(!vehicleRulesService.checkIfModelExists(addVehicleRequest.getModelName())){
            return new ErrorResult("You entered wrong model!");
        }

        Model model = modelRepository.findByModelName(addVehicleRequest.getModelName());
        if(!vehicleRulesService.checkIfTypeModelDuoExists(type, model)){
            return new ErrorResult("Wrong type model duo!");
        }
        vehicle.setModel(model);

        vehicleRepository.save(vehicle);

        return new SuccessResult("Vehicle is added!");
    }

    @Override
    public Result update(UpdateVehicleRequest updateVehicleRequest) {
        if(vehicleRepository.findById(updateVehicleRequest.getId()).isEmpty()){
            return new ErrorResult("You entered wrong id!");
        }
        Vehicle vehicle = vehicleRepository.findById(updateVehicleRequest.getId()).get();
        if(sellerRepository.findById(updateVehicleRequest.getSellerId()).isEmpty()){
            return new ErrorResult("You entered wrong seller id!");
        }
        Seller seller = sellerRepository.findById(updateVehicleRequest.getSellerId()).get();
        vehicle.setSeller(seller);
        if(!vehicleRulesService.checkIfTypeExists(updateVehicleRequest.getTypeName())){
            return new ErrorResult("You entered wrong type!");
        }
        Type type = typeRepository.findByTypeName(updateVehicleRequest.getTypeName());
        vehicle.setType(type);
        if(!vehicleRulesService.checkIfModelExists(updateVehicleRequest.getModelName())){
            return new ErrorResult("You entered wrong model!");
        }
        Model model = modelRepository.findByModelName(updateVehicleRequest.getModelName());
        vehicle.setModel(model);
        vehicle.setKmCount(updateVehicleRequest.getKmCount());
        vehicle.setYear(updateVehicleRequest.getYear());

        vehicleRepository.save(vehicle);

        return new SuccessResult("Vehicle is updated!");
    }

    @Override
    public Result delete(Long id) {
        vehicleRepository.deleteById(id);
        return new SuccessResult("Vehicle is deleted!");
    }

    @Override
    public DataResult<List<GetVehicleResponse>> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if(vehicles.isEmpty()){
            return new ErrorDataResult<>("No vehicles found!");
        }
        List<GetVehicleResponse> vehicleResponses = vehicles.stream().map(
                vehicle -> mms.forResponse().map(vehicle, GetVehicleResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetVehicleResponse>>("Vehicles are fetched!", vehicleResponses);
    }

    @Override
    public DataResult<List<GetVehicleResponse>> getByType(String typeName) {
        if(!typeRepository.existsByTypeName(typeName)){
            return new ErrorDataResult<>("You entered wrong type!");
        }
        Type type = typeRepository.findByTypeName(typeName);
        List<Vehicle> vehicles = vehicleRepository.findByType(type);
        if(vehicles.isEmpty()){
            return new ErrorDataResult<>("No vehicles found for this type!");
        }
        List<GetVehicleResponse> vehicleResponses = vehicles.stream().map(
                vehicle -> mms.forResponse().map(vehicle, GetVehicleResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetVehicleResponse>>("Vehicles are fetched!", vehicleResponses);
    }
}
