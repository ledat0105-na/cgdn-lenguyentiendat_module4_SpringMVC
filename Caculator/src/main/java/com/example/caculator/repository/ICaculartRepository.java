package com.example.caculator.repository;

public interface ICaculartRepository {
    Double calc(double a, double b, String op) throws ArithmeticException, IllegalArgumentException;
}
