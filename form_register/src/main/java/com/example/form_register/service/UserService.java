package com.example.form_register.service;

import com.example.form_register.dto.UserDTO;
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
    public User register(UserDTO userDTO) {
        if (repo.existsByEmailIgnoreCase(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (repo.existsByPhonenumber(userDTO.getPhonenumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        
        // Convert DTO to Entity
        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .phonenumber(userDTO.getPhonenumber())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .build();
                
        return repo.save(user);
    }
}
