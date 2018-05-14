package com.example.chen.myanimator;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by chen on 2018/4/25.
 */

 interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();

    @Headers({"Content-Type: application/json"})
    @POST("dwapp/dlzc/dlzc/zhdlCx")
    Call<User>getUser(@Body RequestBody body);
}
