package com.example.product_management.repository;

import com.example.product_management.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    private final Map<Long, Product> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public ProductRepository() {
        save(new Product(null, "iPhone 15", new BigDecimal("999.00"), "Flagship", "Apple"));
        save(new Product(null, "Galaxy S24", new BigDecimal("899.00"), "Android high-end", "Samsung"));
        save(new Product(null, "ThinkPad X1", new BigDecimal("1599.00"), "Business laptop", "Lenovo"));
        save(new Product(null, "SamSung", new BigDecimal("1575.00"), "Ipad", "Lenovo"));
        save(new Product(null, "Oppo", new BigDecimal("1799.00"), "Phone", "Lenovo"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Product save(Product p) {
        long id = seq.incrementAndGet();
        p.setId(id);
        store.put(id, p);
        return p;
    }

    @Override
    public Product update(Long id, Product p) {
        if (!store.containsKey(id)) return null;
        p.setId(id);
        store.put(id, p);
        return p;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Product> searchByName(String q) {
        if (q == null || q.isBlank()) return findAll();
        String kw = q.trim().toLowerCase();
        return store.values().stream()
                .filter(pr -> pr.getName() != null && pr.getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }
}
