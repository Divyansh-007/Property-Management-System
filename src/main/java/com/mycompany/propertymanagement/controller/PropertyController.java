package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO property){
        property = propertyService.saveProperty(property);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(property, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        List<PropertyDTO> properties = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(properties,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO newPropertyDetails, @PathVariable Long propertyId){
        PropertyDTO updatedProperty = propertyService.updateProperty(newPropertyDetails,propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(updatedProperty, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO newPropertyDetails, @PathVariable Long propertyId){
        PropertyDTO updatedProperty = propertyService.updatePropertyDescription(newPropertyDetails,propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(updatedProperty, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO newPropertyDetails, @PathVariable Long propertyId){
        PropertyDTO updatedProperty = propertyService.updatePropertyPrice(newPropertyDetails,propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(updatedProperty, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deletePropertyPrice(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
