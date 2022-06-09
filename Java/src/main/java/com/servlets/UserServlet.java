package com.servlets;

import com.dao.UserDAO;
import com.exceptions.NoUserFoundException;
import com.exceptions.OutOfBoundsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.services.GetUserById.getbyId;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserDAO userDAO;
    private String logString;

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


        Object logString = "UserServlet received a get request at - " + LocalDateTime.now();
            CustomLogger.log((String) logString, LogLevel.INFO);
            List<User> userList = userDAO.getAllUsers();
            System.out.println("This is the request " + req);

            //Get user by Username
            String username = req.getParameter("username");
            resp.getWriter().write("no user found with provided username");

            try {
                int userId = Integer.parseInt(req.getParameter("id"));
                userList = userList.stream().filter(user -> user.getId() == userId).collect(Collectors.toList());

            } catch (NumberFormatException e) {
                logString = "Null or invalid ID input";
                CustomLogger.log((String) logString, LogLevel.ERROR);
            }

            // filter userList based on username
            if (username != null) {
                userList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
            }

            // set response
            String result = mapper.writeValueAsString(userList);
            resp.setContentType("application/json");
            resp.getWriter().write(result);

        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object logString = "UserServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log((String) logString, LogLevel.INFO);

        try {
            List<User> users = userDAO.getAllUsers();
            User newUser = mapper.readValue(req.getInputStream(), User.class);
            for (User user : users) {
                if (newUser.getUsername().equals(user.getUsername())) {
                    logString = "Username taken, please insert a different username - " + LocalDateTime.now();
                    CustomLogger.log((String) logString, LogLevel.ERROR);

                    System.err.println("[ERROR] - Username taken, please insert a different username.");
                } else {
                    userDAO.createUser(newUser);
                }
            }


        } catch (Exception e) {
            logString = String.format("An error occurred while creating a User. More Information: %s", ExceptionUtils.getStackTrace(e));
            CustomLogger.log((String) logString, LogLevel.INFO);
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























































