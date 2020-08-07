package com.book.system.service;

import com.book.system.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Map<String,Object>> queryList(Map<String, Object> paramMap);

    int delByCategoryIds(List<Integer> ids);

    Category selectById(Integer id);

    int insertCategory(Category category);

    int editByCategory(Category category);
}
