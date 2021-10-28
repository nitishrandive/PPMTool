package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.User;
import com.ndroid.ppmtool.exceptions.UserAlreadyExistsException;
import com.ndroid.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User newUser)
    {
        // check if password is as same as confirm password
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }
        catch (Exception ex){
            throw new UserAlreadyExistsException("UserName "+newUser.getUsername()+" Already Exists");
        }

    }
}
