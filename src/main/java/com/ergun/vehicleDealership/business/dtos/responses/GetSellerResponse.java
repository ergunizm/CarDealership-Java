package com.ergun.vehicleDealership.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSellerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long companyId;
    private String username;
    private String email;
    private String password;
}