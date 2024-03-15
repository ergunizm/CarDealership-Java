package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.ModelService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddModelRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetModelResponse;
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
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;
    @GetMapping("/getAll/{typeName}")
    public DataResult<List<GetModelResponse>> getAllByType(@PathVariable("typeName") String name){
        return modelService.getAllByType(name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddModelRequest addModelRequest){
        return ResponseEntity.ok(modelService.add(addModelRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(modelService.delete(id));
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
