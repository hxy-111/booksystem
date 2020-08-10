package com.book.system.controller;

import com.book.system.entity.Admin;
import com.book.system.entity.User;
import com.book.system.service.AdminService;
import com.book.system.service.UserService;
import com.book.system.utils.AjaxResult;
import com.book.system.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Classname DispatherController
 * @Description 控制器
 */
@Controller
@RequestMapping("/manager")
public class DispatherController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

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


    /**
     * 登陆验证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/adminLogin")
    public @ResponseBody
    AjaxResult login(String users, String username, String password, HttpSession session) {
        System.out.println(users+" "+username+" "+password);
        //验证用户名和密码
        if (users.equals("admin")){
            Admin admin=adminService.adminLogin(username,password);
            if (admin!=null){
                session.setAttribute("admin",admin);
                ajaxResult.ajaxTrue("登录成功");
            } else {
                ajaxResult.ajaxFalse("登录失败");
            }
        }else if(users.equals("user")){
            User user=userService.userLogin(username,password);
            if (user!=null){
                session.setAttribute("user",user);
                ajaxResult.ajaxTrue("登录成功");
            } else {
                ajaxResult.ajaxFalse("登录失败");
            }
        }
        session.setAttribute("users",users);
        session.setAttribute("username",username);
        return ajaxResult;
    }

    /**
     * 跳转修改密码页面
     * @return
     */
    @GetMapping("/password")
    public String password(){
        return "manager/common/password";
    }

    /**
     * 修改密码
     * @param session
     * @param password
     * @param newpassword
     * @param repassword
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(HttpSession session,String password,String newpassword,String repassword){
        if(!newpassword.equals(repassword)){
            ajaxResult.ajaxFalse("密码不一致");
            return ajaxResult;
        }
        if (session.getAttribute("admin")!=null){
            Admin admin = (Admin) session.getAttribute("admin");
            System.out.println("admin111:"+admin.getAdminName());
            Admin admin1=adminService.adminLogin(admin.getAdminName(),password);
            if(admin1==null){
                ajaxResult.ajaxFalse("原密码错误");
                return ajaxResult;
            }
            admin.setAdminPwd(newpassword);
            int count = adminService.editPassword(admin);
            if(count >= 1){
                ajaxResult.ajaxTrue("修改密码成功");
            }else{
                ajaxResult.ajaxFalse("系统错误");
            }
        }else if (session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            System.out.println("user111:"+user.getUserName());
            User user1=userService.userLogin(user.getUserName(),password);
            if(user1==null){
                ajaxResult.ajaxFalse("原密码错误");
                return ajaxResult;
            }
            user.setUserPwd(newpassword);
            int count = userService.editPassword(user);
            if(count >= 1){
                ajaxResult.ajaxTrue("修改密码成功");
            }else{
                ajaxResult.ajaxFalse("系统错误");
            }
        }
        return ajaxResult;
    }


    @GetMapping("/info")
    public String info(HttpSession session, Model model){
        return "manager/common/info";
    }

    @PostMapping("/editInfo")
    @ResponseBody
    public AjaxResult editInfo(HttpSession session,String username,String phone,String email){
        if (session.getAttribute("admin")!=null){
            Admin ad = (Admin) session.getAttribute("admin");
            ad.setAdminName(username);
            ad.setAdminEmail(email);
            ad.setAdminPhone(phone);
            System.out.println("ad:"+ad);
            int count = adminService.editAdmin(ad);
            if(count >= 1){
                ajaxResult.ajaxTrue("修改成功");
            }else{
                ajaxResult.ajaxFalse("修改失败");
            }
        }else if (session.getAttribute("user")!=null){
            User ad = (User) session.getAttribute("user");
            ad.setUserName(username);
            ad.setUserEmail(email);
            ad.setUserPhone(phone);
            int count = userService.editByUser(ad);
            if(count >= 1){
                ajaxResult.ajaxTrue("修改成功");
            }else{
                ajaxResult.ajaxFalse("修改失败");
            }
        }
        return ajaxResult;
    }
}
