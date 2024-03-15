package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.VehicleService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateVehicleRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
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
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class VehiclesController {
    private VehicleService vehicleService;

    @GetMapping("/getAll")
    public DataResult<List<GetVehicleResponse>> getAll(){
        return vehicleService.getAll();
    }
    @GetMapping("/getAll/{typeName}")
    public DataResult<List<GetVehicleResponse>> getAllByType(@PathVariable("typeName") String name){
        return vehicleService.getByType(name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddVehicleRequest addVehicleRequest){
        return ResponseEntity.ok(vehicleService.add(addVehicleRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateVehicleRequest updateVehicleRequest){
        return ResponseEntity.ok(vehicleService.update(updateVehicleRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.delete(id));
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
