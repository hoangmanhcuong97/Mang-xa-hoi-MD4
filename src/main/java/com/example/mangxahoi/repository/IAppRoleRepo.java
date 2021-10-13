package com.example.mangxahoi.repository;



import com.example.mangxahoi.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends JpaRepository<AppRole,Long> {
}
