package com.sergey.demoprojectspringboot.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtTokenProvider {

    private final String jwtSecret = "ksdjbvlerbvleiaurhfliuefgewriu37845ty7cno8734tycn8yfnsirefhkhireufh"; // Must be a minimum 32 bytes
    private final long jwtLifeTime = 1800000; // 30 minutes //1800000
    private static final String ROLES_CLAIM = "roles";
    /**
     * Create new JWT token for user with username
     * @param username
     * @return String variable with a complete JWT token
     */
    public String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtLifeTime);
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validation of JWT token, received from User
     * @param token
     * @return boolean variable with a result of validation. True, if the token is valid, otherwise will be thrown an exception:
     * @SignatureException - Invalid JWT Signature
     * @MalformedJwtException - Invalid JWT token
     * @ExpiredJwtException - Expired Jwt token
     * @UnsupportedJwtException - Unsupported Jwt token
     * @IllegalArgumentException - JWT claims is empty
     */
    public boolean validateToken(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (JwtException e) {
            throw new InvalidJwtException("Invalid JWT token: " + e.getMessage());
        }
        return true;
    }

    /**
     * Get username from JWT token
     * @param token JWT token
     * @return - String variable with username
     */
    public String getUsernameFromJwt(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        Claims claimsPayload = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claimsPayload.getSubject();
    }



}
