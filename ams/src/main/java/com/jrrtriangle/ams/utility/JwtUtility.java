package com.jrrtriangle.ams.utility;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {
    static final long JWT_TOKEN_VALIDITY=5*60*60;
    @Value(("${jwt.secret}"))
    private String secretKey;
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }
    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver) {

        try {
            final Claims claims = getAllClaimsFromToken(token);

            return claimResolver.apply(claims);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private Claims getAllClaimsFromToken(String token) {
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Boolean isTokenExpired(String token){
       try{
           final Date expiration = getExpirationDateFromToken(token);
           return expiration.before(new Date());
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }



    public String genrateToken(UserDetails userDetails){
        try{
            Map<String,Object> claims = new HashMap<>();
            return doGenerateToken(claims,userDetails.getUsername());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private String doGenerateToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }
    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        boolean isExpired=isTokenExpired(token);
        if(isExpired){
            throw new RuntimeException("Toke has been expired");
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
