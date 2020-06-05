package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private UserRepository userRepository;
    private UserService userService;

    public SecurityService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserDto registration(UserDto user){
        // TODO Check verification (mail, username)
        // return userService.save(DtoConverter.map(user, UserEntity.class));
        return null;
    }
}
