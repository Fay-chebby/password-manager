package com.bootcamp.application.passwordmanager.ServiceLayer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements  JwtService{

    private final String SECRETE_KEY = "9d5a517ddceaccd8870a94d22188a4525daeb5d7028d413de938cc3ea4caea41";
    @Override
    public String generateJwtToken(String username) {
        log.info("Generating a JTW token");
        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 10)))
                .signWith(getSecreteKey())
                .compact();
    }

    @Override
    public Boolean isValid(String jwtToken, UserDetails userDetails) {
        log.info("Checking validity of the jwt token");
        return extractUsername(jwtToken).equals(userDetails.getUsername())
                &&
                !isExpired(jwtToken);
    }


    private Boolean isExpired(String jwtToken) {
        log.info("Checking whether the jwt is expired.");
        Date expiration = extractClaim(jwtToken, Claims::getExpiration);
        return expiration.before(new Date(System.currentTimeMillis()));
    }


    private Date extractExpiration(String jwtToken) {
        log.info("Extracting expiration date.");
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    @Override
    public String extractUsername(String jwtToken) {
        log.info("extracting username.");
        return extractClaim(jwtToken, Claims::getSubject);
    }


    private Claims parseAllClaims(String jwtToken) {
        log.info("extracting all claims.");
        return Jwts
                .parser()
                .verifyWith(getSecreteKey())
                .build()
                .parseEncryptedClaims(jwtToken)
                .getPayload();
    }


    private  <T> T extractClaim(String jwtToken, Function<Claims, T> getClaim) {
        log.info("Generic function for extracting specific claim");
        Claims claims = parseAllClaims(jwtToken);
        return getClaim.apply(claims);
    }

    private SecretKey getSecreteKey() {
        log.info("Generating a secrete key");
        byte[] bytes = Decoders.BASE64URL.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
