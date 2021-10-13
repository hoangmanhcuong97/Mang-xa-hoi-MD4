package com.example.mangxahoi.service.account;


import com.example.mangxahoi.model.Account;
import com.example.mangxahoi.service.IGeneralService;

public interface IServiceAccount extends IGeneralService<Account> {
     Account loadUserByEmail(String email);


     boolean checkLogin(Account account);

     boolean add(Account account);

}
