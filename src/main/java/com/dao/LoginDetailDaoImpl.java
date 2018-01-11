package com.dao;

import com.classes.login.LoginDetailDTO;
import com.model.LoginDetail;
import com.model.User;
import com.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by prashant on 10/1/18.
 */
@Repository
public class LoginDetailDaoImpl implements LoginDetailDao{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertLoginDetail(String userId){
        String lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String query = "INSERT INTO loginDetail (userId, lastLogin) VALUES (:userId,:lastLogin)";
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("lastLogin", lastLogin);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public List<LoginDetailDTO> findAllLoginDetail() {
        String sql = "SELECT userDetail.username , userDetail.loginStatus, loginDetail.lastLogin FROM userDetail inner join loginDetail\n" +
                "WHERE userDetail.id = loginDetail.userId";
        List<LoginDetailDTO> result = namedParameterJdbcTemplate.query(sql, new LoginDetailDaoImpl.LoginDetailMapper());
        return result;
    }

    private static final class LoginDetailMapper implements RowMapper<LoginDetailDTO> {

        public LoginDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            LoginDetailDTO loginDetail = new LoginDetailDTO();
            loginDetail.setLastLogin(rs.getString("lastLogin"));
            loginDetail.setLoginStatus(rs.getString("loginStatus"));
            loginDetail.setUsername(rs.getString("username"));
            return loginDetail;
        }
    }
}
