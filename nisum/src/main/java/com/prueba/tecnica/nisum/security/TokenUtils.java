package com.prueba.tecnica.nisum.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static String  ACCESS_TOKEN_SECRET = "aAAsss223sdaASD2323Asdasdasda223";
    private static Long ACCESS_TOKEN_VALIDITY_SECONDS = 180L;


    public static String createToken(String name, String email){
        long expirationTime =  ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> data = new HashMap<>();
        data.put("lastLogin", new Date());

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(data)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication (String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }

    public static Claims getClaimsToken (String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            return null;
        }
    }
}
