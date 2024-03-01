package com.bootcamp.application.passwordmanager.ServiceLayer;

import com.bootcamp.application.passwordmanager.CustomExceptions.UserExistException;
import com.bootcamp.application.passwordmanager.CustomExceptions.WeakPasswordException;
import com.bootcamp.application.passwordmanager.models.UserRegistrationTable;
import com.bootcamp.application.passwordmanager.models.AuthenticationResponseModel;
import com.bootcamp.application.passwordmanager.models.LoginModel;
import com.bootcamp.application.passwordmanager.models.RegistrationModel;
import com.bootcamp.application.passwordmanager.repositories.UserDetailsRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtServiceImpl;

    private final UserDetailsRepository userDetailsRepository;

    private AuthenticationResponseModel authenticationResponseModel;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(
            AuthenticationManager authenticationManager,
            JwtServiceImpl jwtServiceImpl,
            UserDetailsRepository userDetailsRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtServiceImpl = jwtServiceImpl;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeModel()
    {
        authenticationResponseModel = new AuthenticationResponseModel();
    }

    public AuthenticationResponseModel registerUser(RegistrationModel registrationModel) {

        /*Check password strength*/
        if (!isPasswordStrong(registrationModel.getPassword())) {
            throw new WeakPasswordException("Password Does Not Meet Requirements");
        }

        /*Check if the username Already exists*/
        if(userDetailsRepository.findAllByUsername(registrationModel.getUsername()).isPresent()) {
            log.error("Username already Exist");
            throw new UserExistException("The Username is already taken");
        }

        /*Moving forward to insert the new user*/
        log.info("Registering the user.");
        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();
        userRegistrationTable.setUsername(registrationModel.getUsername());
        userRegistrationTable.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
        userRegistrationTable.setEmail(registrationModel.getEmail());

        userDetailsRepository.save(userRegistrationTable);
        log.info("User saved successfully");

        /*Generating the jwt*/
        String token = jwtServiceImpl.generateJwtToken(userRegistrationTable);

        /*preparing the user response.*/
        authenticationResponseModel.setJwtToken(token);
        authenticationResponseModel.setDate(new Date());
        authenticationResponseModel.setMessage("User added Successfully");
        authenticationResponseModel.setHttpStatus(HttpStatus.CREATED);

        return authenticationResponseModel;
    }

    private boolean isPasswordStrong(String password) {
        log.info("Checking whether the password matches specifications");
        Pattern pattern =
                Pattern.compile("^(?=.*\\p{javaLowerCase})(?=.*\\p{javaUpperCase})(?=.*\\p{javaDigit})(?=.*\\p{Punct}).*$");

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public AuthenticationResponseModel login(LoginModel loginModel) throws BadCredentialsException {
        log.info("Forwarding the login request.");

        /*Authentication manager.*/
        log.info("Forwarding user authentication.");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(),
                        loginModel.getPassword()
                )
        );

        /*Getting a userDetails object*/
        log.info("User details");
        UserDetails userDetails =
                userDetailsRepository.findAllByUsername(loginModel.getUsername()).orElseThrow();

        /*Generating a new jwt for the user.*/
        log.info("generating the jwt.");
        String token = jwtServiceImpl.generateJwtToken(userDetails);

        /*Preparing the user response.*/
        authenticationResponseModel.setDate(new Date());
        authenticationResponseModel.setMessage("User Login Authentication Successful");
        authenticationResponseModel.setHttpStatus(HttpStatus.OK);
        authenticationResponseModel.setJwtToken(token);

        return authenticationResponseModel;
    }
}
