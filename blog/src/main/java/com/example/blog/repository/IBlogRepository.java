package com.example.blog.repository;

import com.example.blog.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogRepository {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog b);              // create
    Blog update(Long id, Blog b);   // update
    void deleteById(Long id);
    List<Blog> searchByTitle(String keyword);
}
