package com.example.osmanceylan.web.api;

import com.example.osmanceylan.error.ApiResult;
import com.google.gson.JsonElement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBlogApi {
    ApiResult save(JsonElement element);
    ResponseEntity<List<?>> list();
    ResponseEntity<?> find(Long id);
    ApiResult update(Long id,JsonElement element);
    ApiResult delete(Long id);
}
