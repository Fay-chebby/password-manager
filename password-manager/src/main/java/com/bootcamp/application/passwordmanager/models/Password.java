package com.bootcamp.application.passwordmanager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Password {
    @Id
    private String password;
    private String website;
}
