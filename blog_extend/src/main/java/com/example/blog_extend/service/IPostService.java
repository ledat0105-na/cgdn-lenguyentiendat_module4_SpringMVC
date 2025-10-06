package com.example.blog_extend.service;

import com.example.blog_extend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPostService {
    Page<Post> findAll(Pageable pageable);
    Page<Post> search(String keyword, Pageable pageable);
    Page<Post> findByCategoryId(Long categoryId, Pageable pageable);

    Optional<Post> findById(Long id);
    Post create(Post post);
    Post update(Long id, Post post);
    void deleteById(Long id);
}
