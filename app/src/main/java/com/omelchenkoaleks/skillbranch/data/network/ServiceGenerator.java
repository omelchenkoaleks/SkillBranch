package com.omelchenkoaleks.skillbranch.data.network;

import com.omelchenkoaleks.skillbranch.utils.AppConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public  static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
