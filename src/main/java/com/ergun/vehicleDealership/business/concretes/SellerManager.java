package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.SellerService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddSellerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateSellerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
import com.ergun.vehicleDealership.business.rules.abstracts.SellerRulesService;
import com.ergun.vehicleDealership.business.rules.abstracts.UserRulesService;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.*;
import com.ergun.vehicleDealership.dataAccess.abstracts.CompanyRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.SellerRepository;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import com.ergun.vehicleDealership.entities.Company;
import com.ergun.vehicleDealership.entities.userEntities.Seller;
import com.ergun.vehicleDealership.entities.userEntities.User;
import com.ergun.vehicleDealership.entities.vehicleEntities.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SellerManager implements SellerService {
    private ModelMapperService modelMapperService;
    private SellerRepository sellerRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private SellerRulesService sellerRulesService;
    private UserRulesService userRulesService;

    @Override
    public DataResult<List<GetSellerResponse>> getAll() {
        List<Seller> sellers = sellerRepository.findAll();
        if(sellers.isEmpty()){
            return new ErrorDataResult<>("No sellers found!");
        }
        List<GetSellerResponse> sellerResponses = sellers.stream().map(
                seller -> modelMapperService.forResponse().map(seller,GetSellerResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetSellerResponse>>("Sellers are fetched!", sellerResponses);
    }

    @Override
    public DataResult<GetSellerResponse> getSellerByUsername(String username) {
        if(! sellerRepository.existsByUser(userRepository.findByUsername(username))){
            return new ErrorDataResult<>("No seller found for this username!");
        }
        Seller seller = sellerRepository.findByUser(userRepository.findByUsername(username));
        GetSellerResponse sellerResponse = modelMapperService.forResponse().map(seller,GetSellerResponse.class);

        return new SuccessDataResult<GetSellerResponse>("Sellers are fetched!", sellerResponse);
    }

    @Override
    public DataResult<List<GetVehicleResponse>> getVehicleOfSeller(String username) {
        if(!sellerRepository.existsByUser(userRepository.findByUsername(username))){
            return new ErrorDataResult<>("No seller found for this username!");
        }
        Seller seller = sellerRepository.findByUser(userRepository.findByUsername(username));
        List<Vehicle> vehicles = seller.getSellingVehicles();
        if(vehicles.isEmpty()){
            return new ErrorDataResult<>("You are not selling any vehicle!");
        }
        List<GetVehicleResponse> vehicleResponses = vehicles.stream().map(
                vehicle -> modelMapperService.forResponse().map(vehicle, GetVehicleResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetVehicleResponse>>("Vehicles are fetched!",vehicleResponses);
    }

    @Override
    public Result add(AddSellerRequest addSellerRequest) {
        if(userRulesService.checkIfUsernameExists(addSellerRequest.getUsername())){
            return new ErrorResult("Username already exists!");
        }
        if(userRulesService.checkIfEmailExists(addSellerRequest.getEmail())){
            return new ErrorResult("Email already exists!");
        }
        Seller seller = this.modelMapperService.forRequest().map(addSellerRequest, Seller.class);
        if(addSellerRequest.getCompanyId() != null && sellerRulesService.checkIfCompanyExists(addSellerRequest.getCompanyId())){
            Company company = companyRepository.findById(addSellerRequest.getCompanyId()).get();
            seller.setCompany(company);
        }

        sellerRepository.save(seller);

        return new SuccessResult("Seller is added!");
    }

    @Override
    public Result update(UpdateSellerRequest updateSellerRequest) {
        if(sellerRepository.findById(updateSellerRequest.getId()).isEmpty()){
            return new ErrorResult("No seller found for this id!");
        }
        Seller seller = sellerRepository.findById(updateSellerRequest.getId()).get();
        seller.setFirstName(updateSellerRequest.getFirstName());
        seller.setLastName(updateSellerRequest.getLastName());

        if(updateSellerRequest.getCompanyId() != null && sellerRulesService.checkIfCompanyExists(updateSellerRequest.getCompanyId())){
            Company company = companyRepository.findById(updateSellerRequest.getCompanyId()).get();
            seller.setCompany(company);
        }

        User user = userRepository.findByUsername(updateSellerRequest.getUsername());
        if(seller.getUser() != user || (!user.getEmail().equals(updateSellerRequest.getEmail()) && userRulesService.checkIfEmailExists(updateSellerRequest.getEmail()))){
            return new ErrorResult("Something went wrong!");
        }
        user.setPassword(updateSellerRequest.getPassword());
        user.setEmail(updateSellerRequest.getEmail());

        sellerRepository.save(seller);
        userRepository.save(user);

        return new SuccessResult("Seller is updated!");
    }

    @Override
    public Result delete(Long id) {
        sellerRepository.deleteById(id);

        return new SuccessResult("Seller is deleted!");
    }
}
