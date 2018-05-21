package com.myharbour.dao;

import com.myharbour.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserMapper {

    //新增一个User
    void insertUser(User user);

    //根据id返回User
    User getUserById(Integer id);

    //泛用性查询，根据任意一个参数返回任意一个参数
    List<User> getUsers(User user);

  //  删除一条数据设置标志位valid为false即可，不必直接从数据库清除
    void updateUser(User user);
}
