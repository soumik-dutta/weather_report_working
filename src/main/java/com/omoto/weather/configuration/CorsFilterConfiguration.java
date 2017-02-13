package com.omoto.weather.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
   Clients using this REST runs on 192.1.1.21:9000. This client is then trying to connect to a REST
   service running on 192.1.1.21:8080.  Client is using Angular to connect to the server.
   This will result in the following error:
   No 'Access-Control-Allow-Origin' header is present on the requested resource
   This is the default browser behavior to prevent cross domain or port hacking.
   CORS security must be configured to enable this. Can be also done via web.xml.
 */
@Component
public class CorsFilterConfiguration implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, authToken"); //TODO: Fix 'token' to 'OAuth' / 'SAML' header.
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}