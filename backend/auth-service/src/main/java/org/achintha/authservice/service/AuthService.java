package org.achintha.authservice.service;

import org.achintha.authservice.entity.UserCredential;

public interface AuthService {
    public String saveUser(UserCredential userCredential);
    public String generateToken(String username);
    public void validateToken(String token);
}
