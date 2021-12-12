package com.virtualbank.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import com.virtualbank.exceptions.UnauthorizedException;
import com.virtualbank.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
  private static final String STR_BEARER = "Bearer";

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private VirtualBankUserDetailService userDetailsService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (bearerToken != null && bearerToken.contains(STR_BEARER)) {
        String token = StringUtils.removeLetters(bearerToken, STR_BEARER, "").trim();
        String email = tokenProvider.resolveToken(token);
        if (email != null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userDetails, null,
                  userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
      
    } catch (UnauthorizedException e) {
     SecurityContextHolder.clearContext();
     log.error(e.getMessage());
     throw new UnauthorizedException("Expired or invalid JWT token");
    }
    filterChain.doFilter(request, response);
  }

}
