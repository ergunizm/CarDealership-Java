package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.VehicleAdService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleAdRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleAdResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.VehicleAdRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.VehicleAdRepository;
import com.ergun.vehicleDealership.entities.vehicleEntities.VehicleAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleAdManager implements VehicleAdService {
    private ModelMapperService mms;
    private VehicleAdRepository vaRepository;
    private VehicleAdRulesService vaRulesService;
    @Override
    public Result add(AddVehicleAdRequest addVehicleAdRequest) {
        if(vaRulesService.checkIfAdExists(addVehicleAdRequest.getVehicleId())){
            return new ErrorResult("Advertisement already exists!");
        }
        if(vaRulesService.checkIfSellerExists(addVehicleAdRequest.getSellerId()) && vaRulesService.checkIfVehicleExists(addVehicleAdRequest.getVehicleId())){
            if(vaRulesService.checkIfVehicleExistsForSeller(addVehicleAdRequest.getVehicleId(), addVehicleAdRequest.getSellerId())){
                return new ErrorResult("You are not selling this car, wrong vehicle id!");
            }
            VehicleAdvertisement vehicleAdvertisement = mms.forRequest().map(addVehicleAdRequest, VehicleAdvertisement.class);
            vaRepository.save(vehicleAdvertisement);

            return new SuccessResult("Vehicle Advertisement is added!");
        }
        return new ErrorResult("You entered wrong vehicle and seller id!");
    }

    @Override
    public Result delete(Long id) {
        vaRepository.deleteById(id);
        return new SuccessResult("Vehicle Advertisement is deleted!");
    }

    @Override
    public DataResult<List<GetVehicleAdResponse>> getAll() {
        List<VehicleAdvertisement> ads = vaRepository.findAll();
        if(ads.isEmpty()){
            return new ErrorDataResult<>("No advertisements found!");
        }
        List<GetVehicleAdResponse> adResponse = ads.stream().map(
                ad -> mms.forResponse().map(ad, GetVehicleAdResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetVehicleAdResponse>>("Vehicle Advertisements are fetched", adResponse);
    }
}
