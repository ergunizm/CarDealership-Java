package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.CompanyService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddCompanyRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetSellerResponse;
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
@RequestMapping("/companies")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CompaniesController {
    private CompanyService companyService;
    @GetMapping("/sellers/{cname}")
    public DataResult<List<GetSellerResponse>> getSellers(@PathVariable String cname){
        return companyService.getAllSellersOfCompany(cname);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddCompanyRequest addCompanyRequest){
        return ResponseEntity.ok(companyService.add(addCompanyRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(companyService.delete(id));
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
