package edu.cuit.jead.demo4.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.cuit.jead.demo4.entity.User;
import edu.cuit.jead.demo4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUserService redisUserService;

    @CachePut(value = "users",key = "#user.uid")
    @Override
    public String saveUserWithPhoto(User user, MultipartFile file) throws Exception {
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            logger.log(Level.SEVERE, "File is empty");
            throw new RuntimeException("file is empty");
        }

        // 定义上传目录（修改这里改变保存位置）
        Path uploadDir = Paths.get("src/main/resources/static/upload").toAbsolutePath().normalize();
        Files.createDirectories(uploadDir);

        // 处理文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename())
                .replace(" ", "_");

        // 构建目标路径
        Path targetLocation = uploadDir.resolve(fileName);

        // 保存文件
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // 设置用户照片路径（可以只存储相对路径或文件名）
        user.setPhoto("/upload/" + fileName);

        try {
            userMapper.insert(user);
            logger.log(Level.INFO, "Saved user: {0}", user);
            redisUserService.quickAdd(user);
            return user.getUid() + " saved successfully";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inserting user: {0}", e.getMessage());
            throw new RuntimeException("Failed to insert user", e);
        }
    }

    @CachePut(value = "users", key = "#user.uid")
    @Override
    public String updateUserWithPhoto(User user, MultipartFile file) throws Exception {
        // 验证文件是否为空
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            logger.log(Level.SEVERE, "File is empty or invalid");
            throw new RuntimeException("File is empty or invalid");
        }

        // 1. 定义上传目录（使用配置或固定路径）
        // 生产环境建议使用配置的方式，如 @Value("${app.upload.dir}")
        Path uploadDir = Paths.get("uploads").toAbsolutePath().normalize();

        // 2. 确保目录存在
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // 3. 处理文件名（增加安全性）
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String safeFilename = UUID.randomUUID().toString() + fileExtension; // 使用UUID防止文件名冲突

        // 4. 构建完整的目标路径
        Path targetLocation = uploadDir.resolve(safeFilename);

        // 5. 保存文件（替换已存在的文件）
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }

        // 6. 设置用户照片路径（存储相对路径）
        user.setPhoto("/uploads/" + safeFilename);

        // 7. 更新用户信息
        try {
            userMapper.updateById(user);
            logger.log(Level.INFO, "Successfully updated user: {0}", user.getUid());
            redisUserService.quickUpdate(user);
            return user.getUid() + " updated successfully with new photo";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user: {0}", e.getMessage());
            // 如果更新失败，删除已上传的文件
            Files.deleteIfExists(targetLocation);
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Override
    @CacheEvict(value = "users", key = "#uid")
    public String deleteUserWithPhoto(Long uid) throws Exception {
        // 1. 查找用户
        User user = userMapper.selectById(uid);
        if (user == null) {
            logger.log(Level.SEVERE, "User with uid: {0} not found", uid);
            throw new RuntimeException("User not found");
        }

        // 2. 处理照片文件删除
        if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
            try {
                // 构建完整文件路径（考虑存储的是相对路径）
                Path photoPath = Paths.get("uploads").toAbsolutePath().normalize()
                        .resolve(user.getPhoto().replaceFirst("/uploads/", ""));

                // 删除文件
                if (Files.exists(photoPath)) {
                    Files.delete(photoPath);
                    logger.log(Level.INFO, "Deleted photo file: {0}", photoPath);
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to delete photo file for user {0}: {1}",
                        new Object[]{uid, e.getMessage()});
                // 不阻止用户删除，即使文件删除失败
            }
        }

        // 3. 删除用户记录
        try {
            userMapper.deleteById(uid);
            logger.log(Level.INFO, "Successfully deleted user with ID: {0}", uid);
            redisUserService.quickDelete(uid);
            return "User " + uid + " deleted successfully";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user: {0}", e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    @Override
    @Cacheable(value = "users", key = "#uid")
    public User getUserById(Long uid) {
        User cachedUser = redisUserService.quickSearchById(uid);
        if (cachedUser != null) {
            return cachedUser;
        }
        User user = userMapper.selectById(uid);
        logger.log(Level.INFO, "Retrieved user by ID {0}: {1}", new Object[]{uid, user}); // 日志记录获取的用户信息
        redisUserService.quickUpdate(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectList(null);
        logger.log(Level.INFO, "Retrieved all users: {0}", users); // 日志记录获取的所有用户信息
        return users;
    }

    @Override
    public List<User> batchUserSearch(Map<String, Object> searchCritteria){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        for (Map.Entry<String, Object> entry : searchCritteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key.toLowerCase()) {
                case "city":
                    queryWrapper.eq("city", value);
                    break;
                case "name":
                    queryWrapper.like("name", value);
                    break;
                case "gender":
                    queryWrapper.eq("gender", value);
                    break;
                default:
                    logger.log(Level.SEVERE, "Unknown key: {0}", key);
                    break;
            }
        }

        List<User> users = userMapper.selectList(queryWrapper);
        logger.log(Level.INFO, "Retrieved all users: {0}", users);
        return users;
    }
}
