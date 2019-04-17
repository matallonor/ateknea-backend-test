package com.ateknea.controllers;

import com.ateknea.entities.User;
import com.ateknea.exceptions.BadRequestException;
import com.ateknea.services.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private UserDBService userDBService;

    public List<User> getUsers() {
        return userDBService.getAll();
    }

    public User createUser(User user) throws BadRequestException {
        if (isValid(user)) {
            return userDBService.create(user);
        } else {
            return null;
        }
    }

    public User updateUser(Long userId, User user) throws BadRequestException {
        if (isValid(user)) {
            return userDBService.update(userId, user);
        } else {
            return null;
        }
    }

    public User deleteUser(Long userId) {
        return userDBService.delete(userId);
    }



    private boolean isValid(User user) throws BadRequestException {
        try {
            if (user == null) return false;

            // Name (max. length 20 characters / must contain alphanumeric characters)
            String name = user.getName();
            if (!user.isValidName(name)) {
                throw new BadRequestException("Invalid field name. It must be a string with a max length of 20 characters");
            }

            // Last name (max. length 40 characters / must contain alphanumeric characters
            String lastName = user.getLastName();
            if (!user.isValidLastName(lastName)) {
                throw new BadRequestException("Invalid field lastName. It must be a string with a max length of 40 characters");
            }

            // Email (max. length 20 characters, it must be checked the email format)
            String email = user.getEmail();
            if (!user.isValidEmail(email)) {
                throw new BadRequestException("Invalid field email. It must be a string with a max length of 20 characters " +
                        "and it must be like \\\"text@text.com\\\"\"");
            }

            boolean enabled = user.isEnabled();

            return true;
        } catch (ClassCastException e) {
            throw new BadRequestException("Invalid field type");
        }
    }
}
