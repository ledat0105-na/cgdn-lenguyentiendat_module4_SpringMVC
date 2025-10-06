package com.example.blog_extend.service;

import com.example.blog_extend.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category getById(Long id);
    Category create(Category c);
    Category update(Long id, Category c);
    void delete(Long id);
}
