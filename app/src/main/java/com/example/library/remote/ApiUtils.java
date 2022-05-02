package com.example.library.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://3.0.59.80/test/public/";
    //địa chỉ tới cloud máy ảo lưu trữ database

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
