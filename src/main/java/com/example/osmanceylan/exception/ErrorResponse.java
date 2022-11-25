package com.example.osmanceylan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.api.ErrorMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String timestamp;

    private String nowDate() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MMMM.yyyy HH:mm:ss", locale);
        Date date = new Date();
        String change = simpleDateFormat.format(date);
        return change;
    }

    public ErrorResponse(int statusCode,String message)
    {
        this.statusCode = statusCode;
        this.timestamp = nowDate();
        this.message = message;
    }
}
