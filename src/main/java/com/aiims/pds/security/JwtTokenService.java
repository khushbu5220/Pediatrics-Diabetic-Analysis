package com.aiims.pds.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {

	private static final String SECRET_KEY = "93b3b8ca2d348bd8f8df8a27d099f0d42104199199fe639da9b311579750ee77";

	//retrieve username from jwt token
	//getUsernameFromToken
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    //getExpirationDateFromToken
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    //for retrieving any information from token we will need the secret key
    //getAllClaimsFromToken
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    //isTokenExpired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) 
    {
        return generateToken(new HashMap<>(), userDetails);
    }

    //creating token
    //doGenerateToken
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) 
    {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
		    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		    .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    //validate token
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token).toLowerCase();
        return (username.equals(userDetails.getUsername().toLowerCase()) && !isTokenExpired(token));
    }

//	public String getUsernameFromToken(String jwtToken) {
//		String username = extractUsername(jwtToken);
//		return username.toLowerCase();
//	}
	
	private Key getSignInKey()
	{
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
