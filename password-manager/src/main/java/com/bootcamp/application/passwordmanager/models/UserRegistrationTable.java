package com.bootcamp.application.passwordmanager.models;

import com.bootcamp.application.passwordmanager.DTOs.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserRegistrationTable implements UserDetails {

    @Id
 /*   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;*/

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private Role role;

    /*Setting the default role for users.*/
    @PrePersist
    public void setDefault()
    {
        if(role == null)
        {
            this.role = Role.USER;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
