package com.myharbour.service.impl;

import com.myharbour.dao.UserMapper;
import com.myharbour.pojo.User;
import com.myharbour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper = null;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public User loginById(Integer id, String password, Integer type) {
        User tempUser = new User();
        tempUser.setUserId(id);
        tempUser.setPassword(password);
        tempUser.setType(type);
        List<User> users = userMapper.getUsers(tempUser);
        if (users.size() == 0) return null;
        users.get(0).setType(type);
        return users.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public User loginByEmail(String email, String password, Integer type) {
        User tempUser = new User();
        tempUser.setEmail(email);
        tempUser.setPassword(password);
        tempUser.setType(type);
        List<User> users = userMapper.getUsers(tempUser);
        if (users.size() == 0) return null;
        users.get(0).setType(type);
        return users.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean isNotDuplicate(Integer id, String email,Integer type) {
        User user = new User();
        user.setUserId(id);
        user.setEmail(email);
        user.setType(type);
        return userMapper.getUsers(user).size() == 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void addAnUser(Integer userId, String password, String email, Integer type) {
        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        user.setType(type);
        user.setPassword(password);
        userMapper.insertUser(user);
    }
}
