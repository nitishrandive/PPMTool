package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.User;
import com.ndroid.ppmtool.repositories.UserRepository;
import com.ndroid.ppmtool.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User Not Found");
        return user;
    }

    @Transactional
    public UserDetails loadUserByUserId(Long id){
        User user  = userRepository.getUserByUserId(id);
        if(user==null)
            throw  new UsernameNotFoundException("User not Found");
        return  user;
    }
}
