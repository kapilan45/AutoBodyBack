package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.RoleEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.AnnonceRepository;
import org.csid.autobody.repository.ImageRepository;
import org.csid.autobody.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AnnonceRepository annonceRepository;
    private final ImageRepository imageRepository;
    private final RoleService roleService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                       AnnonceRepository annonceRepository, ImageRepository imageRepository,
                       RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.annonceRepository = annonceRepository;
        this.imageRepository = imageRepository;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required");
        }
        return findByUsername(authentication.getName());
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (newPassword == null || newPassword.isBlank()
                || newPassword.getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password is required and must not exceed 72 UTF-8 bytes");
        }
        UserEntity currentUser = getAuthenticatedUser();
        if (oldPassword == null || !passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
        }
        userRepository.changePassword(passwordEncoder.encode(newPassword), currentUser.getId());
    }

    @Transactional(readOnly = true)
    public List<String> findAllUsernames() {
        return userRepository.findAllUsername();
    }

    public void deleteUser(String username) {
        UserEntity deletingUser = findByUsername(username);
        if (annonceRepository.existsByUser_Id(deletingUser.getId())
                || imageRepository.existsByOwner_Id(deletingUser.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User has announcements or images; remove those explicitly before deleting the account");
        }
        userRepository.delete(deletingUser);
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        return DtoConverter.map(getAuthenticatedUser(), UserDto.class);
    }

    public UserDto save(UserDto userDto) {
        if (userDto == null || userDto.getUsername() == null || userDto.getUsername().isBlank()
                || userDto.getUsername().length() > 75 || userDto.getPassword() == null
                || userDto.getPassword().isBlank()
                || userDto.getPassword().getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and a valid password are required");
        }
        if (userDto.getMail() != null && userDto.getMail().length() > 150) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email address is too long");
        }
        if (userRepository.existsByUsernameIgnoreCase(userDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already registered");
        }
        if (userDto.getMail() != null && !userDto.getMail().isBlank()
                && userRepository.existsByMailIgnoreCase(userDto.getMail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email address is already registered");
        }

        UserEntity userEntity = DtoConverter.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setStat(false);

        RoleEntity role = roleService.findByRoleName("ROLE_USER");
        if (role == null) {
            throw new IllegalStateException("ROLE_USER must exist before registering users");
        }
        userEntity.setRole(role);
        return DtoConverter.map(userRepository.save(userEntity), UserDto.class);
    }
}
