package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.SellerService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddSellerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateSellerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
import com.ergun.vehicleDealership.business.dtos.responses.GetVehicleResponse;
import com.ergun.vehicleDealership.core.utilities.results.DataResult;
import com.ergun.vehicleDealership.core.utilities.results.ErrorDataResult;
import com.ergun.vehicleDealership.core.utilities.results.ErrorResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sellers")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SellersController {
    SellerService sellerService;

    @GetMapping("/getAll")
    public DataResult<List<GetSellerResponse>> getAll(){
        return this.sellerService.getAll();
    }

    @GetMapping("/profile/{username}")
    public DataResult<GetSellerResponse> getByUsername(@PathVariable String username){
        return this.sellerService.getSellerByUsername(username);
    }
    @GetMapping("/selling/{username}")
    public DataResult<List<GetVehicleResponse>> getVehicles(@PathVariable String username){
        return this.sellerService.getVehicleOfSeller(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddSellerRequest addSellerRequest){
        return ResponseEntity.ok(this.sellerService.add(addSellerRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateSellerRequest updateSellerRequest){
        return ResponseEntity.ok(this.sellerService.update(updateSellerRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.sellerService.delete(id));
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ErrorResult("Unique Integrity Error");
    }
}
