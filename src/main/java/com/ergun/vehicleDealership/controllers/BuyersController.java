package com.ergun.vehicleDealership.controllers;

import com.ergun.vehicleDealership.business.abstracts.BuyerService;
import com.ergun.vehicleDealership.business.dtos.requests.addRequests.AddBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.requests.updateRequests.UpdateBuyerRequest;
import com.ergun.vehicleDealership.business.dtos.responses.GetBuyerResponse;
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
@RequestMapping("/buyers")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class BuyersController {
    private BuyerService buyerService;
    @GetMapping("/getAll")
    public DataResult<List<GetBuyerResponse>> getAll(){
        return this.buyerService.getAll();
    }
    @GetMapping("/getAll/{typeName}")
    public DataResult<List<GetBuyerResponse>> getAllByType(@PathVariable("typeName") String name){
        return this.buyerService.getByType(name);
    }
    @GetMapping("/profile/{username}")
    public DataResult<GetBuyerResponse> getByUsername(@PathVariable String username){
        return this.buyerService.getByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AddBuyerRequest addBuyerRequest){
        return ResponseEntity.ok(this.buyerService.add(addBuyerRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateBuyerRequest updateBuyerRequest){
        return ResponseEntity.ok(this.buyerService.update(updateBuyerRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.buyerService.delete(id));
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

    //for unique constraints
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ErrorResult("Unique Integrity Error");
	}
}
