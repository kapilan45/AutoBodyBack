package org.csid.autobody.controller;

import org.csid.autobody.dto.PasswordUpdate;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getCurrentUser() {
        return Collections.singletonList(userService.getCurrentUser());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("all")
    public List<List<UserDto>> getAllUsers() {
        return userService.findAllUsernames().stream()
                .map(userService::findByUsername)
                .map(DtoConverter::toSafeUserDto)
                .map(Collections::singletonList)
                .toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or authentication.name == #username")
    @GetMapping("{username}")
    public List<UserDto> getByUsername(@PathVariable String username) {
        return Collections.singletonList(DtoConverter.toSafeUserDto(userService.findByUsername(username)));
    }

    @PostMapping("update")
    public void changePassword(@RequestBody PasswordUpdate passwordUpdate) {
        if (passwordUpdate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password update is required");
        }
        userService.changePassword(passwordUpdate.getOldPassword(), passwordUpdate.getNewPassword());
    }

    @PostMapping("register")
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.save(user);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
