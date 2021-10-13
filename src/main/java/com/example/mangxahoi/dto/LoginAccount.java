package com.example.mangxahoi.dto;


import com.example.mangxahoi.model.Image;
import lombok.Data;

@Data
public class LoginAccount {
    private Long id;
    private String fullName;
    private Image avatar;
    private String token;
}