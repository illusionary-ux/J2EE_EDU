package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.view.UserRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /*@GetMapping("/user/search")
    public User searchUser(String UID) {
        return userService.getUser(UID);
    }*/

    @PostMapping("/user/add")
    public String addUser(User user, MultipartFile img) {
        String uid;
        try {
            uid = userService.addUser(user,img);
        }catch (IOException e){
            e.printStackTrace();
            return "fail";
        }
        return uid + "upload Successfully!";
    }

    /*@DeleteMapping("/user/delete/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uid) {
        try {
            userService.deleteUser(uid);
            return ResponseEntity.ok("User " + uid + " deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete user: " + e.getMessage());
        }
    }*/

    UserRender userRender = new UserRender();

    @GetMapping("/user/search")
    public String userSearch(String UID) {
        return userRender.userPage(userService.getUser(UID));
    }
}
