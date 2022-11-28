package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.retrofit.RetrofitCommonGeneric;
import com.example.osmanceylan.retrofit.request.IDailyServiceRequest;
import com.example.osmanceylan.roles.ERoles;
import com.example.osmanceylan.roles.Roles;
import com.example.osmanceylan.service.IDailyService;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
// Lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class DailyService implements IDailyService {
    private final IDailyServiceRequest iDailyServiceRequest;


    // SAVE
    @Override
    @Secured({Roles.writer,Roles.admin})
    public JsonElement save(JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.save(element));
    }
    // LIST
    @Override
    @Secured({Roles.reader,Roles.admin,Roles.writer})
    public List<JsonElement> list() {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.list());
    }

    @Override
    @Secured({Roles.reader,Roles.admin,Roles.writer})
    public JsonElement find(Long id) {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.find(id));
    }

    @Override
    @Secured({Roles.writer,Roles.admin})
    public JsonElement update(Long id, JsonElement element) {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.update(id,element));
    }

    // DELETE
    @Override
    @Secured(Roles.admin)
    public JsonElement delete(Long id) {
        return RetrofitCommonGeneric.retroitGenerics(iDailyServiceRequest.delete(id));
    }
}
