package com.bootcamp.application.passwordmanager.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.crypto.SealedObject;
import java.io.Serializable;

@Data
@Entity
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long managedId;
    private String password;
    private String website;
    private String username;


}
