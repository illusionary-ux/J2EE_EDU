package edu.cuit.jead.demo3.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    public enum Gender {
        MALE, FEMALE
    }
    String uid;
    String name;
    int age;
    Gender gender;
    String photo; // photo image file path
    String intro;
}
