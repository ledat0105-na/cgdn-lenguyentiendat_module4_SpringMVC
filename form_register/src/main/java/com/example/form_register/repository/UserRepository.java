package com.example.form_register.repository;

import com.example.form_register.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final IUserRepository jpa;

    public List<User> findAll() { return jpa.findAll(); }
    public User save(User u) { return jpa.save(u); }

    public boolean existsByEmailIgnoreCase(String email) {
        return jpa.existsByEmailIgnoreCase(email);
    }
    public boolean existsByPhonenumber(String phone) {
        return jpa.existsByPhonenumber(phone);
    }
}
