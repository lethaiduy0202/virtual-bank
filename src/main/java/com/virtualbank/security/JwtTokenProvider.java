package com.virtualbank.security;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.virtualbank.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
  @Value("${security.jwt.expiry-lenght}")
  private long expiryTimeMilliseconds;
  @Value("${security.jwt.secret-key}")
  private String secretKey;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(Long userId, String username) {
    Claims claims = Jwts.claims().setSubject(username).setAudience(String.valueOf(userId));
    Date now = new Date();
    Date expiryTime = new Date(now.getTime() + expiryTimeMilliseconds);
    return Jwts.builder().setId(UUID.randomUUID().toString()).setIssuedAt(now).setIssuer(username)
        .setClaims(claims).setExpiration(expiryTime)
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
  }

  public String resolveToken(String token) {
    try {
      return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody()
          .getSubject();
    } catch (JwtException | IllegalArgumentException e) {
      log.error(e.getMessage());
      throw new UnauthorizedException("Expired or invalid JWT token");
    }
  }

}
