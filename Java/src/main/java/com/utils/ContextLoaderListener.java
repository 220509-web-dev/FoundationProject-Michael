package com.utils;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlets.TestServlet;

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



    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
