package com.example.blog_extend.service;

import com.example.blog_extend.entity.Post;
import com.example.blog_extend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    
    private final PostRepository postRepository;
    
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
    
    @Override
    public Page<Post> search(String keyword, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
    
    @Override
    public Page<Post> findByCategoryId(Long categoryId, Pageable pageable) {
        return postRepository.findByCategory_Id(categoryId, pageable);
    }
    
    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    
    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }
    
    @Override
    public Post update(Long id, Post post) {
        post.setId(id);
        return postRepository.save(post);
    }
    
    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}