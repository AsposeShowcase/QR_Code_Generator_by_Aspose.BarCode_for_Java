/**
 * 
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 * 
 * THE MIT LICENSE (MIT)
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRENTY OF ANY KIND, EXPRESS OR IMPLIED.
 *
 */
package com.aspose.showcase.qrcodegen.web.api.filter; 

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
public class ApiOriginFilter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //destory
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //init
    }
}