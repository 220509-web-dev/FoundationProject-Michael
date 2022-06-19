package com.services;

import com.dao.UserDAO;
import com.models.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


class UserService {
    private final UserDAO userDAO;
    private String logString;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByUsername(String user) {
        return this.userDAO.getUserByUsername(user);
    }

    public User getUserByID(int id) {
        return this.userDAO.getUserById(id);
    }

    public void createNewUser(User newUser) {
        this.userDAO.createUser(newUser);
    }
}
// ToDO make service methods: getAll, getById, getByUsername, authenticate(username, password), createNewUser

public abstract class userService extends HttpServlet {

    private final ObjectMapper mapper;

    public userService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static User AuthService(String providedUsername, String providedPassword) {
        return null;
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(200);
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