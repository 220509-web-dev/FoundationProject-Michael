package com.exceptions.servlets;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.utils.CustomLogger;
import com.utils.LogLevel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private String logString;

    public AuthServlet(ObjectMapper mapper, UserDAO userDAO) {
        this.mapper = mapper;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = new UserDaoPostgres();
        List<User> users = userDAO.getAllUsers();
        String path = req.getServletPath();

        if (path.equals("/auth/login")) {

        } else if (path.equals("/auth/register")) {

        } else {

        }
        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");

        for (User user : users) {
            if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
                System.out.println("[LOG] - found user!");
                logString = "Found user, Cookie Generated! - " + LocalDateTime.now();
                CustomLogger.log(logString, LogLevel.INFO);
                CustomLogger.parser();


                HttpSession session = req.getSession();
                session.setAttribute("auth-user", user);

                resp.setStatus(204);
                return;
            }
        }

        resp.setStatus(400);
        resp.setContentType("application/json");

        HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("code", 400);
        errorMessage.put("message", "No user found with provided credentials");
        errorMessage.put("timestamp", LocalDateTime.now().toString());

        logString = "No user found with provided credentials - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.ERROR);
        CustomLogger.parser();


        resp.getWriter().write(mapper.writeValueAsString(errorMessage));


    }


}