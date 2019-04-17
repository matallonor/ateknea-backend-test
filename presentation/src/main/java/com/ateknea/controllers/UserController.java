package com.ateknea.controllers;

import com.ateknea.dto.UserRequest;
import com.ateknea.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ateknea.entities.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = applicationController.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        // Create user
        User user = applicationController.createUser(userMapper.toUser(request));
        if (user == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        // Generate response
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUSer(@RequestBody UserRequest request, @PathVariable Long userId) {
        // Update user if exists
        User user = applicationController.updateUser(userId, userMapper.toUser(request));
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // Generate response
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }
}