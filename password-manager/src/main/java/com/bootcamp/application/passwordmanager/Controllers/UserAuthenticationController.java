package com.bootcamp.application.passwordmanager.Controllers;

import com.bootcamp.application.passwordmanager.ServiceLayer.AuthenticationService;
import com.bootcamp.application.passwordmanager.models.AuthenticationResponseModel;
import com.bootcamp.application.passwordmanager.models.LoginModel;
import com.bootcamp.application.passwordmanager.models.RegistrationModel;
import com.bootcamp.application.passwordmanager.models.ResponseModel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v0/")
public class UserAuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public UserAuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseModel> registerUser(
            @RequestBody @Valid RegistrationModel registrationModel
            ){
        log.info("Received a request to register a new user.");
        return new ResponseEntity<>(
                authenticationService.registerUser(registrationModel),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseModel> userLogin(
            @RequestBody LoginModel loginModel
            ){
        log.info("received a request for user login");
        return new ResponseEntity<>(authenticationService.login(loginModel),
                HttpStatus.OK);
    }
}
