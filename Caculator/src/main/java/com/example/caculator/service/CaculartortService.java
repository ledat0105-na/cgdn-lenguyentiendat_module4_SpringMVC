package com.example.caculator.service;

import com.example.caculator.entity.Caculator;
import com.example.caculator.repository.ICaculartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaculartortService implements ICaculartService {

    private final ICaculartRepository repo;

    @Autowired
    public CaculartortService(ICaculartRepository repo) {
        this.repo = repo;
    }

    @Override
    public Caculator calculate(Caculator form) {
        try {
            Double rs = repo.calc(form.getA(), form.getB(), form.getOp());
            form.setResult(rs);
            form.setError(null);
        } catch (Exception ex) {
            form.setResult(null);
            form.setError(ex.getMessage());
        }
        return form;
    }
}
