package com.example.product_management.repository;

import com.example.product_management.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> q = em.createQuery("from Product", Product.class);
        return q.getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product p = em.find(Product.class, id);
        return Optional.ofNullable(p);
    }

    @Override
    @Transactional
    public Product save(Product p) {
        em.persist(p);
        return p;
    }

    @Override
    @Transactional
    public Product update(Long id, Product p) {
        p.setId(id);
        return em.merge(p);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product ref = em.find(Product.class, id);
        if (ref != null) {
            em.remove(ref);
        }
    }

    @Override
    public List<Product> searchByName(String q) {
        if (q == null || q.isBlank()) {
            return findAll();
        }
        return em.createQuery(
                        "from Product p where lower(p.name) like :kw", Product.class)
                .setParameter("kw", "%" + q.toLowerCase().trim() + "%")
                .getResultList();
    }
}
