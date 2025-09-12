package com.example.currency.repository;

import com.example.currency.model.Conversion;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyRepository implements ICurrencyRepository {
    @Override
    public Conversion convert(double rate, double usd) {
        return new Conversion(rate, usd);
    }
}
