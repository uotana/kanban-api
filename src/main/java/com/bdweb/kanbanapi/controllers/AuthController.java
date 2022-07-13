package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.AuthRequest;
import com.bdweb.kanbanapi.dtos.AuthResponse;
import com.bdweb.kanbanapi.services.UserDetailsServiceImpl;
import com.bdweb.kanbanapi.utils.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsServiceImpl;
    private TokenUtil tokenUtil;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl, TokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
        } catch (DisabledException e) {
            throw new Exception("Disabled user", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
        UserDetails userDetails = userDetailsServiceImpl
                .loadUserByUsername(request.getUsername());

        final String token = tokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .build());
    }
}