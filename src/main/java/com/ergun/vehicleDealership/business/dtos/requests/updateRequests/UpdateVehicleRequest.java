package com.ergun.vehicleDealership.business.dtos.requests.updateRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private Long id;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private String typeName;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private String modelName;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private int year;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private int kmCount;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private Long sellerId;
}
