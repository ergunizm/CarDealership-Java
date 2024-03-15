package com.ergun.vehicleDealership.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBuyerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String type;
    private String username;
    private String email;
    private String password;
}