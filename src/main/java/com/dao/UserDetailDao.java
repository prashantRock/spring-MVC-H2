package com.dao;

import com.model.UserDetail;

import java.util.List;

public interface UserDetailDao{

    List<UserDetail> findByEmailAndPassword(String email, String password);

    List<UserDetail> findAll();

    public void insertUserDetail(String email, String password);

    public void updateUserDetail(String username,String loginStatus);

    public List<UserDetail> findByEmail(String email);
}
