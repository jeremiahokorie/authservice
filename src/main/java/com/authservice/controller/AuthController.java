package com.authservice.controller;

import com.authservice.dto.AuthenticationResponse;
import com.authservice.dto.request.AuthRequest;
import com.authservice.service.impl.CustomUserDetailsService;
import com.authservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController{

    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;

    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created, Register user first");
            return null;
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt =jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);
    }


}
