package com.servlets;

import com.dao.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    final private ObjectMapper mapper; // not used here, but needed for other (login) servlet
    final private UserDAO userDAO;

    public TestServlet(ObjectMapper mapper, UserDAO userDAO) {
        this.mapper = mapper;
        this.userDAO = userDAO;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG} - TestServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String potentialId = req.getParameter("id");
        String potentialUsername = req.getParameter("username");

        if (potentialId != null) {
            // search DB for user with given id
            resp.getWriter().write("Sent request to service layer for getUserById, value entered: " + potentialId);
            return;
        }

        if (potentialUsername != null) {
            // search the DB for user with given username
            resp.getWriter().write("Sent request to service layer for getUserByUsername, value entered: " + potentialUsername);
            return;
        }
        resp.getWriter().write("The service method for getAllUsers got the following record(s): ");

        // if we make it here, then the request isn't for a user by id or username - just get all users

        // search DB for all users and return
    }

}
