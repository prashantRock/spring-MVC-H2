package com.web.controller;

import com.classes.login.LoginCommand;
import com.classes.login.LoginDetailDTO;
import com.dao.LoginDetailDao;
import com.dao.UserDetailDao;
import com.model.LoginDetail;
import com.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by prashant on 8/1/18.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserDetailDao userDetailDao;

    @Autowired
    LoginDetailDao loginDetailDao;

    @RequestMapping(value = "/loginPage")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(LoginCommand loginCommand, HttpServletRequest request) {
        System.out.print(loginCommand.getEmailId());
        List<UserDetail> userDetail = userDetailDao.findByEmailAndPassword(loginCommand.getEmailId(),loginCommand.getPassword());
        if(userDetail.size()>0){
            loginDetailDao.insertLoginDetail(userDetail.get(0).getId().toString());
            userDetailDao.updateUserDetail(userDetail.get(0).getUsername(),"logIn");
            HttpSession session = request.getSession(true);
            session.setAttribute("userid", userDetail.get(0).getUsername());
        }
        ModelAndView modelAndView = new ModelAndView();
        List<LoginDetailDTO> loginDetail = loginDetailDao.findAllLoginDetail();
        modelAndView.addObject("loginDetail", loginDetail);
        modelAndView.setViewName("dashboard/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(LoginCommand loginCommand, HttpServletRequest request) {
        List<UserDetail> userDetail = userDetailDao.findByEmail(loginCommand.getEmailId());
        ModelAndView modelAndView = new ModelAndView();
        if(userDetail.size()==0){
            userDetailDao.insertUserDetail(loginCommand.getEmailId(), loginCommand.getPassword());
            modelAndView.setViewName("login/login");
        }else{
            modelAndView.setViewName("login/signUp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
    public ModelAndView signUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/signUp");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        String username = session.getAttribute("userid").toString();
        userDetailDao.updateUserDetail(username,"logOut");
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        List<LoginDetailDTO> loginDetail = loginDetailDao.findAllLoginDetail();
        modelAndView.addObject("loginDetail", loginDetail);
        modelAndView.setViewName("dashboard/dashboard");
        return modelAndView;
    }
}