package com.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;




// ToDO make service methods: getAll, getById, getByUsername, authenticate(username, password), createNewUser

public abstract class UserService extends HttpServlet {

    private final ObjectMapper mapper;

    public UserService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    public void insert(User userToBeRegistered) {
    }

    public Object getUserByUsername(String userToBeRegisteredUsername) {
        return null;
    }

    public void createNewUser(User userToBeRegistered) {
    }
}

