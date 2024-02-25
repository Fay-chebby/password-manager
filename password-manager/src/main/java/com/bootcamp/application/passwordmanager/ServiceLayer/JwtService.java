package com.bootcamp.application.passwordmanager.ServiceLayer;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {

    String generateJwtToken(String username);

    Boolean isValid(String jwtToken, UserDetails userDetails);


    String extractUsername(String jwtToken);
}
