package edu.cuit.jead.demo4.controller;

import edu.cuit.jead.demo4.entity.User;
import edu.cuit.jead.demo4.service.UserService;
import edu.cuit.jead.demo4.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController // 标记为 REST 控制器
@RequestMapping("/users") // 设置基础请求路径为 /users
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService; // 自动注入 UserService
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/") // 处理 POST 请求到 /users/
    public ResponseEntity<String> createUser(@RequestParam String name,
                                             @RequestParam String gender,
                                             @RequestParam String intro,
                                             @RequestParam String city,
                                             @RequestParam(value = "photo", required = false) MultipartFile file) {
        try {
            User user = new User(); // 创建一个新的 User 对象
            user.setName(name); // 设置用户名
            user.setGender(gender); // 设置用户性别
            user.setIntro(intro); // 设置用户简介
            user.setCity(city); // 设置用户所在城市

            logger.log(Level.INFO, "Creating user: {0}", user); // 日志记录创建的用户信息
            userService.saveUserWithPhoto(user, file); // 调用服务层方法保存用户及照片
            return ResponseEntity.ok("User created successfully!"); // 返回成功响应
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating user: {0}", e.getMessage()); // 日志记录错误信息
            return ResponseEntity.badRequest().body(e.getMessage()); // 返回错误响应
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{uid}") // 处理 PUT 请求到 /users/{uid}
    public ResponseEntity<String> updateUser(@PathVariable Long uid,
                                             @RequestParam String name,
                                             @RequestParam String gender,
                                             @RequestParam String intro,
                                             @RequestParam String city,
                                             @RequestParam(value = "photo", required = false) MultipartFile file) {
        try {
            User user = new User(); // 创建一个新的 User 对象
            user.setUid(uid); // 设置用户ID
            user.setName(name); // 设置用户名
            user.setGender(gender); // 设置用户性别
            user.setIntro(intro); // 设置用户简介
            user.setCity(city); // 设置用户所在城市

            logger.log(Level.INFO, "Updating user: {0}", user); // 日志记录更新的用户信息
            userService.updateUserWithPhoto(user, file); // 调用服务层方法更新用户及照片
            return ResponseEntity.ok("User updated successfully!"); // 返回成功响应
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating user: {0}", e.getMessage()); // 日志记录错误信息
            return ResponseEntity.badRequest().body(e.getMessage()); // 返回错误响应
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{uid}") // 处理 DELETE 请求到 /users/{uid}
    public ResponseEntity<String> deleteUser(@PathVariable Long uid) {
        try {
            logger.log(Level.INFO, "Deleting user with ID: {0}", uid); // 日志记录删除的用户ID
            userService.deleteUserWithPhoto(uid); // 调用服务层方法删除用户
            return ResponseEntity.ok("User deleted successfully!"); // 返回成功响应
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting user: {0}", e.getMessage()); // 日志记录错误信息
            return ResponseEntity.badRequest().body(e.getMessage()); // 返回错误响应
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{uid}") // 处理 GET 请求到 /users/{uid}
    public ResponseEntity<User> getUserById(@PathVariable Long uid) {
        User user = userService.getUserById(uid); // 调用服务层方法获取用户
        if (user != null) {
            logger.log(Level.INFO, "Retrieved user by ID {0}: {1}", new Object[]{uid, user}); // 日志记录获取的用户信息
            return ResponseEntity.ok(user); // 返回用户数据
        } else {
            logger.log(Level.WARNING, "User not found with ID: {0}", uid); // 日志记录未找到用户的信息
            return ResponseEntity.notFound().build(); // 返回未找到响应
        }
    }

    @GetMapping("/") // 处理 GET 请求到 /users/
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser(); // 调用服务层方法获取所有用户
        logger.log(Level.INFO, "Retrieved all users: {0}", users); // 日志记录获取的所有用户信息
        return ResponseEntity.ok(users); // 返回用户列表
    }

    @GetMapping("/test") // 处理 GET 请求到 /users/test
    public ResponseEntity<String> test() {
        logger.log(Level.INFO, "Test endpoint accessed"); // 日志记录测试端点被访问
        return ResponseEntity.ok("Test endpoint is working!"); // 返回测试响应
    }

    @GetMapping("/batchUsersSearch")
    public ResponseEntity<List<User>> batchUsersSearch(@RequestParam Map<String, Object> searchCriteria) {
        List<User> users = userServiceImpl.batchUserSearch(searchCriteria);
        logger.log(Level.INFO, "Retrieved all users: {0}", users);
        return ResponseEntity.ok(users);
    }
}



