package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.retrofit.RetrofitCommonGeneric;
import com.example.osmanceylan.retrofit.request.IBlogServiceRequest;
import com.example.osmanceylan.roles.Roles;
import com.example.osmanceylan.service.IBlogService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
public class BlogService implements IBlogService {

    private final IBlogServiceRequest iBlogServiceRequest;


    @Override
    @Secured({Roles.writer,Roles.admin})
    public JsonElement save(JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.save(element));
    }

    @Override
    @Secured({Roles.reader,Roles.admin,Roles.writer})
    public List<JsonElement> list() {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.list());
    }

    @Override
    @Secured({Roles.reader,Roles.admin,Roles.writer})
    public JsonElement find(Long id) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.find(id));
    }

    @Override
    @Secured({Roles.writer,Roles.admin})
    public JsonElement update(Long id, JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.update(id, element));
    }

    @Override
    @Secured(Roles.admin)
    public JsonElement delete(Long id) {
       return RetrofitCommonGeneric.retroitGenerics(iBlogServiceRequest.delete(id));
    }
}
