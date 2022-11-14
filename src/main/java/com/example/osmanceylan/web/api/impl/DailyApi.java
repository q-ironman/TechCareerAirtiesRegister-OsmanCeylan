package com.example.osmanceylan.web.api.impl;

import com.example.osmanceylan.service.IDailyService;
import com.example.osmanceylan.web.api.IDailyApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor


@RestController
@RequestMapping("gateway/daily")
public class DailyApi implements IDailyApi {
    // Injection
    private final IDailyService service;

    //CREATE
    // http://localhost:6060/gateway/daily ==> POST
    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody JsonElement element) {
        return ResponseEntity.ok(service.save(element));
    }

    //LIST
    // http://localhost:6060/gateway/daily ==> GET
    @Override
    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }
    //DELETE
    // http://localhost:6060/gateway/daily ==> DELETE
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
