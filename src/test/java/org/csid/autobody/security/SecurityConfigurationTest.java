package org.csid.autobody.security;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityConfigurationTest {

    private static final String TEST_SECRET = Base64.getEncoder().encodeToString(new byte[32]);

    @Test
    void jwtRoundTripRestoresSubjectAndRoleAuthorities() {
        SecurityConfiguration configuration = new SecurityConfiguration();
        var key = configuration.jwtSecretKey(TEST_SECRET);
        var encoder = configuration.jwtEncoder(key);
        var decoder = configuration.jwtDecoder(key);
        Instant now = Instant.now();

        String value = encoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                JwtClaimsSet.builder()
                        .subject("alice")
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(300))
                        .claim("roles", List.of("ROLE_USER"))
                        .build())).getTokenValue();

        Authentication authentication = configuration.jwtAuthenticationConverter()
                .convert(decoder.decode(value));

        assertEquals("alice", authentication.getName());
        assertTrue(authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void bearerResolverAcceptsLegacyRawTokensAndBearerTokens() {
        var resolver = new SecurityConfiguration().bearerTokenResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.addHeader("Authorization", "signed.jwt.value");
        assertEquals("signed.jwt.value", resolver.resolve(request));

        request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer signed.jwt.value");
        assertEquals("signed.jwt.value", resolver.resolve(request));
    }

    @Test
    void corsRequiresConfiguredExplicitOriginsAndDoesNotAllowCredentials() {
        var source = new SecurityConfiguration().corsConfigurationSource("");
        var cors = source.getCorsConfiguration(new MockHttpServletRequest());

        assertTrue(cors.getAllowedOrigins().isEmpty());
        assertFalse(cors.getAllowCredentials());
        assertTrue(cors.getExposedHeaders().contains("cache-control"));
        assertThrows(IllegalArgumentException.class,
                () -> new SecurityConfiguration().corsConfigurationSource("*"));
    }

    @Test
    void jwtSecretMustBeBase64AndAtLeast32Bytes() {
        SecurityConfiguration configuration = new SecurityConfiguration();

        assertThrows(IllegalStateException.class, () -> configuration.jwtSecretKey("not-base64"));
        assertThrows(IllegalStateException.class,
                () -> configuration.jwtSecretKey(Base64.getEncoder().encodeToString(new byte[31])));
    }
}
