package com.example.osmanceylan.service;

import com.google.gson.JsonElement;

import java.util.List;

public interface IBlogService {
    //CREATE
    JsonElement save(JsonElement element);
    //LIST
    List<JsonElement> list();
    //FIND
    JsonElement find(Long id);
    //UPDATE
    JsonElement update(Long id, JsonElement element);
    //DELETE
    JsonElement delete(Long id);
}
