package com.example.currency.repository;

import com.example.currency.model.Conversion;

public interface ICurrencyRepository {
    Conversion convert(double rate, double usd);
}
