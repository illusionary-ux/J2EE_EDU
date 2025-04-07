package edu.cuit.jead.demo4.service;

import edu.cuit.jead.demo4.util.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FileUploader fileUploader;

    
}
