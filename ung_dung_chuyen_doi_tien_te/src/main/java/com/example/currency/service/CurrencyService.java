package com.example.currency.service;

import com.example.currency.model.Conversion;
import com.example.currency.repository.ICurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository repo;

    @Autowired
    public CurrencyService(ICurrencyRepository repo) {
        this.repo = repo;
    }

    @Override
    public Conversion convert(double rate, double usd) {
        return repo.convert(rate, usd);
    }
}
