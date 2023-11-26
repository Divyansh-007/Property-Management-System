package com.mycompany.propertymanagement.service.implementations;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Same as @Service
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImplementation implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private UserRepository userRepository;


    @Override
    public PropertyDTO saveProperty(PropertyDTO property) {

        Optional<UserEntity> user = userRepository.findById(property.getUserId());

        if(user.isPresent()){
            PropertyEntity newProperty = propertyConverter.convertDTOtoEntity(property);
            newProperty.setUser(user.get());
            newProperty = propertyRepository.save(newProperty);
            property = propertyConverter.convertEntityToDTO(newProperty);

        }else{
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel err1 = new ErrorModel();
            err1.setCode("USER_ID_NOT_FOUND");
            err1.setMessage("User was not found");

            errors.add(err1);
            throw new BusinessException(errors);
        }

        return property;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> properties = new ArrayList<>();
        for (PropertyEntity p : propertyEntities) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(p);
            properties.add(dto);
        }

        return properties;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> propertyEntities = (List<PropertyEntity>) propertyRepository.findAllByUserId(userId);
        List<PropertyDTO> properties = new ArrayList<>();
        for (PropertyEntity p : propertyEntities) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(p);
            properties.add(dto);
        }

        return properties;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO newPropertyDetails, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO updatedProperty = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity p = optionalPropertyEntity.get();
            p.setTitle(newPropertyDetails.getTitle());
            p.setDescription(newPropertyDetails.getDescription());
            p.setAddress(newPropertyDetails.getAddress());
            p.setPrice(newPropertyDetails.getPrice());

            updatedProperty = propertyConverter.convertEntityToDTO(p);
            propertyRepository.save(p);
        }

        return updatedProperty;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO newPropertyDetails, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO updatedProperty = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity p = optionalPropertyEntity.get();
            p.setDescription(newPropertyDetails.getDescription());

            updatedProperty = propertyConverter.convertEntityToDTO(p);
            propertyRepository.save(p);
        }

        return updatedProperty;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO newPropertyDetails, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO updatedProperty = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity p = optionalPropertyEntity.get();
            p.setPrice(newPropertyDetails.getPrice());

            updatedProperty = propertyConverter.convertEntityToDTO(p);
            propertyRepository.save(p);
        }

        return updatedProperty;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
