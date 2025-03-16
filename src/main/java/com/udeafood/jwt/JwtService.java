package com.udeafood.jwt;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final SecretKey key = Keys.hmacShaKeyFor(ApplicationConstants.SECRET_KEY_PASSWORD.getBytes());



    public String getToken(String email, String roles) {
        return Jwts.builder()
                .setSubject(email)  // Use the email as the subject
                .claim("roles", roles)
                .setIssuedAt(new Date())  // Emission date
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.TOKEN_EXPIRATION_TIME))  // Expiration date
                .signWith(key)  // Sign with the secret key
                .compact();  // Compact and return the token
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
