package org.csid.autobody.controller;

import org.csid.autobody.dto.UserDto;
import org.csid.autobody.services.SecurityService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/control")
public class SecurityController {

    private SecurityService securityService;

    @PostMapping("/register")
    public UserDto registration(@RequestBody UserDto user){

        return securityService.registration(user);
    }
}
