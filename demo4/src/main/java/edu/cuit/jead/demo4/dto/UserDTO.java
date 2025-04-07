package edu.cuit.jead.demo4.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String formattedUid;  // 格式化后的UID（如"UID0001"）
    private String name;
    private String gender;
    private String intro;
    private String city;
    private String photoUrl;     // 头像访问路径（可能拼接完整URL）
}
