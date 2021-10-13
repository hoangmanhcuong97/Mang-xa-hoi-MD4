package com.example.mangxahoi.controller;

import com.example.mangxahoi.service.account.IServiceAccount;
import com.example.mangxahoi.service.approle.IServiceAppRole;
import com.example.mangxahoi.service.image.IServiceImage;
import com.example.mangxahoi.service.jwt.JwtService;
import com.example.mangxahoi.service.post.IServicePost;
import com.example.mangxahoi.service.verify.IVerifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class SignUpController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    IServiceAppRole serviceAppRole;
    @Autowired
    IServiceImage serviceImage;
    @Autowired
    IServicePost servicePost;
    @Autowired
    JwtService jwtService;
    @Autowired
    IVerifiService verifiService;

}
