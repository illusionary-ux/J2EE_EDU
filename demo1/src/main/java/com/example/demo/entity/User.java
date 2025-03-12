package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
    public enum Gender
    {
        MALE, FEMALE
    }
    private String UID;
    private String name;
    private int age;
    Gender gender;
    String photo;
    String intro;
}
