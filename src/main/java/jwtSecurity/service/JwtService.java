package jwtSecurity.service;



import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	
	public String generateToken (String username , String role) {
		
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public Key getSignKey () {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
		
	}
	
	
	public String extractUsername (String token) {
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
		
	}
	
	private Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSignKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}
	
	
	public Boolean validateToken(String token, UserDetails userDetails) {
	    String username = extractUsername(token);
	    boolean isExpired = extractExpiration(token).before(new Date());
	    
	    return (username.equals(userDetails.getUsername()) && !isExpired);
	}


	private Date extractExpiration(String token) {
		Claims claims = extractAllClaims(token);
	    return claims.getExpiration();
		
	}
	
	public String extractRole(String token) {
	    Claims claims = extractAllClaims(token);
	    return claims.get("role", String.class);
	}
}
