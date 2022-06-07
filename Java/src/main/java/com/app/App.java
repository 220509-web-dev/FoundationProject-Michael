package com.app;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.models.User;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDaoPostgres();
//        List<User> userList = userDAO.getAllUsers();
//        System.out.println(userList);
//        User user = new User(5, "Dummy", "Bot", "dummybot", "botdummy", 1);
//        userDAO.createUser(user);

        User user = userDAO.getUserByUsername("dummybot");
//        user.setFirstName("Dummy");
//        user.setLastName("IDK");
//        userDAO.updateUser(user);
//        System.out.println(user);
//        userDAO.deleteUserById(9);



        User test = userDAO.getUserByUsername("dummybot");
        System.out.println(test);

    }
}
