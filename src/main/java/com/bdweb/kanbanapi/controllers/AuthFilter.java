package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.services.UserDetailsServiceImpl;
import com.bdweb.kanbanapi.utils.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
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

@Component
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private TokenUtil tokenUtil;
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("requestTokenHeader: "+ requestTokenHeader);
        String jwt = null;
        String username = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            logger.info("Token is a Bearer");
            jwt = requestTokenHeader.substring(7);
            System.out.println(requestTokenHeader);
            try {
                username = tokenUtil.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get token");
            } catch (ExpiredJwtException e) {
                System.out.println("Expired token");
            }
        } else {
            logger.warn("Token is not a Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("username != null");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("userDetails: " + userDetails.toString());
            System.out.println("userDetails authorities: "+ userDetails.getAuthorities().toString());
            if (tokenUtil.validateToken(jwt, userDetails)) {
                logger.info("Token is valid");
                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
