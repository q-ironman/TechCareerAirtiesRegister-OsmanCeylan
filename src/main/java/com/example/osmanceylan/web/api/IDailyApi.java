package com.example.osmanceylan.web.api;

import com.google.gson.JsonElement;
import org.springframework.http.ResponseEntity;
import retrofit2.Response;

public interface IDailyApi {
    ResponseEntity<?> save(JsonElement element);
    ResponseEntity<?> list();
    ResponseEntity<?> delete(Long id);
}
