package com.servlets;

import com.dao.UserDAO;
import com.exceptions.NoUserFoundException;
import com.exceptions.OutOfBoundsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.services.GetAllUsers.GetAllUsersRequest;
import static com.services.GetUserById.getbyId;

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
            //resp.getWriter().write("Sent request to service layer for getUserById, value entered: " + potentialId);
            User user;
            try {
                user = getbyId(userDAO, potentialId);
            } catch (NumberFormatException e) {
                resp.getWriter().write("ID entered was not an integer!");
                resp.setStatus(400);
                return;
            } catch (OutOfBoundsException e) {
                resp.getWriter().write("ID entered was out of bounds!");
                resp.setStatus(400);
                return;
            } catch (NoUserFoundException e) {
                resp.getWriter().write("There is no user with that ID!");
                resp.setStatus(400);
                return;
            } catch (SQLException e) {
                resp.getWriter().write("A database error occurred!");
                resp.setStatus(400);
                return;
            } catch (Throwable t) {
                resp.getWriter().write("An error occurred!");
                resp.setStatus(400);
                return;
            }
            resp.setStatus(200);
            resp.getWriter().write("Service layer found the record " + user);

            return;
        }

        if (potentialUsername != null) {
            // search the DB for user with given username
            resp.getWriter().write("Sent request to service layer for getUserByUsername, value entered: " + potentialUsername);
            return;
        }
        System.out.println("The service method for getAllUsers was invoked");
        List<User> users;
        try {
            users = GetAllUsersRequest(userDAO);
        } catch (Exception e) {
            resp.getWriter().write("A database error occurred!");
            resp.setStatus(400);
            return;
        }
        resp.setStatus(200);
        resp.setHeader("Content-type", "text/plain");
        resp.getWriter().write(mapper.writeValueAsString(users));
        // if we make it here, then the request isn't for a user by id or username - just get all users

        // search DB for all users and return
    }

}
