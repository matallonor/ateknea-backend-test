package com.ateknea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ateknea.entities.User;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApplicationController applicationController;

    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = applicationController.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}