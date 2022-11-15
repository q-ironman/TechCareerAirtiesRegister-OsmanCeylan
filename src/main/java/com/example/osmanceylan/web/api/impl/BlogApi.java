package com.example.osmanceylan.web.api.impl;

import com.example.osmanceylan.error.ApiResult;
import com.example.osmanceylan.service.IBlogService;
import com.example.osmanceylan.web.api.IBlogApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor

@RestController
@RequestMapping("gateway/blog")
public class BlogApi implements IBlogApi {

    // Injection
    private final IBlogService service;
    private static final String PATH = "gateway/blog";

    //CREATE
    // http://localhost:6060/gateway/blog ==> POST
    @Override
    @PostMapping
    public ApiResult save(@RequestBody JsonElement element) {
        service.save(element);
        return new ApiResult(200,"Blog Created", PATH);
    }

    //LIST
    // http://localhost:6060/gateway/blog ==> GET
    @Override
    @GetMapping
    public ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(service.list());
    }

    //FIND
    // http://localhost:6060/gateway/blog ==> GET
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.find(id));
    }


    //UPDATE
    // http://localhost:6060/gateway/blog ==> PUT
    @Override
    @PutMapping("/{id}")
    public ApiResult update(@PathVariable(name = "id") Long id, @RequestBody JsonElement element) {
        service.update(id,element);
        return new ApiResult(200,"Blog Updated",PATH);
    }

    //DELETE
    // http://localhost:6060/gateway/blog ==> DELETE
    @Override
    @DeleteMapping("/{id}")
    public ApiResult delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ApiResult(200,"Blog Deleted",PATH);
    }
}
