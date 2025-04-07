package edu.cuit.jead.demo4.controller;

import edu.cuit.jead.demo4.dto.UserDTO;
import edu.cuit.jead.demo4.entity.User;
import edu.cuit.jead.demo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("intro") String intro,
            @RequestParam("city") String city,
            @RequestParam("file") MultipartFile file) {
        try {
            User user = new User();
            user.setName(name);
            user.setGender(gender);
            user.setIntro(intro);
            user.setCity(city);

            UserDTO dto = userService.addUser(user, file);
            return ResponseEntity.ok(dto);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
