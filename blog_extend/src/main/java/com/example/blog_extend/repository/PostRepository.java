package com.example.blog_extend.repository;

import com.example.blog_extend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"category"})
    Page<Post> findByTitleContainingIgnoreCase(String q, Pageable pageable);

    @EntityGraph(attributePaths = {"category"})
    Page<Post> findByCategory_Id(Long categoryId, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Post> findAll(Pageable pageable);
}
