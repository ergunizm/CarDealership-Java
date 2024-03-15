package com.ergun.vehicleDealership.business.dtos.requests.updateRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBuyerRequest {
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private Long id;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private String firstName;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private String lastName;
    private String type;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    private String username;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    @Email(message = "Should be valid email")
    private String email;
    @NotNull(message = "Can not be empty")
    @NotBlank(message = "Can not be empty")
    @Pattern(regexp = "^(?=.*\\d).{8,}$")
    private String password;
}
