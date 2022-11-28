package com.example.osmanceylan.web.api;

import com.example.osmanceylan.error.ApiResult;
import com.google.gson.JsonElement;
import org.springframework.http.ResponseEntity;
import retrofit2.Response;

import java.util.List;

public interface IDailyApi {
    ApiResult save(JsonElement element);
    ResponseEntity<List<?>> list();
    ResponseEntity<?> find(Long id);
    ResponseEntity<?> update(Long id,JsonElement element);
    ResponseEntity<?> delete(Long id);
}
