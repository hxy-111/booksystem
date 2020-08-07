package com.book.system.controller;

import com.book.system.entity.Book;
import com.book.system.service.BookService;
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
public class BookController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private BookService bookService;

    /**
     * 跳转书籍页面
     * @return
     */
    @GetMapping("/book")
    public String book(Model model){
        model.addAttribute("types",bookService.queryType());
        return "manager/book/bookList";
    }

    /**
     * 异步加载图书列表
     * @param pageNo
     * @param pageSize
     * @param bookname
     * @param author
     * @return
     */
    @RequestMapping("/listBook")
    @ResponseBody
    public Object listBook(@RequestParam(value = "page", defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "limit", defaultValue = "5") Integer pageSize,
                           String bookname, String author,String categoryName,Integer state){
        //设置分页每页记录数
        PageHelper.startPage(pageNo, pageSize);
        //获取查询属性
        Map<String,Object> paramMap = new HashMap();
        //判断是否为空
        if(!StringUtils.isEmpty(bookname)) paramMap.put("bookName",bookname);
        if(!StringUtils.isEmpty(author)) paramMap.put("bookAuthor",author);
        if(!StringUtils.isEmpty(categoryName)) paramMap.put("categoryName",categoryName);
        if(!StringUtils.isEmpty(state)) paramMap.put("state",state);
        System.out.println("paramMap:"+paramMap);
        List<Map<String, Object>> listBook = bookService.queryList(paramMap);
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
     * 跳转添加书籍页面
     * @return
     */
    @GetMapping("/addBook")
    public String addBook(String type, Integer id, Model model){
        if(type != null && type.equals("edit")){
            Book book=bookService.selectById(id);
            System.out.println("book:"+book);
            model.addAttribute("book",book);
        }
        model.addAttribute("types",bookService.queryType());
        return "manager/book/addBook";
    }

    /**
     * 添加书籍 修改书籍
     * @param book
     * @return
     */
    @PostMapping("/addBook")
    @ResponseBody
    public AjaxResult submitAddAdmin(Book book, Integer state){
        if(book.getCategoryName().equals(0)){
            ajaxResult.ajaxFalse("请选择类别");
            return ajaxResult;
        }
        //设置状态
        book.setState(state!=null?1:0);
        System.out.println("book" + book);
        if(book.getBookId()!=null) {
            //修改图书
            int count = bookService.editByBook(book);
            if (count > 0) {
                ajaxResult.ajaxTrue("修改成功");
            } else {
                ajaxResult.ajaxFalse("修改失败");
            }
        }else{
            //添加图书
            int count = bookService.insertBook(book);
            if(count > 0){
                ajaxResult.ajaxTrue("添加成功");
            }else{
                ajaxResult.ajaxFalse("添加失败");
            }
        }
        return ajaxResult;
    }

    /**
     * 删除书籍
     * @param data
     * @return
     */
    @PostMapping("/delBook")
    @ResponseBody
    public AjaxResult delBook(Data data){
        System.out.println("data:"+data.getIds());
        int count = bookService.delByBookIds(data.getIds());
        if(count >= data.getIds().size()){
            ajaxResult.ajaxTrue("删除成功");
        }else{
            ajaxResult.ajaxFalse("删除失败");
        }
        return ajaxResult;
    }


}
