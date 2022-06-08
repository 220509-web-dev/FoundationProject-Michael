package com.app;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.models.User;

public class test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDaoPostgres();
        User user = userDAO.getUserById(1);
        System.out.println(user);
    }
}
