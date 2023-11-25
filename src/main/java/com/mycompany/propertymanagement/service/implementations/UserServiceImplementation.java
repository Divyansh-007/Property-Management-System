package com.mycompany.propertymanagement.service.implementations;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO user) {
        UserEntity newUser = userConverter.convertDTOtoEntity(user);
        newUser = userRepository.save(newUser);
        user = userConverter.convertEntityToDTO(newUser);

        return user;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
