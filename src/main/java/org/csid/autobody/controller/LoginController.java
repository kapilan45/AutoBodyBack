package org.csid.autobody.controller;

import com.auth0.jwt.JWT;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@RestController
@Slf4j
public class LoginController {

    public static final long EXPIRATION_TIME = 3_600_000; // 10 days
    public static final String HEADER_STRING = "cache-control";
	public static final String SECRET = "SecretKeyToGenJWTs";

	private AuthenticationManager authenticationManager;
	private UserService userService;

	public LoginController(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto req) {

		String username = req.getUsername(), password = req.getPassword();
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password, null);
		UserDto res = new UserDto();
		try {
			authenticationManager.authenticate(auth);
		} catch(AuthenticationException ex) {
			// failed to authenticate!
			return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
		}

		if (!auth.isAuthenticated()) {
			// failed to authenticate!
			return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
		}

		SecurityContextHolder.getContext().setAuthentication(auth);

		HttpHeaders httpHeaders = new HttpHeaders();
        // use JWT token ...
		httpHeaders.add(HEADER_STRING, GenerateToken(username));

        UserEntity user = userService.findByUsername(username);
        res.setRole(user.getRole().getRole());
		res.setUsername(user.getUsername());

        return new ResponseEntity<>(res, httpHeaders, HttpStatus.OK);
	}

	private String GenerateToken(String username) {
		return JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(HMAC512(SECRET.getBytes()));
	}

}
