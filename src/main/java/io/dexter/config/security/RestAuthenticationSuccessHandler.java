package io.dexter.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Spring Security success handler, specialized for REST requests.
 * @author ericramos
 */
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    /**
     * Return Http 200 (OK status) when authentication got sucess. Do not proceed with any redirect
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

