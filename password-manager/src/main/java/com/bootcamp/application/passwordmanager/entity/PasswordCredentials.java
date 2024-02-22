package com.bootcamp.application.passwordmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table()
public class PasswordCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String website;
    private String username;
    private String password;
    private String initializationVector;


}
