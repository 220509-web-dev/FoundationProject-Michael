package com.services;


import com.dao.UserDAO;
import com.exceptions.NoUserFoundException;
import com.exceptions.OutOfBoundsException;
import com.models.User;

import java.sql.SQLException;

public class GetUserById {
    public static User getbyId(UserDAO userDAO, String id_maybe) throws SQLException {
        // TODO : Test id_maybe for validity
        int id;
        try {
            id = Integer.parseInt(id_maybe);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        if (id < 0 || id > 2000000000) {
            throw new OutOfBoundsException();
        }
        // We arrived here in the code. We have a valid integer id
        User user;
        try {
            user = userDAO.getUserById(id);
        } catch (Throwable t) {
            //String at = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
            //System.out.println(at+at+at+at+at+at+at+at+at);
            throw new SQLException();
        }

        if (user == null) {
            throw new NoUserFoundException();
        }
        return user;
    }
}






