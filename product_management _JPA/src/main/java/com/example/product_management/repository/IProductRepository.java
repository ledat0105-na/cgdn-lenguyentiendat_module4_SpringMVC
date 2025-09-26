package com.example.product_management.repository;

import com.example.product_management.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product p);

    Product update(Long id, Product p);

    void deleteById(Long id);

    List<Product> searchByName(String q);
}
