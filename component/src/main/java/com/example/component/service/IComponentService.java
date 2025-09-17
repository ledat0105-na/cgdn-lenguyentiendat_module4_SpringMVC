package com.example.component.service;

import com.example.component.entity.Component;

import java.util.List;

public interface IComponentService {
    Component getCurrent();

    void update(Component c);

    List<String> languages();

    List<Integer> pageSizes();
}
