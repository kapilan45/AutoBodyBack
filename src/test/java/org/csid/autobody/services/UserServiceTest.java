package org.csid.autobody.services;

import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.RoleEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.AnnonceRepository;
import org.csid.autobody.repository.ImageRepository;
import org.csid.autobody.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final AnnonceRepository annonceRepository = mock(AnnonceRepository.class);
    private final ImageRepository imageRepository = mock(ImageRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final RoleService roleService = mock(RoleService.class);
    private final UserService service = new UserService(
            passwordEncoder, userRepository, annonceRepository, imageRepository, roleService);

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void publicRegistrationAlwaysAssignsUserRoleAndDoesNotReturnHash() {
        RoleEntity userRole = new RoleEntity();
        userRole.setRole("ROLE_USER");
        when(roleService.findByRoleName("ROLE_USER")).thenReturn(userRole);
        when(passwordEncoder.encode("password-123")).thenReturn("bcrypt-hash");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto request = new UserDto();
        request.setUsername("new-user");
        request.setPassword("password-123");
        request.setMail("new@example.test");
        request.setRole("ROLE_ADMIN");
        request.setStat(true);

        UserDto response = service.save(request);

        assertEquals("ROLE_USER", response.getRole());
        assertNull(response.getPassword());
        var saved = org.mockito.ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(saved.capture());
        assertEquals("ROLE_USER", saved.getValue().getRole().getRole());
        assertEquals("bcrypt-hash", saved.getValue().getPassword());
        assertFalse(saved.getValue().isStat());
    }

    @Test
    void duplicateUsernameIsRejected() {
        when(userRepository.existsByUsernameIgnoreCase("existing")).thenReturn(true);
        UserDto request = new UserDto();
        request.setUsername("existing");
        request.setPassword("password-123");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.save(request));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void passwordChangeUsesOnlyTheAuthenticatedPrincipal() {
        UserEntity account = new UserEntity();
        account.setId(12L);
        account.setUsername("alice");
        account.setPassword("old-hash");
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated("alice", null, List.of()));
        when(userRepository.findByUsername("alice")).thenReturn(account);
        when(passwordEncoder.matches("old-password", "old-hash")).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("new-hash");

        service.changePassword("old-password", "new-password");

        verify(userRepository).changePassword("new-hash", 12L);
    }

    @Test
    void passwordChangeRejectsWrongCurrentPassword() {
        UserEntity account = new UserEntity();
        account.setId(12L);
        account.setUsername("alice");
        account.setPassword("old-hash");
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated("alice", null, List.of()));
        when(userRepository.findByUsername("alice")).thenReturn(account);
        when(passwordEncoder.matches("wrong-password", "old-hash")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.changePassword("wrong-password", "new-password"));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(userRepository, never()).changePassword(any(), any());
    }
}
