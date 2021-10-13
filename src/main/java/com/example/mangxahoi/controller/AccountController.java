package com.example.mangxahoi.controller;

import com.example.mangxahoi.service.account.IServiceAccount;
import com.example.mangxahoi.service.accountlike.IServiceLike;
import com.example.mangxahoi.service.comment.IServiceComment;
import com.example.mangxahoi.service.friend.IServiceFriend;
import com.example.mangxahoi.service.image.IServiceImage;
import com.example.mangxahoi.service.jwt.JwtService;
import com.example.mangxahoi.service.post.IServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@EnableSpringDataWebSupport
public class AccountController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    IServiceImage serviceImage;
    @Autowired
    IServicePost servicePost;
    @Autowired
    IServiceLike serviceLike;
    @Autowired
    IServiceComment serviceComment;
    @Autowired
    JwtService jwtService;
    @Autowired
    IServiceFriend serviceFriend;


}
