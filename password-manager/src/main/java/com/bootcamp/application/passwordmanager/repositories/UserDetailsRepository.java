package com.bootcamp.application.passwordmanager.repositories;

import com.bootcamp.application.passwordmanager.DTOs.UserRegistrationTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserRegistrationTable, Long> {

    Optional<UserDetails> findAllByUsername(String username);
}
