package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.BuyerService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetBuyerResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.BuyerRulesService;
import com.ergun.vehicleDealership.business.rules.abstracts.UserRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.BuyerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.TypeRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import com.ergun.vehicleDealership.entities.userEntities.Buyer;
import com.ergun.vehicleDealership.entities.userEntities.User;
import com.ergun.vehicleDealership.entities.vehicleEntities.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuyerManager implements BuyerService {
    private ModelMapperService modelMapperService;
    private BuyerRepository buyerRepository;
    private UserRepository userRepository;
    private TypeRepository typeRepository;
    private BuyerRulesService buyerRulesService;
    private UserRulesService userRulesService;

    @Override
    public DataResult<List<GetBuyerResponse>> getAll() {
        List<Buyer> buyers = buyerRepository.findAll();
        if(buyers.isEmpty()){
            return new ErrorDataResult<>("No buyers found!");
        }
        List<GetBuyerResponse> buyerResponses = buyers.stream().map(
                buyer -> modelMapperService.forResponse().map(buyer, GetBuyerResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetBuyerResponse>>("Buyers are fetched!", buyerResponses);
    }

    @Override
    public DataResult<List<GetBuyerResponse>> getByType(String type) {
        if(!buyerRulesService.checkIfTypeExists(type)){
            return new ErrorDataResult<>("You entered wrong type!");
        }
        Type type1 = typeRepository.findByTypeName(type);
        List<Buyer> buyersWithType = buyerRepository.findByLookingType(type1);
        if(buyersWithType.isEmpty()){
            return new ErrorDataResult<>("No buyers found for this type!");
        }
        List<GetBuyerResponse> buyersWithTypeResponses = buyersWithType.stream().map(
                buyer -> modelMapperService.forResponse().map(buyer, GetBuyerResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetBuyerResponse>>("Buyers are fetched!", buyersWithTypeResponses);
    }

    @Override
    public DataResult<GetBuyerResponse> getByUsername(String username) {
        if(! buyerRulesService.checkIfUserExists(username)){
            return new ErrorDataResult<>("No buyer found for this username!");
        }
        Buyer buyer = buyerRepository.findByUser(userRepository.findByUsername(username));
        GetBuyerResponse buyerResponse = modelMapperService.forResponse().map(buyer, GetBuyerResponse.class);

        return new SuccessDataResult<GetBuyerResponse>("Buyers are fetched!", buyerResponse);
    }

    @Override
    public Result add(AddBuyerRequest addBuyerRequest) {
        Buyer buyer = this.modelMapperService.forRequest().map(addBuyerRequest, Buyer.class);
        if(!buyerRulesService.checkIfTypeExists(addBuyerRequest.getType()) && addBuyerRequest.getType() != null){
            return new ErrorResult("You entered wrong type!");
        }
        if(addBuyerRequest.getType() != null){
            Type type = typeRepository.findByTypeName(addBuyerRequest.getType());
            buyer.setLookingType(type);
        }

        buyerRepository.save(buyer);

        return new SuccessResult("Buyer is added!");
    }

    @Override
    public Result update(UpdateBuyerRequest updateBuyerRequest) {
        if(buyerRepository.findById(updateBuyerRequest.getId()).isEmpty()){
            return new ErrorResult("No buyers found for this id!");
        }
        Buyer buyer = buyerRepository.findById(updateBuyerRequest.getId()).get();
        buyer.setFirstName(updateBuyerRequest.getFirstName());
        buyer.setLastName(updateBuyerRequest.getLastName());

        if(updateBuyerRequest.getType() != null && updateBuyerRequest.getType().trim() != ""){
            if(!buyerRulesService.checkIfTypeExists(updateBuyerRequest.getType())){
                return new ErrorResult("You entered wrong type!");
            }
            Type type = typeRepository.findByTypeName(updateBuyerRequest.getType());
            buyer.setLookingType(type);
        }

        User user = userRepository.findByUsername(updateBuyerRequest.getUsername());
        if(buyer.getUser() != user){
            return new ErrorResult("Something went wrong!");
        }
        user.setPassword(updateBuyerRequest.getPassword());
        user.setEmail(updateBuyerRequest.getEmail());

        buyerRepository.save(buyer);
        userRepository.save(user);

        return new SuccessResult("Buyer is updated!");
    }

    @Override
    public Result delete(Long id) {
        buyerRepository.deleteById(id);

        return new SuccessResult("Buyer is deleted!");
    }
}
