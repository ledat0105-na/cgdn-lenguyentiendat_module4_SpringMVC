package com.example.component.repository;

import com.example.component.entity.Component;
import org.springframework.stereotype.Repository;

@Repository
public class ComponentRepository implements IComponentRepository {

    private Component stored;

    public ComponentRepository() {
        stored = new Component();
        stored.setLanguage("English");
        stored.setPageSize(25);
        stored.setSpamFilter(false);
        stored.setSignature("Thor\nKing, Asgard");
    }

    @Override
    public Component get() {
        return stored;
    }

    @Override
    public void save(Component c) {
        stored = c;
    }
}
