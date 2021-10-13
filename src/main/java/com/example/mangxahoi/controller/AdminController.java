package com.example.mangxahoi.controller;

import com.example.mangxahoi.service.account.IServiceAccount;
import com.example.mangxahoi.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    JwtService jwtService;

}
