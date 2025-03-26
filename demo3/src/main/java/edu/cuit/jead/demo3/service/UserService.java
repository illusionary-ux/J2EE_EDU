package edu.cuit.jead.demo3.service;

import edu.cuit.jead.demo3.dao.UserRedisAccess;
import edu.cuit.jead.demo3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    //Map<String, User> userOcean;

    @Autowired
    private UserRedisAccess userPool;

    @Autowired
    private FileUploader fileUploader;

    int cnt = 0; //计数，统计已经加入了多少User对象


    public User getUser(String uid){
        return userPool.get(uid);
    }

    public String addUser(User user, MultipartFile img)
        throws IOException {
        cnt++;
        String uid = String.format("UID%03d", cnt);
        user.setUid(uid);

        String imgPath = fileUploader.saveFile(img, uid);
        user.setPhoto(imgPath);

        userPool.put(uid, user);
        return uid;
    }
}
