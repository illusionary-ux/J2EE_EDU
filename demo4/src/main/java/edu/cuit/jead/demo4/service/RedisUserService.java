package edu.cuit.jead.demo4.service;

import edu.cuit.jead.demo4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUserService {
    private static final String USER_KEY_PREFIX = "user:";

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public User quickSearchById(Long uid)
    {
        return redisTemplate.opsForValue().get(USER_KEY_PREFIX + uid);
    }

    public void quickAdd(User user)
    {
        redisTemplate.opsForValue().set(USER_KEY_PREFIX + user.getUid(), user);
    }

    public void quickDelete(Long uid)
    {
        redisTemplate.delete(USER_KEY_PREFIX + uid);
    }

    public void quickUpdate(User user)
    {
        redisTemplate.opsForValue().set(USER_KEY_PREFIX + user.getUid(), user);
    }
}
