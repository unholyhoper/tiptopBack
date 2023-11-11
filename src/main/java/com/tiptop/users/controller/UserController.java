package com.tiptop.users.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.tiptop.users.entities.User;
import com.tiptop.users.service.UserService;

import javax.jws.soap.SOAPBinding;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="all",method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getYearWinner")
    public User getYearWinner(){
        return userService.getYearWinner();
    }

    @GetMapping("/getUserId/{username}")
    public Long getUserIdByUsername(@PathVariable String username){
        return userService.getUserIdByUsername(username);
    }
/*
	    @DeleteMapping("/users/{username}")
	    public void deleteUser(@PathVariable String username) {
	        userService.deleteUserByUsername(username);
	    }

	    @GetMapping("/users/{username}/roles")
	    public List<Role> getUserRoles(@PathVariable String username) {
	        return userService.findUserRolesByUsername(username);
	    }

	    @PutMapping("/users/{username}")
	    public User updateUser(@RequestBody User user, @PathVariable String username) {
	        return userService.updateUser(user, username);
	    }
	    */
}



