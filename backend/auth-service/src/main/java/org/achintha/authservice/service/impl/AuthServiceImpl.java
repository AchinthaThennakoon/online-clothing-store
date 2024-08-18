package org.achintha.authservice.service.impl;

import org.achintha.authservice.entity.UserCredential;
import org.achintha.authservice.repository.UserCredentialRepository;
import org.achintha.authservice.service.AuthService;
import org.achintha.authservice.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "user added to the system";
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
