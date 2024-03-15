package com.ergun.vehicleDealership.business.concretes;

import com.ergun.vehicleDealership.business.abstracts.UserService;
import com.ergun.vehicleDealership.business.dtos.responses.GetUserResponse;
import com.ergun.vehicleDealership.core.utilities.mappers.ModelMapperService;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.ErrorDataResult;
import com.ergun.vehicleDealership.core.utilities.results.SuccessDataResult;
import com.ergun.vehicleDealership.dataAccess.abstracts.UserRepository;
import com.ergun.vehicleDealership.entities.userEntities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    ModelMapperService mms;
    UserRepository userRepository;
    @Override
    public DataResult<GetUserResponse> getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return new ErrorDataResult<>("You entered wrong username!");
        }
        GetUserResponse UserResponse = mms.forResponse().map(user, GetUserResponse.class);

        return new SuccessDataResult<GetUserResponse>("User is fetched", UserResponse);
    }

    @Override
    public DataResult<List<GetUserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return new ErrorDataResult<>("No users found!");
        }
        List<GetUserResponse> userResponse = users.stream().map(
                user -> mms.forResponse().map(user, GetUserResponse.class)
        ).collect(Collectors.toList());

        return new SuccessDataResult<List<GetUserResponse>>("Users are fetched", userResponse);
    }
}
