package com.book.system.controller;

import com.book.system.service.AdminService;
import com.book.system.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manager")
public class AdminController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private AdminService adminService;

    /**
     * 跳转管理员页面
     * @return
     */
    @GetMapping("/admin")
    public String admin(){
        return "manager/admin/adminList";
    }

    /**
     * 管理员登陆
     * @param adminName
     * @param adminPwd
     * @return
     */
    @RequestMapping("/adminLogin")
    public @ResponseBody AjaxResult login(@RequestParam(value = "username")String adminName, @RequestParam(value = "password", defaultValue = "1")String adminPwd, HttpSession session) {
        //验证用户名和密码
        if (adminService.adminLogin(adminName,adminPwd)){
            ajaxResult.ajaxTrue("登录成功");
        } else {
            ajaxResult.ajaxFalse("登录失败");
        }
        System.out.println("123:"+ajaxResult);
        session.setAttribute("username",adminName);
        return ajaxResult;
    }

}
