package com.example.component.repository;

import com.example.component.entity.Component;

public interface IComponentRepository {
    Component get();

    void save(Component c);
}
