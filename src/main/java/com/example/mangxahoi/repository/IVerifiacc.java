package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.VerifiAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVerifiacc extends JpaRepository<VerifiAccount,Long> {
}
