package org.achintha.authservice.service.impl;

import org.achintha.authservice.config.CustomUserDetails;
import org.achintha.authservice.entity.UserCredential;
import org.achintha.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private  UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential = userCredentialRepository.findByName(username);
        return userCredential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found with name :"+ username));
    }
}
