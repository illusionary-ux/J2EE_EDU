package edu.cuit.jead.demo4.service;




import edu.cuit.jead.demo4.dto.UserDTO;
import edu.cuit.jead.demo4.entity.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface UserService {
    UserDTO addUser(User user, MultipartFile avatarFile) throws IOException;
    UserDTO getUserById(Long uid);
    void deleteUser(Long uid);
    UserDTO updateUser(Long uid, User user, MultipartFile newAvatarFile) throws IOException;

    // Redis缓存操作
    UserDTO quickSearchById(Long uid);
    void quickAdd(UserDTO userDTO);
    void quickDelete(Long uid);
}