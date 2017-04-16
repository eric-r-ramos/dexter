package io.dexter.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Returns a 401 error code (Unauthorized) to the client, when REST authentication fails.
 * @author ericramos
 */
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);

    /**
     * Return Http 401 when authentication got failure. Do not proceed with any redirect
     * 
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
    	
    	log.debug("Proced to checkout");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}