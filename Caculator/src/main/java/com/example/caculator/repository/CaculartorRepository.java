package com.example.caculator.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CaculartorRepository implements ICaculartRepository {

    @Override
    public Double calc(double a, double b, String op) {
        switch (op) {
            case "add":
                return a + b;
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                if (b == 0) throw new ArithmeticException("Không thể chia cho 0.");
                return a / b;
            default:
                throw new IllegalArgumentException("Phép toán không hợp lệ.");
        }
    }
}
