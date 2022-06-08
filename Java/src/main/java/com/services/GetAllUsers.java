package com.services;

import com.dao.UserDAO;
import com.exceptions.NoUsersFound;
import com.models.User;

import java.util.List;

public class GetAllUsers {
    public static List<User> GetAllUsersRequest(UserDAO userDAO) {
        List<User> users = userDAO.getAllUsers();
        if (users.size() == 0) {
            throw new NoUsersFound();
        }
        return users;
    }
}
