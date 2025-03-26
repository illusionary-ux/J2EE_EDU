package edu.cuit.jead.demo3.config;


import edu.cuit.jead.demo3.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CommConfig {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.static-path}")
    private String staticPath;

    public String getUploadDir() {
        return uploadDir;
    }

    public String getStaticPath() { return staticPath; }


    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        //使用String序列化键
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);

        //使用Jackson序列化User对象
        Jackson2JsonRedisSerializer jSer = new Jackson2JsonRedisSerializer(User.class);
        template.setValueSerializer(jSer);

        return template;

    }
}
