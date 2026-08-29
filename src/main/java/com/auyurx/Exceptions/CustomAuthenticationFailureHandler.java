package com.auyurx.Exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if (exception instanceof DisabledException){
            errorMessage = "Account not enabled. Please verify your email.";
        } else if (exception instanceof LockedException) {
            errorMessage = "Email not verified. Check your inbox.";
        }else {
            errorMessage = "Invalid email or password.";
        }
        request.getSession().setAttribute("error",errorMessage);
        response.sendRedirect("/login?error");
    }
}
