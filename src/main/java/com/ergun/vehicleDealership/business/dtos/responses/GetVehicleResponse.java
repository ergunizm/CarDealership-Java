package com.ergun.vehicleDealership.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVehicleResponse {
    private Long vehicleId;
    private String typeName;
    private String modelName;
    private int year;
    private int kmCount;
    private Long sellerId;
}
