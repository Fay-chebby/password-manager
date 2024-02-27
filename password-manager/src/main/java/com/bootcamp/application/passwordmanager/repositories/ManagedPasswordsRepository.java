package com.bootcamp.application.passwordmanager.repositories;

import com.bootcamp.application.passwordmanager.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagedPasswordsRepository extends JpaRepository<Password,Long> {
    Password findAllByUsername(String username);
}
