package com.ndroid.ppmtool.services;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<Map<String,String>> getMapValidationError(BindingResult result) {
        Map<String, String> errorMap = null;
        // check if errors are present
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            errorMap = new HashMap<>();
            //store all errors to map
            for (FieldError error : fieldErrors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        }
        return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
