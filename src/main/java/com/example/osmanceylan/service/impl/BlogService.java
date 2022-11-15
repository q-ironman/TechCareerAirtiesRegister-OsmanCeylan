package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.retrofit.RetrofitCommonGeneric;
import com.example.osmanceylan.retrofit.request.IBlogServiceRequest;
import com.example.osmanceylan.service.IBlogService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
public class BlogService implements IBlogService {

    private final IBlogServiceRequest iBlogServiceRequest;


    @Override
    public JsonElement save(JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.save(element));
    }

    @Override
    public List<JsonElement> list() {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.list());
    }

    @Override
    public JsonElement find(Long id) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.find(id));
    }

    @Override
    public JsonElement update(Long id, JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.update(id, element));
    }

    @Override
    public void delete(Long id) {
        RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.delete(id));
    }
}
