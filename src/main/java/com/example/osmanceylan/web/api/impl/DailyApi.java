package com.example.osmanceylan.web.api.impl;

import com.example.osmanceylan.error.ApiResult;
import com.example.osmanceylan.service.IDailyService;
import com.example.osmanceylan.web.api.IDailyApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor


@RestController
@RequestMapping("gateway/daily")
public class DailyApi implements IDailyApi {
    // Injection
    private final IDailyService service;

    private static final String PATH = "gateway/daily";

    //CREATE
    // http://localhost:6060/gateway/daily ==> POST
    @Override
    @PostMapping
    public ApiResult save(@RequestBody JsonElement element) {
        service.save(element);
        return new ApiResult(200,"Daily Created",PATH);
    }

    //LIST
    // http://localhost:6060/gateway/daily ==> GET
    @Override
    @GetMapping
    public ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(service.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @Override
    @PutMapping("/{id}")
    public ApiResult update(@PathVariable(name = "id") Long id, @RequestBody JsonElement element) {
        service.update(id,element);
        return new ApiResult(200,"Updated",PATH);
    }

    //DELETE
    // http://localhost:6060/gateway/daily ==> DELETE
    @Override
    @DeleteMapping("/{id}")
    public ApiResult delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ApiResult(200,"Deleted",PATH);
    }
}
