package com.example.osmanceylan.retrofit;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfigBean {


    // Date Seperation
    @Bean
    public Gson gson(GsonBuilder gsonBuilder){
        return gsonBuilder.create();
    }
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson){
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);
        return gsonHttpMessageConverter;
    }
    @Bean
    public GsonBuilder gsonBuilder(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>)(date, type, context)->
                                date != null ? new JsonPrimitive(String.valueOf(DateTimeFormatter.ISO_LOCAL_DATE_TIME)):null)
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>)(json,type,context) ->
                        LocalDateTime.parse(json.getAsString(),DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
