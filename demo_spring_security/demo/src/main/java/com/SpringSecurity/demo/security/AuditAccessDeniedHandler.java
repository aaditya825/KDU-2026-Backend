package com.SpringSecurity.demo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Logs 403 Forbidden attempts for audit purposes.
 */
@Component
public class AuditAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(AuditAccessDeniedHandler.class);

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            @NonNull AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = (auth != null) ? String.valueOf(auth.getPrincipal()) : "anonymous";
        String path = request.getRequestURI();
        String method = request.getMethod();

        // ✅ Audit trail: forbidden access attempt
        log.warn("ACCESS_FORBIDDEN username={} method={} path={}", username, method, path);

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
    }
}
