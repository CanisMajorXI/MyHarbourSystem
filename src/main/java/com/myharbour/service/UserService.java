package com.myharbour.service;

import com.myharbour.pojo.User;

public interface UserService {

    User loginById(Integer id, String password, Integer type);

    User loginByEmail(String email, String password, Integer type);

    boolean isNotDuplicate(Integer id, String email,Integer type);

    void addAnUser(Integer userId, String password, String email, Integer type);

}
