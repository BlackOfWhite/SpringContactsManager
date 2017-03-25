package com.pniewinski.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by niewinskip on 2017-03-23.
 * <p>
 * Intercept all requests to validate the presence of the JWT–that is, the ones that are not issued to / nor /users.
 * This validation is done with the help of the TokenAuthenticationService class.
 */


public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("doFilter");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            Authentication authentication = TokenAuthenticationService
                    .getAuthentication((HttpServletRequest) request);

            final String authHeader = req.getHeader("Authorization");
            System.out.println("authHeader: " + authHeader);
            if (authHeader == null || !authHeader.trim().startsWith("Bearer ")) {
                System.out.println("Missing or invalid Authorization header.");
                throw new ServletException("Missing or invalid Authorization header.");
            }
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }

    }
}
