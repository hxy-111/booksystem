package com.book.system.service.Impl;

import com.book.system.entity.Book;
import com.book.system.mapper.BookMapper;
import com.book.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> paramMap) {
        return bookMapper.queryList(paramMap);
    }

    @Override
    public List<Map<String, Object>> queryType() {
        return bookMapper.queryType();
    }

    @Override
    public int delByBookIds(List<Integer> ids) {
        return bookMapper.delByBookIds(ids);
    }

    @Override
    public Book selectById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public int editByBook(Book book) {
        return bookMapper.editByBook(book);
    }


}
