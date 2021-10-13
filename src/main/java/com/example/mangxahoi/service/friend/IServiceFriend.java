package com.example.mangxahoi.service.friend;


import com.example.mangxahoi.model.Account;
import com.example.mangxahoi.model.Friend;
import com.example.mangxahoi.service.IGeneralService;

import java.util.List;

public interface IServiceFriend extends IGeneralService<Friend> {
    Friend findByAccount_IdAndAccount_Id(Account account, Account friend);
    List<Friend> findAllByIdAcc(Account account, Boolean status1, Account friend, Boolean status2);
    List<Friend> findFriendByIdAcc(Account account, Boolean status1);
}
