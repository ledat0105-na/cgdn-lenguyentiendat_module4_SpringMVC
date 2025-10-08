package com.example.form_register.service;

import com.example.form_register.model.User;
import com.example.form_register.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    private final IUserRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User register(User user) {
        if (repo.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (repo.existsByPhonenumber(user.getPhonenumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        return repo.save(user);
    }
}
