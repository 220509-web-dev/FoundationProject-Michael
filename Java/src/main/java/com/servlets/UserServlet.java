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
import java.time.LocalDateTime;
import java.util.List;

import static com.services.GetAllUsers.GetAllUsersRequest;
import static com.services.GetUserById.getbyId;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserDAO userDAO;

    public UserServlet(ObjectMapper mapper, UserDAO userDAO) {
        this.mapper = mapper;
        this.userDAO = userDAO;
    }


    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Init param, another-param: " + this.getServletConfig().getInitParameter("another-param"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // GET: /users
        // GET: /users?id=123
        // GET: /users?username=tester@email.com
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
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(user));
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
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(users));
        // if we make it here, then the request isn't for a user by id or username - just get all users

        // search DB for all users and return


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - UserServlet received a POST request at " + LocalDateTime.now());

        try {
            // AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
            //System.out.println(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setStatus(204);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}































