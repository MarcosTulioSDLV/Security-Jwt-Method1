package com.springboot.securityjwtmethod1.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springboot.securityjwtmethod1.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;


    public String generateToken(User user){
        Algorithm algorithm= Algorithm.HMAC256(secretKey);
        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("id: ",user.getId())//Add another claim
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new JWTCreationException("Error generating JWT Token",e);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.ofHours(-5));//For local time of Colombia
    }


    public String validateToken(String token){
        Algorithm algorithm= Algorithm.HMAC256(secretKey);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            //return "";
            throw new JWTVerificationException("Error validating JWT token: "+e.getMessage());
        }
    }

}
