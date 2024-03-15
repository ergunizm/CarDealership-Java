package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.TypeService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddTypeRequest;
import com.ergun.vehicleDealership.core.utilities.results.ErrorDataResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/types")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class TypesController {
    private TypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddTypeRequest addTypeRequest){
        return ResponseEntity.ok(typeService.add(addTypeRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(typeService.delete(id));
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
