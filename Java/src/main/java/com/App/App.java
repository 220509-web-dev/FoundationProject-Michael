package com.App;

import com.DAO.UserDAO;
import com.DAO.UserDaoPostgres;
import com.models.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDaoPostgres();
//        List<User> userList = userDAO.getAllUsers();
//        System.out.println(userList);
//        User user = new User(0, "Dummy", "Bot", "dummybot", "botdummy", 1);
//        userDAO.createUser(user);
//        User user = userDAO.getUserByUsername("dummybot");
//        user.setFirstName("Gerard");
//        user.setLastName("IDK");
//        userDAO.updateUser(user);
//        System.out.println(user);
//        userDAO.deleteUserById(8);



        User test = userDAO.getUserByUsername("dummybot");
        System.out.println(test);

    }
}
