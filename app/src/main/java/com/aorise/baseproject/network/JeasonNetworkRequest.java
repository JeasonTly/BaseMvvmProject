package com.aorise.baseproject.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class JeasonNetworkRequest {
    public static ApiService getInstance(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NetworkURLConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiService.class);
    }
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(NetworkTimeBuildConfig.CONNECT_TIME, TimeUnit.SECONDS)//超时时间
            .writeTimeout(NetworkTimeBuildConfig.CONNECT_TIME, TimeUnit.SECONDS)
            .readTimeout(NetworkTimeBuildConfig.CONNECT_TIME, TimeUnit.SECONDS)
            .build();
    public static Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
