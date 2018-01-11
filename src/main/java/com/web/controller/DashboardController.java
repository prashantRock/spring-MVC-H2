package com.web.controller;

import com.classes.login.LoginCommand;
import com.classes.login.LoginDetailDTO;
import com.dao.LoginDetailDao;
import com.dao.UserDetailDao;
import com.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {


    @Autowired
    LoginDetailDao loginDetailDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String dashboard( ) {
        return "dashboard/dashboard";
    }

    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView signUp(LoginCommand loginCommand, HttpServletRequest request) {
        System.out.print(loginCommand.getEmailId());
        ModelAndView modelAndView = new ModelAndView();
        List<LoginDetailDTO> loginDetail = loginDetailDao.findAllLoginDetail();
        modelAndView.addObject("loginDetail", loginDetail);
        modelAndView.setViewName("dashboard/dashboard");
        return modelAndView;
    }


}
