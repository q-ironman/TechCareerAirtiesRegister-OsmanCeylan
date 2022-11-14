package com.example.osmanceylan.error;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class ApiResult {
    private String timestamp;
    private int status;
    private String message;
    private String error;
    private String path;

    private String nowDate() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MMMM.yyyy HH:mm:ss", locale);
        Date date = new Date();
        String change = simpleDateFormat.format(date);
        return change;
    }

    public ApiResult(int status,  String message, String path){
        this.timestamp = nowDate();
        this.status=status;
        this.message=message;
        this.path=path;
    }
    public ApiResult(int status,  String message, String path, String error){
        this.timestamp = nowDate();
        this.status=status;
        this.message=message;
        this.error = error;
        this.path=path;
    }

}
