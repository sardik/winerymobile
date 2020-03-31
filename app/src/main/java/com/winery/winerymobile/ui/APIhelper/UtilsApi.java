package com.winery.winerymobile.ui.APIhelper;

public class UtilsApi {

    public static final String BASE_URL_API = "http://203.142.86.142:4141/winery-api-ci/index.php/api/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
