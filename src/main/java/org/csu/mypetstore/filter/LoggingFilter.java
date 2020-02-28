package org.csu.mypetstore.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.Filter;
import java.io.*;
import java.util.Date;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

public class LoggingFilter implements Filter {
    private PrintWriter logger;
    private String prefix;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("prefix");
        String logFileName = filterConfig.getInitParameter("logFileName");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        System.out.println("logFileName: " + logFileName);
        try {

            logger = new PrintWriter(new File(appPath,logFileName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("loggingFilter.doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
        logger.flush();
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destorying filter");
        if(logger != null)
        {
            logger.close();
        }
    }
}
