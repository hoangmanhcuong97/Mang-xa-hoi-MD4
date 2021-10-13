package com.example.mangxahoi.service.verify;

import com.example.mangxahoi.model.VerifiAccount;
import com.example.mangxahoi.repository.IVerifiacc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifiService implements IVerifiService {
    @Autowired
    private IVerifiacc verifiacc;


    public VerifiAccount add(VerifiAccount verifiAccount) {
        return verifiacc.save(verifiAccount);
    }

    @Override
    public Iterable<VerifiAccount> findAll() {
        return null;
    }

    @Override
    public Optional<VerifiAccount> findById(Long id) {
        return verifiacc.findById(id);
    }

    @Override
    public void save(VerifiAccount verifiAccount) {

    }

    @Override
    public void remove(Long id) {

    }
}
