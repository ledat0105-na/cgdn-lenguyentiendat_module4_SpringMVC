package com.example.Dictionary.service;

import com.example.Dictionary.model.Word;

public interface IDictionaryService {
    Word translate(String english);
}
