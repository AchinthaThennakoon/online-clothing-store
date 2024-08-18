package org.achintha.authservice.repository;

import org.achintha.authservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
}
