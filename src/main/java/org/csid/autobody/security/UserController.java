package org.csid.autobody.security;

import org.csid.autobody.entity.RoleEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.services.CustomUserDetailsService;
import org.csid.autobody.services.RoleService;
import org.csid.autobody.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import  java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
/*
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
    */

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final RoleService roleService;

    public UserController(CustomUserDetailsService userDetailsService, UserService userService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public List<UserDetails> getAdmin(){
        return Collections.singletonList(userDetailsService.loadUserByUsername("admin"));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("all")
    public List<List<UserDetails>> getAllUsers(){

        List<List<UserDetails>> allUsersList = new ArrayList<>();
        List<String> usernamesList = userService.findAllUsernames();

        usernamesList.forEach( username -> allUsersList.add(Collections.singletonList(userDetailsService.loadUserByUsername(username))));

        return allUsersList;
    }



    @GetMapping("me")
    public List<UserDetails> getCurrentUser(){

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDetails) {
            String username = ((UserDetails) user).getUsername();
            return Collections.singletonList(userDetailsService.loadUserByUsername(username));
        } else {
            String username = user.toString();
            return Collections.singletonList(userDetailsService.loadUserByUsername(username));
        }


    }

    @GetMapping("{username}")
    public List<UserDetails> getAllByUsername(@PathVariable String username){
        return Collections.singletonList(userDetailsService.loadUserByUsername(username));
    }

    @PostMapping
    public void changePassword(@RequestBody PasswordUpdate passwordUpdate){
        userService.changePassword(passwordUpdate.getOldPassword(),passwordUpdate.getNewPassword());
    }

    @PostMapping("create")
    public void createUser(@RequestBody UserEntity user){
        RoleEntity role = roleService.findByRoleName("ROLE_USER");
        user.setPassword(userService.getPasswordEncoder().encode(user.getPassword()));

        if(user.getRole() == null){
            user.setRole(role);
        }

        userService.getUserRepository().save(user);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @

}
