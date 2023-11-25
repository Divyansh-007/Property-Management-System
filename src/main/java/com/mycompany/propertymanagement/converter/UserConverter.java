package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertDTOtoEntity (UserDTO user){
        UserEntity u = new UserEntity();

        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setPhone(user.getPhone());

        return u;
    }

    public UserDTO convertEntityToDTO(UserEntity user){
        UserDTO u = new UserDTO();

        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setPhone(user.getPhone());


        return u;
    }
}
