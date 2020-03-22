package org.csid.autobody.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import  java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserController(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UserDetails> getAll(){
        return Collections.singletonList(jdbcUserDetailsManager.loadUserByUsername("admin"));
    }

    @PostMapping
    public void changePassword(@RequestBody PasswordUpdate passwordUpdate){
        jdbcUserDetailsManager.changePassword(passwordUpdate.getOldPassword(),passwordEncoder.encode(passwordUpdate.getNewPassword()));

    }
}
