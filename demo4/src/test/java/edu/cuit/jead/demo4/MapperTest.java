package edu.cuit.jead.demo4;

import edu.cuit.jead.demo4.entity.User;
import edu.cuit.jead.demo4.mapper.UserMapper;
import edu.cuit.jead.demo4.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUid(1L);
        user.setName("Alice");
        user.setGender("Female");
        user.setIntro("A software engineer.");
        user.setCity("New York");
        user.setPhoto("alice.jpg");

        int result = userMapper.insert(user);
        System.out.println("Insert Result: " + result);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println("Selected User: " + user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setUid(1L);
        user.setName("Alice Updated");
        int result = userMapper.updateById(user);
        System.out.println("Update Result: " + result);
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1L);
        System.out.println("Delete Result: " + result);
    }
}
