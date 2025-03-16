package com.udeafood.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "your secret key";

    public String getToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())  // Use the email as the subject
                .setIssuedAt(new Date())  // Emission date
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // Expiration date
                .signWith(getKey())  // Sign with the secret key
                .compact();  // Compact and return the token
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }



    public boolean isTokenValid(String token, String emailAccount) {
        final String email = getEmailFromToken(token);
        return (email.equals(emailAccount) && !isTokenExpired(token));
    }



    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())  // Use the secret key to validate
                .build()
                .parseClaimsJws(token)  // Decode the token
                .getBody();  // Returns the claims
    }



    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }



    public Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }



    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
