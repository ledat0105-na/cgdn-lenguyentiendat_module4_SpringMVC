package com.example.Dictionary.repository;

import com.example.Dictionary.model.Word;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryRepository implements IDictionaryRepository {

    private final Map<String, Word> data = new HashMap<>();

    public DictionaryRepository() {
        put("hello", "xin chào");
        put("goodbye", "tạm biệt");
        put("book", "sách");
        put("computer", "máy tính");
        put("keyboard", "bàn phím");
        put("mouse", "chuột");
        put("phone", "điện thoại");
        put("table", "cái bàn");
        put("chair", "cái ghế");
        put("love", "tình yêu");
    }

    private void put(String en, String vi) {
        data.put(en, new Word(en, vi));
    }

    @Override
    public Word findByEnglish(String english) {
        if (english == null) return null;
        return data.get(english.trim().toLowerCase());
    }
}
