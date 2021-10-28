package com.ndroid.ppmtool.controller;

import com.ndroid.ppmtool.domain.User;
import com.ndroid.ppmtool.exceptions.UserAlreadyExistsException;
import com.ndroid.ppmtool.services.MapValidationErrorService;
import com.ndroid.ppmtool.services.UserService;
import com.ndroid.ppmtool.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserValidator userValidator;

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        userValidator.validate(user,result);

        ResponseEntity<Map<String,String>> errors = mapValidationErrorService.getMapValidationError(result);
        if(errors.getBody()!=null)
         return new ResponseEntity(errors.getBody(),HttpStatus.BAD_REQUEST);




    return  new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
    }

}
