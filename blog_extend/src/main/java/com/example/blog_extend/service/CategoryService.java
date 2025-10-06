package com.example.blog_extend.service;

import com.example.blog_extend.entity.Category;
import com.example.blog_extend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
    
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
    
    @Override
    public Category update(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }
    
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
