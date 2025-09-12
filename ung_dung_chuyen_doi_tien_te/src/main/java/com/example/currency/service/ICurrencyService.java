package com.example.currency.service;

import com.example.currency.model.Conversion;

public interface ICurrencyService {
    Conversion convert(double rate, double usd);
}
