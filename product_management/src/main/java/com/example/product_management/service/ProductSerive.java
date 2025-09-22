package com.example.product_management.service;

import com.example.product_management.entity.Product;
import com.example.product_management.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSerive implements IProductSerice {

    private final IProductRepository repo;

    @Autowired
    public ProductSerive(IProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product create(Product p) {
        return repo.save(p);
    }

    @Override
    public Product update(Long id, Product p) {
        return repo.update(id, p);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Product> searchByName(String q) {
        return repo.searchByName(q);
    }
}
