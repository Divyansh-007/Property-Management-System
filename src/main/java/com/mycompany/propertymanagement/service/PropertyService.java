package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO property);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getAllPropertiesForUser(Long userId);
    PropertyDTO updateProperty(PropertyDTO newPropertyDetails,Long propertyId);
    PropertyDTO updatePropertyDescription(PropertyDTO newPropertyDetails,Long propertyId);
    PropertyDTO updatePropertyPrice(PropertyDTO newPropertyDetails,Long propertyId);
    void deleteProperty(Long propertyId);

}
