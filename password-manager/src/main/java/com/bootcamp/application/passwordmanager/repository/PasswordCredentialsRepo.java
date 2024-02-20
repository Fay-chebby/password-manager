package com.bootcamp.application.passwordmanager.repository;

import com.bootcamp.application.passwordmanager.entity.PasswordCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordCredentialsRepo extends JpaRepository<PasswordCredentials,Long> {

}
