package com.idolmedia.yzy.api.retrofit;


import android.util.Log;

import com.idolmedia.yzy.api.exception.NullOnEmptyConverterFactory;
import com.idolmedia.yzy.api.exception.ToStringConverterFactory;
import com.idolmedia.yzy.utlis.CleanMessageUtil;
import com.mumu.common.base.BaseApplication;
import com.mumu.common.utils.LogUtil;
import com.mumu.common.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {


    private Retrofit retrofit;
    private static OkHttpClient okHttpClient = null;

    private static YZYAPi mapi;
    private static RetrofitHttp retrofitHelper;

    private RetrofitHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "ACache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //200Mb
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                int tryCount = 0;
                Response response = chain.proceed(request);
                // response.body().contentType().charset(Charset.forName("utf-8"));
                while (!response.isSuccessful() && tryCount < 3) {

                    LogUtil.logd("RetrofitHttp", "interceptRequest is not successful - :{}");

                    tryCount++;

                    // retry the request
                    response = chain.proceed(request);
                }


                if (NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .addNetworkInterceptor(cacheInterceptor)//设置网络拦截器
                //.addInterceptor(cacheInterceptor)
                .addInterceptor(loggingInterceptor)//设置日志拦截器
                .cache(cache)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mapi = retrofit.create(YZYAPi.class);

    }


    //获取单例
    public static YZYAPi getInstance() {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHttp();
        }
        return retrofitHelper.mapi;
    }



    public static List<MultipartBody.Part> onUpload(HashMap<String, Object> map, List<String> list) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        if (list != null && list.size() > 0)
            for (int i=0;i<list.size();i++){
                File file =new File(list.get(i));
                RequestBody body =RequestBody.create(MediaType.parse("multipart/form-data"),file);
                builder.addFormDataPart("head_photo"+i,file.getName(),body);
               // Log.e("file.getName()", CleanMessageUtil.getFormatSize(file.getName().getBytes().length)+"");
                    //builder.addPart(body);
            }
            for(String key:map.keySet()){
                builder.addFormDataPart(key,map.get(key).toString());
            }
        return builder.build().parts();
    }

}