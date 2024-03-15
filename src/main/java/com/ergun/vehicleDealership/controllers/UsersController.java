package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.UserService;
import com.ergun.vehicleDealership.business.dtos.responses.GetUserResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UsersController {
    private UserService userService;

    @GetMapping("/getAll")
    public DataResult<List<GetUserResponse>> getAll(){
        return this.userService.getAll();
    }

    @GetMapping("/get/{username}")
    public DataResult<GetUserResponse> getByUsername(@PathVariable String username){
        return this.userService.getByUsername(username);
    }
}
