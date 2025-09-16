package com.example.condiments.service;

import com.example.condiments.entity.CondimentSelection;
import com.example.condiments.repository.ICondimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class CondimentService implements ICondimentService {

    private final ICondimentRepository repo;

    @Autowired
    public CondimentService(ICondimentRepository repo) {
        this.repo = repo;
    }

    @Override
    public java.util.List<String> getAll() {
        return repo.getAll();
    }

    @Override
    public CondimentSelection buildSelection(String[] picked) {
        return new CondimentSelection(
                picked == null ? Collections.emptyList() : Arrays.asList(picked)
        );
    }
}
