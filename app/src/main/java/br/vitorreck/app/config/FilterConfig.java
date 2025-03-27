package br.vitorreck.app.config;

import br.vitorreck.app.services.AuthorizationService;
import br.vitorreck.app.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterConfig extends OncePerRequestFilter {

  private final AuthorizationService authorizationService;
  private final JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = this.recoverToken(request);

    if(token != null){
      String email = jwtUtils.getEmailFromJWT(token);
      UserDetails user = authorizationService.loadUserByUsername(email);

      var authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request){
    var authHeader = request.getHeader("Authorization");
    if(authHeader != null)
      return authHeader.replace("Bearer ", "");

    return null;
  }
}
