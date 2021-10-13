package com.example.mangxahoi.service.comment;

import com.example.mangxahoi.model.Comment;
import com.example.mangxahoi.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceComment implements IServiceComment{
    @Autowired
    private ICommentRepo commentRepo;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepo.deleteById(id);
    }
}
