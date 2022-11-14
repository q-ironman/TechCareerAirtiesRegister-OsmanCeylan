package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.retrofit.RetrofitCommonGeneric;
import com.example.osmanceylan.retrofit.request.IDailyServiceRequest;
import com.example.osmanceylan.service.IDailyService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
// Lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class DailyService implements IDailyService {
    private final IDailyServiceRequest iDailyServiceRequest;


    // SAVE
    @Override
    public JsonElement save(JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.save(element));
    }
    // LIST
    @Override
    public List<JsonElement> list() {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.list());
    }
    // DELETE
    @Override
    public void delete(Long id) {
        RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.delete(id));
    }
}
