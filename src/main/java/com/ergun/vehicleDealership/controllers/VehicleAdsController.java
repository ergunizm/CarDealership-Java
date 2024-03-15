package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.VehicleAdService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleAdRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleAdResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.ErrorDataResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ads")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class VehicleAdsController {
    private VehicleAdService vehicleAdService;

    @GetMapping("/getAll")
    public DataResult<List<GetVehicleAdResponse>> getAll(){
        return this.vehicleAdService.getAll();
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody AddVehicleAdRequest addVehicleAdRequest){
        return ResponseEntity.ok(this.vehicleAdService.add(addVehicleAdRequest));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.vehicleAdService.delete(id));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<Object>("Validation Errors", validationErrors);
    }
}
