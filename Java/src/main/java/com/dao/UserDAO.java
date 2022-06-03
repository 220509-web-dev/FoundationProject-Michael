package com.dao;

import com.models.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);

    User getUserByUsername(String username);

    User getUserById(int id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUserById(int id);
}
