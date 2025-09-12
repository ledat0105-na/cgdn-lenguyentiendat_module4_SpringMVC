package com.example.Dictionary.service;

import com.example.Dictionary.model.Word;
import com.example.Dictionary.repository.IDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService implements IDictionaryService {

    private final IDictionaryRepository repo;

    @Autowired
    public DictionaryService(IDictionaryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Word translate(String english) {
        return repo.findByEnglish(english);
    }
}
