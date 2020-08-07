package com.book.system.controller;

import com.book.system.entity.Book;
import com.book.system.entity.Category;
import com.book.system.service.CategoryService;
import com.book.system.utils.AjaxResult;
import com.book.system.utils.Data;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class CategoryController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转类别页面
     * @return
     */
    @GetMapping("/category")
    public String type(){
        return "manager/category/categoryList";
    }

    /**
     * 异步加载图书种类列表
     * @param pageNo
     * @param pageSize
     * @param categoryName
     * @return
     */
    @RequestMapping("/listCategory")
    @ResponseBody
    public Object listCategory(@RequestParam(value = "page", defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "limit", defaultValue = "5") Integer pageSize,
                           String categoryName){
        //设置分页每页记录数
        PageHelper.startPage(pageNo, pageSize);
        //获取查询属性
        Map<String,Object> paramMap = new HashMap();
        //判断是否为空
        if(!StringUtils.isEmpty(categoryName)) paramMap.put("categoryName",categoryName);
        System.out.println("paramMap:"+paramMap);
        List<Map<String, Object>> listBook = categoryService.queryList(paramMap);
        PageInfo<Map<String, Object>> pageInfo=new PageInfo<Map<String, Object>>(listBook);
        Map<String,Object> rest = new HashMap();
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count",pageInfo.getTotal());
        rest.put("data", pageInfo.getList());
        System.out.println("rest:"+rest);
        return rest;
    }

    /**
     * 删除书籍类型
     * @param data
     * @return
     */
    @PostMapping("/delCategory")
    @ResponseBody
    public AjaxResult delCategory(Data data){
        System.out.println("data:"+data.getIds());
        int count = categoryService.delByCategoryIds(data.getIds());
        if(count >= data.getIds().size()){
            ajaxResult.ajaxTrue("删除成功");
        }else{
            ajaxResult.ajaxFalse("删除失败");
        }
        return ajaxResult;
    }

    /**
     * 跳转添加书籍种类页面
     * @return
     */
    @GetMapping("/addCategory")
    public String addCategory(String type, Integer id, Model model){
        if(type != null && type.equals("edit")){
            Category category=categoryService.selectById(id);
            System.out.println("category:"+category.getCategoryId());
            model.addAttribute("category",category);
        }
        return "manager/category/addCategory";
    }

    /**
     * 添加书籍种类 修改书籍种类
     * @param category
     * @return
     */
    @PostMapping("/addCategory")
    @ResponseBody
    public AjaxResult submitAddCategory(Category category){
        if(category.getCategoryId()!=null) {
            //修改图书
            int count = categoryService.editByCategory(category);
            if (count > 0) {
                ajaxResult.ajaxTrue("修改成功");
            } else {
                ajaxResult.ajaxFalse("修改失败");
            }
        }else{
            //添加图书
            int count = categoryService.insertCategory(category);
            if(count > 0){
                ajaxResult.ajaxTrue("添加成功");
            }else{
                ajaxResult.ajaxFalse("添加失败");
            }
        }
        return ajaxResult;
    }

}
