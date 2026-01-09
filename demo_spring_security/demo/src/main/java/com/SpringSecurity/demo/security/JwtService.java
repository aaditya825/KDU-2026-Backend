package com.SpringSecurity.demo.security;
import com.SpringSecurity.demo.config.JwtProperties;
import com.SpringSecurity.demo.model.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties props;
    private final Key signingKey;

    public JwtService(JwtProperties props) {
        this.props = props;

        // Secret must be sufficiently long for HS256 (>= 256 bits).
        // For learning: keep in application.yml, but in real systems use a secrets manager.
        byte[] keyBytes = props.secret().getBytes(StandardCharsets.UTF_8);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String issueAccessToken(AppUser user) {
        Instant now = Instant.now();
        Instant exp = now.plus(props.accessTokenMinutes(), ChronoUnit.MINUTES);

        return Jwts.builder()
                .setIssuer(props.issuer())
                .setSubject(user.getUserName())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                // Put roles into a claim for authorization decisions
                .claim("roles", user.getRoles())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parseAndValidate(String token) {
        // This verifies signature + exp + structure
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .requireIssuer(props.issuer())
                .build()
                .parseClaimsJws(token);
    }
}
