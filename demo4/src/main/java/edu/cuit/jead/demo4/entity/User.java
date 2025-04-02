package edu.cuit.jead.demo4.entity;

import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long uid;

    private String Name;
    private String Gender;
    private String Intro;
    private String City;
    private String Photo;
}
