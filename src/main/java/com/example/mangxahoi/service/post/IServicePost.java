package com.example.mangxahoi.service.post;

import com.example.mangxahoi.model.Post;
import com.example.mangxahoi.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicePost extends IGeneralService<Post> {
    List<Post> findAllByAccount_Id(Long idAcc);
    Post add(Post post);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findPostByPrivacyContaining(String optional, Pageable pageable);
    List<Post> findAllByAccount_IdAndPrivacyIsNotContaining(Long idAcc, String privacy);
}
