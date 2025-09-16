package com.example.condiments.service;

import com.example.condiments.entity.CondimentSelection;

import java.util.List;

public interface ICondimentService {
    List<String> getAll();
    CondimentSelection buildSelection(String[] picked);
}
