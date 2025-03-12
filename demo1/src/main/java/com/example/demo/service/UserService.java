package com.example.demo.service;

import com.example.demo.config.CommonConfig;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private CommonConfig config;

    @Autowired
    private FileUploader fileUploader;

    @Autowired
    private UserMapper userMapper;

    private int cnt;
    private String lastID;

    @PostConstruct
    public void init() {
        lastID = userMapper.getLastId();
        if (lastID == null) {
            cnt = 1;
        } else {
            cnt = Integer.parseInt(lastID.substring(3)) + 1;
        }
    }

    public User getUser(String UID) {
        return userMapper.selectUserByUid(UID);
    }

    public String addUser(User user, MultipartFile img) throws IOException {

        String uid = String.format("UID%03d", cnt);
        cnt++;
        user.setUID(uid);

        // 保存用户头像
        String imgPath = fileUploader.saveFile(img, uid);
        user.setPhoto(imgPath);

        // 将用户信息写入用户池
        userMapper.insertUser(user);
        return uid;
    }
}

