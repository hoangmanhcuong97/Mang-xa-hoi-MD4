package com.example.mangxahoi.service.accountlike;


import com.example.mangxahoi.model.AccountLike;
import com.example.mangxahoi.service.IGeneralService;

public interface IServiceLike extends IGeneralService<AccountLike> {
    AccountLike findByAccount_IdAndPost_Id(Long idAcc,Long idPost);
    public void delete(AccountLike accountLike);
}
