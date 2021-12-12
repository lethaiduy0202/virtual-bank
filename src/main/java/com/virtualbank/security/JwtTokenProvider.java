package com.virtualbank.security;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.virtualbank.entity.Token;
import com.virtualbank.exceptions.UnauthorizedException;
import com.virtualbank.repositories.TokenRepository;
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

  @Autowired
  private TokenRepository tokenRepository;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(Long userId, String username) {
    Claims claims = Jwts.claims().setSubject(username).setAudience(String.valueOf(userId));
    Date now = new Date();
    Date expiryTime = new Date(now.getTime() + expiryTimeMilliseconds);
    String tokenId = UUID.randomUUID().toString();
    String token = Jwts.builder().setId(tokenId).setIssuedAt(now).setIssuer(username)
        .setClaims(claims).setExpiration(expiryTime)
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
    Token tokenResp = tokenRepository.findByUserId(userId);
    if (tokenResp == null) {
      tokenRepository
          .save(Token.builder().tokenId(tokenId).isRevoked(false).userId(userId).build());
    } else {
      tokenResp.setTokenId(tokenId);
      tokenResp.setRevoked(false);
      tokenRepository.save(tokenResp);
    }
    return token;

  }

  public String resolveToken(String token) {
    try {
      Long userId = Long.parseLong(Jwts.parser().setSigningKey(secretKey.getBytes())
          .parseClaimsJws(token).getBody().getAudience());
      Token tokenResp = tokenRepository.findByUserId(userId);
      if (tokenResp != null && !tokenResp.isRevoked())
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody()
            .getSubject();
      else {
        throw new UnauthorizedException("Expired or invalid JWT token");
      }
    } catch (JwtException | IllegalArgumentException e) {
      log.error(e.getMessage());
      throw new UnauthorizedException("Expired or invalid JWT token");
    }
  }

  public void revokeToken(Long userId) {
    Token token = tokenRepository.findByUserId(userId);
    if (token != null) {
      token.setRevoked(true);
      tokenRepository.save(token);
    }
  }

}
