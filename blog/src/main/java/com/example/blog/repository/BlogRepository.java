package com.example.blog.repository;

import com.example.blog.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BlogRepository implements IBlogRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        return em.createQuery("from Blog", Blog.class).getResultList();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.ofNullable(em.find(Blog.class, id));
    }

    @Override
    @Transactional
    public Blog save(Blog b) {
        em.persist(b);       // gán id nếu @GeneratedValue
        return b;
    }

    @Override
    @Transactional
    public Blog update(Long id, Blog b) {
        b.setId(id);
        return em.merge(b);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Blog ref = em.find(Blog.class, id);
        if (ref != null) em.remove(ref);
    }

    @Override
    public List<Blog> searchByTitle(String keyword) {
        if (keyword == null || keyword.isBlank()) return findAll();
        return em.createQuery(
                        "from Blog b where lower(b.title) like :kw", Blog.class)
                .setParameter("kw", "%" + keyword.toLowerCase().trim() + "%")
                .getResultList();
    }
}