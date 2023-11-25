package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO user);
    UserDTO login(String email, String password);
}
