package com.ride2go.r2gapi.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    public SimpleCorsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "12000");
        //IE11 enforces us to do it explicitly as a comma-separated string.
       response.setHeader("Access-Control-Allow-Headers",
               "Access-Control-Allow-Headers, "+
                       "Content-Type, "+
                       "Access-Control-Allow-Headers, "+
                       "Authorization, "+
                       "X-Requested-With, "+
                       "Apikey, apikey, "+
                       "Authkey, authkey"
       );
       // response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}