package com.bootcamp.application.passwordmanager.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserManagedLinker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long managedId;


}
