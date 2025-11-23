package com.example.bankapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String traceId = UUID.randomUUID().toString();

        logger.info("Incoming request: method={}, path={}, traceId={}",
                request.getMethod(),
                request.getRequestURI(),
                traceId
        );

        // Add traceId to response
        response.addHeader("X-Trace-Id", traceId);

        // Continue the request
        filterChain.doFilter(request, response);
    }
}
