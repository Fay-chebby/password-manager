package com.bootcamp.application.passwordmanager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.crypto.SealedObject;
import java.io.Serializable;

@Data
@Entity
public class Password implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String website;
    private SealedObject encryptedPassword;

    public void setEncryptedPassword(SealedObject encryptedObject) {
    }
}
