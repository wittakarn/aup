package com.thaisoftplus.aup.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Wittakarn
 *
 */
public class CharacterEncodingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            chain.doFilter(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {

    }

}
