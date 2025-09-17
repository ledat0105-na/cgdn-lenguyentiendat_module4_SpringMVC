package com.example.component.service;

import com.example.component.entity.Component;
import com.example.component.repository.IComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ComponentService implements IComponentService {

    private final IComponentRepository repo;

    @Autowired
    public ComponentService(IComponentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Component getCurrent() {
        return repo.get();
    }

    @Override
    public void update(Component c) {
        repo.save(c);
    }

    @Override
    public List<String> languages() {
        return Arrays.asList("English", "Vietnamese", "Japanese", "French");
    }

    @Override
    public List<Integer> pageSizes() {
        return Arrays.asList(5, 10, 25, 50, 100);
    }
}
