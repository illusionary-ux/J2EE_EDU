package edu.cuit.jead.demo3.controller;

import edu.cuit.jead.demo3.entity.User;
import edu.cuit.jead.demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/search")
    public User userSearch(String uid) {
        return userService.getUser(uid);
    }

    @PostMapping("/user/add")
    public String userAdd(User user, MultipartFile img) {
        String uid;
        try {
            uid = userService.addUser(user, img);
        } catch (IOException e) {
            e.printStackTrace();
            return "fail to add user";
        }

        return  uid + " Upload Successfully!";
    }
}
