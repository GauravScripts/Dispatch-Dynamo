package com.stackroute.vendorservice.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class VendorFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // get authorization header from request object

        String authHeader = request.getHeader("authorization");
        System.out.println(authHeader);
        // if authHeader is null || not bearer type : throw exception
        // else : verify the token, process request/dont process the request
        if(authHeader==null || !authHeader.startsWith("Bearer") ){
            throw new ServletException("Token missing");
        }
        else{ // authHeader existing + it has Bearer token
            // authHeader: Bearer_xxxxxxxxxxx.xxxxxxx.xxxxxxx

            String token = authHeader.substring(7); // Bearer abcd.xyz.mnop ->  abcd.xyz.mnop


            System.out.println(token);
            Claims claims= Jwts.parser().setSigningKey("G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A").parseClaimsJws(token).getBody();
            // above parsing throws exception if parsing fails ( token invalid / key invalid )
            System.out.println("claims : " + claims);
            // attach emailid to request
            request.setAttribute("current_user_emailid",claims.get("user_email"));
            filterChain.doFilter(request,response);

        }
    }
    }

