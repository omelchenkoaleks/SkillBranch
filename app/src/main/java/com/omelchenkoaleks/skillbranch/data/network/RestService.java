package com.omelchenkoaleks.skillbranch.data.network;

import com.omelchenkoaleks.skillbranch.data.network.req.UserLoginReq;
import com.omelchenkoaleks.skillbranch.data.network.res.UserModelRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RestService {
    @POST("login")
    Call<UserModelRes> loginUser (@Body UserLoginReq req);

//    @Multipart
//    @POST("user/{userId}/publicValues/profilePhoto")
//    Call<UserEditPhotoRes> editUserPhoto(
//            @Path("userId") String userId,
//            @Part MultipartBody.Part bodyPart);
//
//    @GET("user/{userId}")
//    Call<UserLoginRes> checkToken(@Path("userId") String userId);
//
//    @GET("user/list?orderBy=rating")
//    Call<UserListRes> getUserList();
}
