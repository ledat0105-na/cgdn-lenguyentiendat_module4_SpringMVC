package com.example.condiments.repository;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CondimentRepository implements ICondimentRepository {

    private static final List<String> DATA = Arrays.asList(
            "Lettuce", "Tomato", "Mustard", "Sprouts", "Cheese", "Onion", "Ketchup", "Mayonnaise"
    );

    @Override
    public List<String> getAll() {
        return DATA;
    }
}
