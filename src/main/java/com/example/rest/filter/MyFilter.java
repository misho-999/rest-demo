package com.example.rest.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* This is example of servlet Filter.
* It filters every Controller request
* */
@Component
public class MyFilter implements Filter {

    // doFilter() Method - To apply Filter's Business logic.
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("================This is a Servlet doFilter() Method! ================");

        // Get remote data
        System.out.println("Request LocalAddress: "+ servletRequest.getLocalAddr());
        System.out.println("Remote Address: "+ servletRequest.getRemoteAddr());
        System.out.println("Request LocalPort: " + servletRequest.getLocalPort());
        System.out.println("Response Locale: " + servletResponse.getLocale());

        // Invoke filterChain to execute the next filter inorder.
        filterChain.doFilter(servletRequest,servletResponse);
    }
}