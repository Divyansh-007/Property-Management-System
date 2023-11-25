package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
// to ignore null and unknown properties while returning back as response
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private  String name;
    @NotNull(message = "Email is mandatory")
    private String email;
    private String phone;
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8,max = 15)
    private String password;
}
