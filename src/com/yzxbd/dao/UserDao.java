package com.yzxbd.dao;

import com.yzxbd.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> findAll(int page,int pageSize) throws Exception;
    int getTotalPages(int pageSize) throws Exception;
    int addUser(User user) throws Exception;
    User findById(int id) throws Exception;
    void updateUser(User user) throws Exception;
    void delUser(int id) throws Exception;
    User findByUsername(String username) throws Exception;

    List<User> queryAllUserName() throws SQLException, Exception;
}
