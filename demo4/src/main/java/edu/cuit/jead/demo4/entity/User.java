package edu.cuit.jead.demo4.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO) // 如果主键是自增的，需要指定
    private Long uid;
    private String name; // 用户姓名
    private String gender; // 性别
    private String intro; // 简介
    private String city; // 城市
    private String photo; // 头像路径
}
