package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    public PropertyEntity convertDTOtoEntity (PropertyDTO property){
        PropertyEntity p = new PropertyEntity();

        p.setTitle(property.getTitle());
        p.setDescription(property.getDescription());
        p.setAddress(property.getAddress());
        p.setPrice(property.getPrice());

        return p;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity property){
        PropertyDTO p = new PropertyDTO();

        p.setId(property.getId());
        p.setTitle(property.getTitle());
        p.setDescription(property.getDescription());
        p.setAddress(property.getAddress());
        p.setPrice(property.getPrice());

        return p;
    }
}
