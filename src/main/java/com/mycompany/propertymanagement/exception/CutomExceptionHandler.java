package com.mycompany.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CutomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        System.out.println("Field Validation Exception is thrown");
        List<ErrorModel> errors = new ArrayList<>();
        ErrorModel err = null;
        List<FieldError> fieldErrors = manv.getFieldErrors();

        for(FieldError fe: fieldErrors){
            err = new ErrorModel();
            err.setCode(fe.getField());
            err.setMessage(fe.getDefaultMessage());
            errors.add(err);
        }

        return new ResponseEntity<List<ErrorModel>>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        System.out.println("Business Exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
