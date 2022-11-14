package com.example.osmanceylan.service;

import com.google.gson.JsonElement;

import java.util.List;

public interface IDailyService {
    //CREATE
    JsonElement save(JsonElement element);
    //LIST
    List<JsonElement> list();
    //DELETE
    void delete(Long id);
}
