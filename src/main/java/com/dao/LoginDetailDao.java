package com.dao;

import com.classes.login.LoginDetailDTO;

import java.util.List;

/**
 * Created by prashant on 10/1/18.
 */
public interface LoginDetailDao {

    public void insertLoginDetail(String userId);

    public List<LoginDetailDTO> findAllLoginDetail();

}
