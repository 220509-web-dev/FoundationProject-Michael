package com.services;

import com.exceptions.InvalidCredentialsException;
import com.exceptions.UsernameNotAvailableException;
import com.models.User;


public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(User userToBeRegistered) {
        if (userService.getUserByUsername(userToBeRegistered.getUsername()) != null) {
            throw new UsernameNotAvailableException("Username is already taken");
        }

        userService.createNewUser(userToBeRegistered);
    }

    public User login(String username, String password) {
        User user;

        user = (User) userService.getUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            System.out.println("Logged in Successfully!");
            return user;
        } else if (user != null && !password.equals(user.getPassword())) {
            System.out.println("Wrong Password, try again.");
            throw new InvalidCredentialsException("Wrong Password Entered.");
        } else {
            System.out.println("User Does Not Exist!");
            throw new InvalidCredentialsException("User Does Not Exist!");
        }
    }
}

