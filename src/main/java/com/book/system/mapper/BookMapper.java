package com.book.system.mapper;

import com.book.system.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Map<String,Object>> queryList(Map<String, Object> paramMap);

    List<Map<String,Object>> queryType();

    int delByBookIds(List<Integer> ids);

    Book selectById(Integer id);

    int insertBook(Book book);

    int editByBook(Book book);
}
