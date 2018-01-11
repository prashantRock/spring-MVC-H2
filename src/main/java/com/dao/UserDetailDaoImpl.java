package com.dao;

import com.model.User;
import com.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prashant on 10/1/18.
 */
@Repository
public class UserDetailDaoImpl  implements UserDetailDao{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<UserDetail> findByEmailAndPassword(String email, String password) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", email);
        params.put("password", password);

        String sql = "SELECT * FROM userDetail WHERE username=:username AND password=:password";

        List<UserDetail> result = namedParameterJdbcTemplate.query(
                sql,
                params,
                new UserDetailDaoImpl.UserMapper());
        return result;
    }

    @Override
    public List<UserDetail> findByEmail(String email) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", email);

        String sql = "SELECT * FROM userDetail WHERE username=:username";

        List<UserDetail> result = namedParameterJdbcTemplate.query(
                sql,
                params,
                new UserDetailDaoImpl.UserMapper());
        return result;
    }


    @Override
    public void insertUserDetail(String username, String password) {
        String loginStatus = "login";
        String query = "INSERT INTO userDetail (username, password, loginStatus) VALUES (:username,:password,:loginStatus)";
        Map namedParameters = new HashMap();
        namedParameters.put("username", username);
        namedParameters.put("password", password);
        namedParameters.put("loginStatus", loginStatus);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public void updateUserDetail(String username,String loginStatus) {
        String query = "UPDATE userDetail SET loginStatus = :loginStatus WHERE  username=:username";
        Map namedParameters = new HashMap();
        namedParameters.put("username", username);
        namedParameters.put("loginStatus", loginStatus);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public List<UserDetail> findAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT * FROM userDetail";
        List<UserDetail> result = namedParameterJdbcTemplate.query(sql, params, new UserDetailDaoImpl.UserMapper());
        return result;
    }

    private static final class UserMapper implements RowMapper<UserDetail> {

        public UserDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDetail userDetail = new UserDetail();
            userDetail.setId(rs.getInt("id"));
            userDetail.setUsername(rs.getString("username"));
            userDetail.setPassword(rs.getString("password"));
            return userDetail;
        }
    }


}
