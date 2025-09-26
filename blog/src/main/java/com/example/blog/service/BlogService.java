package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.IBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    private final IBlogRepository repo;

    public BlogService(IBlogRepository repo) {
        this.repo = repo;
    }

    @Override public List<Blog> findAll() { return repo.findAll(); }
    @Override public Optional<Blog> findById(Long id) { return repo.findById(id); }
    @Override public Blog create(Blog b) { return repo.save(b); }
    @Override public Blog update(Long id, Blog b) { return repo.update(id, b); }
    @Override public void deleteById(Long id) { repo.deleteById(id); }
    @Override public List<Blog> search(String keyword) { return repo.searchByTitle(keyword); }
}
