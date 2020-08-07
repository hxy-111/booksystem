package com.book.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Classname DispatherController
 * @Description 控制器
 */
@Controller
@RequestMapping("/manager")
public class DispatherController {


    /**
     * 跳转登录界面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "manager/login";
    }


    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "manager/login";
    }

}
