package com.ergun.vehicleDealership.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVehicleAdResponse {
    private Long adId;
    private Long vehicleId;
    private Long sellerId;
}
