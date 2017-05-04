package com.yswcustomizeview.mRxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by： Ysw on 2016/7/8.16:56.
 */
public class HttpUtil {
    private Retrofit mRetrofit;
    private Service mService;
    private static final int DEFAULT_TIMEOUT = 5;

    private HttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Url.BASEURL)
                .build();
        mService = mRetrofit.create(Service.class);
    }

    // 获取单例 @author Ysw created at 2016/7/8 17:18
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 在访问HttpUtil时创建单例 @author Ysw created at 2016/7/8 17:19
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

//    public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
//
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getReturnResult() == 0) {
//                throw new ApiException(100);
//            }
//            return httpResult.getData();
//        }
//    }


//    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{
    private class HttpResultFunc<T> implements rx.functions.Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getReturnResult() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getData();
        }
    }
}
