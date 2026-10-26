package org.csid.autobody.services;

import org.csid.autobody.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UsernameNotFoundException("Username is required");
        }

        UserEntity user;
        try {
            user = userService.findByUsername(username);
        } catch (org.springframework.web.server.ResponseStatusException exception) {
            throw new UsernameNotFoundException("User not found", exception);
        }
        if (user.getRole() == null || user.getRole().getRole() == null) {
            throw new UsernameNotFoundException("User role is not configured");
        }
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().getRole())))
                .build();
    }
}
