package com.example.mangxahoi.service.verify;

import com.example.mangxahoi.model.VerifiAccount;
import com.example.mangxahoi.service.IGeneralService;

public interface IVerifiService extends IGeneralService<VerifiAccount> {
    VerifiAccount add(VerifiAccount verifiAccount);
}
