package edu.cuit.jead.demo3.dao;

import edu.cuit.jead.demo3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedisAccess {
    private RedisTemplate<String, User> redisTemplate;
    private ValueOperations<String, User> kvOps;

    @Autowired
    public UserRedisAccess(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        kvOps = redisTemplate.opsForValue();
    }

    public User get(String id) {
        return kvOps.get(id);
    }

    public void put(String id, User user) {
        kvOps.set(id, user);
    }
}
