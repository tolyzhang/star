package cn.stylefeng.star.modular.welcome.controller;


import cn.stylefeng.star.modular.bussines.entity.TUser;
import cn.stylefeng.star.modular.bussines.service.TUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@Slf4j
public class loginsController {

    @Autowired
    private TUserService tUserService;

    private String PREFIX = "/welcome/";

/*    @RequestMapping(value="/overs",method = RequestMethod.GET)
    public String overs(Model model){
        model. addAttribute("msg", "");
        return PREFIX +"logo.html";
    }*/




  /*  @RequestMapping("/success")
    public String success(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String isLogin  = session.getAttribute("isLogin")+"";
        if(("1").equals(isLogin)){
            model.addAttribute("isLogin",isLogin);
            model.addAttribute("userName",session.getAttribute("userName"));
        }
        return PREFIX +"success.html";
    }*/
}
