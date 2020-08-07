package com.book.system.service.Impl;

import com.book.system.entity.Category;
import com.book.system.mapper.CategoryMapper;
import com.book.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> paramMap) {
        return categoryMapper.queryList(paramMap);
    }

    @Override
    public int delByCategoryIds(List<Integer> ids) {
        return categoryMapper.delByCategoryIds(ids);
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int editByCategory(Category category) {
        return categoryMapper.editByCategory(category);
    }
}
