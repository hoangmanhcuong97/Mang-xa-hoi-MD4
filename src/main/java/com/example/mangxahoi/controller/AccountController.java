package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Account;
import com.example.mangxahoi.model.AccountLike;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @PostMapping("/timeline")
    public ResponseEntity<Page<Post>> timeline(@RequestBody String page) {
        String[] sortById = new String[2];
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 5, Sort.by("id").descending());
        Page<Post> postPage = servicePost.findPostByPrivacyContaining("public", pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/likeshow/{idAcc}/{idPost}")
    public ResponseEntity<?> createlike(@PathVariable("idAcc") Long idAcc, @PathVariable("idPost") Long idPost) {
        AccountLike accountLike = serviceLike.findByAccount_IdAndPost_Id(idAcc, idPost);
        if (accountLike != null) {
            Long idlike = accountLike.getId();
            serviceLike.remove(idlike);
        } else {
            Account account = serviceAccount.findById(idAcc).get();
            Post post = servicePost.findById(idPost).get();
            AccountLike accountLike1 = new AccountLike();
            accountLike1.setAccount(account);
            accountLike1.setPost(post);
            serviceLike.save(accountLike1);
        }
        serviceLike.remove(accountLike.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/comment/{idAcc}/{idPost}")
    public ResponseEntity<String> createComment(@RequestBody Comment comment, @PathVariable("idAcc") Long idAcc, @PathVariable("idPost") Long idPost) {
        Account account = serviceAccount.findById(idAcc).get();
        Post post = servicePost.findById(idPost).get();
        comment.setAccount(account);
        comment.setPost(post);
        serviceComment.save(comment);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/deletecomment/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable("idComment") Long idComment) {
        serviceComment.remove(idComment);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
