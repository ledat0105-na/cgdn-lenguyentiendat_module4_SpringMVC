package com.example.Dictionary.repository;

import com.example.Dictionary.model.Word;

public interface IDictionaryRepository {
    Word findByEnglish(String english);
}
