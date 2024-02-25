package com.bootcamp.application.passwordmanager.ServiceLayer;

import com.bootcamp.application.passwordmanager.repositories.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsImplService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsImplService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting user by username");
        return userDetailsRepository.findAllByUsername(username).orElseThrow(
                () ->
                {
                    log.error("User Not Found");
                    return new UsernameNotFoundException("The Username Does Not Match AnyUser.");
                }
        );
    }
}
