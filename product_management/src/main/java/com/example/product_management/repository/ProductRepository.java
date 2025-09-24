package com.example.product_management.repository;

import com.example.product_management.entity.Product;
import com.example.product_management.utils.ConnectionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> findAll() {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).getResultList();
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Product p = session.find(Product.class, id);
            return Optional.ofNullable(p);
        }
    }

    @Override
    public Product save(Product p) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(p);
            transaction.commit();
            return p;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Product update(Long id, Product p) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            p.setId(id);
            Product merged = (Product) session.merge(p);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Product toDelete = session.find(Product.class, id);
            if (toDelete != null) {
                session.remove(toDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Product> searchByName(String q) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            if (q == null || q.isBlank()) {
                return session.createQuery("from Product", Product.class).getResultList();
            }
            return session.createQuery(
                            "from Product p where lower(p.name) like :kw", Product.class)
                    .setParameter("kw", "%" + q.toLowerCase().trim() + "%")
                    .getResultList();
        }
    }
}
