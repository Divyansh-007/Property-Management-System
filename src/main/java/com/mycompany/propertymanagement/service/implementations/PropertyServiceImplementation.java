package com.mycompany.propertymanagement.service.implementations;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
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


    @Override
    public PropertyDTO saveProperty(PropertyDTO property) {

        PropertyEntity newProperty = propertyConverter.convertDTOtoEntity(property);
        newProperty = propertyRepository.save(newProperty);
        property = propertyConverter.convertEntityToDTO(newProperty);

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
