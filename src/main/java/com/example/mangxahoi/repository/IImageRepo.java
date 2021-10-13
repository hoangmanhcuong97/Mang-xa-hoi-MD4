package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepo extends JpaRepository<Image,Long> {
}
