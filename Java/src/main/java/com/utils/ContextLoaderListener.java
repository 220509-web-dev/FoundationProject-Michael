package com.utils;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.exceptions.servlets.AuthServlet;
import com.exceptions.servlets.TestServlet;
import com.exceptions.servlets.UserServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ObjectMapper mapper = new ObjectMapper();
        final UserDAO userDAO = new UserDaoPostgres();


        ServletContext context = sce.getServletContext();

        TestServlet testServlet = new TestServlet(mapper, userDAO);
        context.addServlet("TestServlet", testServlet).addMapping("/test");

        UserServlet userServlet = new UserServlet(mapper, userDAO);
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");

        AuthServlet authServlet = new AuthServlet(mapper, userDAO);
        context.addServlet("AuthServlet", authServlet).addMapping("/auth/login");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

}



