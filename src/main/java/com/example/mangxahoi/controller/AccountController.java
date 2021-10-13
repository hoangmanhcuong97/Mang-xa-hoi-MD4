package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Account;
import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.model.Post;
import com.example.mangxahoi.service.account.IServiceAccount;
import com.example.mangxahoi.service.accountlike.IServiceLike;
import com.example.mangxahoi.service.comment.IServiceComment;
import com.example.mangxahoi.service.friend.IServiceFriend;
import com.example.mangxahoi.service.image.IServiceImage;
import com.example.mangxahoi.service.jwt.JwtService;
import com.example.mangxahoi.service.post.IServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody Image image) {
        Post post = image.getPost();
        Long idAc = post.getAccount().getId();
        Account account = serviceAccount.findById(idAc).get();
        post.setAccount(account);

        Post newPost = servicePost.add(post);
        image.setPost(newPost);
        serviceImage.save(image);
        return new ResponseEntity<>(servicePost.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findPost/{idPost}")
    public ResponseEntity<Post> findPostById(@PathVariable("idPost") Long idPost) {
        Post post = servicePost.findById(idPost).get();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
