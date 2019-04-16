package com.ateknea.controllers;

import com.ateknea.entities.User;
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

}