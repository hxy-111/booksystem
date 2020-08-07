package com.book.system.controller;

import com.book.system.entity.TreeMenu;
import com.book.system.service.TreeMenuService;
import com.book.system.utils.AjaxResult;
import com.book.system.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @Classname ManagerController
 * @Description 后台控制器
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private TreeMenuService treeMenuService;


    /**
     * 跳转后台页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "manager/index";
    }


    /**
     * 异步加载权限树
     * @param session
     * @return
     */
    @PostMapping("/treeMenu")
    @ResponseBody
    public Object treeMenu(HttpSession session){
        List<TreeMenu> treeMenuList = treeMenuService.selectByAdminId();
        session.setAttribute(Const.TREEMENU,treeMenuList);
        return treeMenuList;
    }

    /**
     * 异步加载后台主页
     * @return
     */
    @GetMapping("/console")
    public String console(){
        return "manager/console";
    }

}
