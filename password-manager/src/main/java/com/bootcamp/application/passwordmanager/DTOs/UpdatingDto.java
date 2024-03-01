package com.bootcamp.application.passwordmanager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatingDto {
    private String password;
    private String Website;
}
