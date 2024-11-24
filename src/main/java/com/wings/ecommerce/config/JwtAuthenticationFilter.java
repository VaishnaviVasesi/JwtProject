package com.wings.ecommerce.config;

import com.wings.ecommerce.models.User;
import com.wings.ecommerce.service.UserAuthService;
import com.wings.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private UserAuthService authService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader("JWT").substring(7);
        User user = UserUtil.getLoggedInUser();

        if(user != null && !jwtUtil.isValidToken(jwt, user)){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.authService.loadUserByUsername(user.getUsername());
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
