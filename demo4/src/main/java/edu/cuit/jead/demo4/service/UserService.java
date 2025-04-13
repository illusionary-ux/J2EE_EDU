package edu.cuit.jead.demo4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cuit.jead.demo4.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface UserService extends IService<User> {
    String saveUserWithPhoto(User user, MultipartFile file) throws Exception;
    String updateUserWithPhoto(User user, MultipartFile file) throws Exception;
    String deleteUserWithPhoto(Long uid) throws Exception;
    User getUserById(Long uid);
    List<User> getAllUser();
    List<User> batchUserSearch(Map<String, Object> searchCritteria);
}
