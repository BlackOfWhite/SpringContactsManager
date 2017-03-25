package com.pniewinski.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by niewinskip on 2017-03-23.
 * <p>
 * Receive POST request from the /login path and authenticate the user.
 * When the user is successfully authenticated, it will return a JWT in the Authorization header of the response.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(url);
        setAuthenticationManager(authManager);
    }

    /**
     * User email and password are retrieved during authentication attempt from the request.
     * Compare it to existing user. If ok, then successfulAuthentiction() will be triggered.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AuthCredentials authCredentials = new ObjectMapper()
                .readValue(request.getInputStream(), AuthCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        authCredentials.getUsername(),
                        authCredentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    /**
     * Fetch the name from the authenticated user, and pass it on to TokenAuthenticationService, which will then add a JWT to the response.
     *
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService
                .addAuthentication(response, auth.getName());
    }
}
