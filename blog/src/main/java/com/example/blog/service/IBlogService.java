package com.example.blog.service;

import com.example.blog.entity.Blog;
import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog create(Blog b);
    Blog update(Long id, Blog b);
    void deleteById(Long id);
    List<Blog> search(String keyword);
}
