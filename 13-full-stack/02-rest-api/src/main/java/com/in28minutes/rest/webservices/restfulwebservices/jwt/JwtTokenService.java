package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generates a signed JWT token for the authenticated user.
     * <p>
     * The token includes standard claims such as issuer, issued time, expiration time,
     * subject (username), and a custom "scope" claim which contains the user's authorities
     * excluding roles that start with "ROLE".
     * The token is signed using the HS512 MAC algorithm.
     *
     * @param authentication the {@link Authentication} object containing user details and authorities
     * @return a signed JWT token as a {@link String}
     * @see org.springframework.security.core.Authentication
     * @see org.springframework.security.oauth2.jwt.JwtEncoder
     * @see org.springframework.security.oauth2.jwt.JwtClaimsSet
     * @see org.springframework.security.oauth2.jwt.JwtEncoderParameters
     */
    public String generateToken(Authentication authentication) {
        var now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.startsWith("ROLE"))
                .collect(Collectors.joining(" "));
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);

        return this.jwtEncoder.encode(encoderParameters).getTokenValue();
    }

//    public String generateToken(Authentication authentication) {
//
//        var scope = authentication
//                        .getAuthorities()
//                        .stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.joining(" "));
//
//        var claims = JwtClaimsSet.builder()
//                        .issuer("self")
//                        .issuedAt(Instant.now())
//                        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
//                        .subject(authentication.getName())
//                        .claim("scope", scope)
//                        .build();
//
//        return this.jwtEncoder
//                .encode(JwtEncoderParameters.from(claims))
//                .getTokenValue();
//    }
}


