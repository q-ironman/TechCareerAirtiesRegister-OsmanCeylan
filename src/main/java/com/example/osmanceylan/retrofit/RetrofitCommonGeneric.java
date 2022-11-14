package com.example.osmanceylan.retrofit;

import lombok.extern.log4j.Log4j2;
import retrofit2.Call;
import retrofit2.Response;

@Log4j2
public class RetrofitCommonGeneric {
    public static <T> T retroitGenerics(Call<T> request){
        try {
            Response<T> response = request.execute();
            if (!response.isSuccessful()){
                log.error("Retrofit daily failed statusCode: {} reason: {}",response.code(),response.errorBody().string());
            }
            return response.body();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
