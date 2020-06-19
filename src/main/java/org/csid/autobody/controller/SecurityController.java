package org.csid.autobody.controller;

import org.csid.autobody.dto.UserDto;
import org.csid.autobody.dto.PasswordUpdate;
import org.csid.autobody.services.CustomUserDetailsService;
import org.csid.autobody.services.RoleService;
import org.csid.autobody.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import  java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/security")
public class SecurityController {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final RoleService roleService;

    public SecurityController(CustomUserDetailsService userDetailsService, UserService userService, RoleService roleService) {
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


    @GetMapping("{username}")
    public List<UserDetails> getAllByUsername(@PathVariable String username){
        return Collections.singletonList(userDetailsService.loadUserByUsername(username));
    }

    @PostMapping("update")
    public void changePassword(@RequestBody PasswordUpdate passwordUpdate){
        userService.changePassword(passwordUpdate.getOldPassword(),passwordUpdate.getNewPassword());
    }

    @PostMapping("register")
    public UserDto createUser(@RequestBody UserDto user){
        user.setStat(false);
        return DtoConverter.map(this.userService.save(user),UserDto.class);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

}
