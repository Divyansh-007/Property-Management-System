package com.mycompany.propertymanagement.service.implementations;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO user) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel err1 = new ErrorModel();
            err1.setCode("ALREADY_REGISTERED");
            err1.setMessage("You are already registered. Please login to continue");

            errors.add(err1);
            throw new BusinessException(errors);
        }

        UserEntity newUser = userConverter.convertDTOtoEntity(user);
        newUser = userRepository.save(newUser);
        user = userConverter.convertEntityToDTO(newUser);

        return user;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO user = null;
        Optional<UserEntity> userEntity = userRepository.findByEmailAndPassword(email,password);

        if(userEntity.isPresent()){
            user = userConverter.convertEntityToDTO(userEntity.get());
        }else{
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel err1 = new ErrorModel();
            err1.setCode("INVALID_LOGIN");
            err1.setMessage("Invalid email or password");

            errors.add(err1);

            throw new BusinessException(errors);
        }

        return user;
    }
}
