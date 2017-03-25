package com.pniewinski.controllers;

import com.pniewinski.entities.Contact;
import com.pniewinski.entities.User;
import com.pniewinski.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by niewinskip on 2017-03-06.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ControllerUser {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    // not secure
    @RequestMapping(value = "/users/{uId}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("uId") Integer cId) {
        userService.deleteUserById(cId);
    }

    // not secure
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @RequestMapping(value = "/users/{uId}/contacts", method = RequestMethod.GET)
    public List<Contact> getUserContacts(@PathVariable("uId") Integer uId) {
        User user = userService.getUserById(uId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("user:"  + user.getRole());
        if (user.getEmail().equals(email)) {
            return userService.getUserContacts(user.getuId());
        } else {
            throw new SecurityException("Authentication error");
        }
    }

    @RequestMapping(value = "/users/{uId}/contacts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewContact(@RequestBody Contact contact, @PathVariable("uId") Integer uId) {
        User user = userService.getUserById(uId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (user.getEmail().equals(email)) {
            user.getContactList().add(contact);
            userService.saveUser(user);
        } else {
            throw new SecurityException("Authentication error");
        }
    }

    @RequestMapping(value = "/users/id", method = RequestMethod.GET)
    public String getLoggedUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return "{\"id\":\"" + userService.getUserByEmail(email).getuId() + "\"}";
    }
}
