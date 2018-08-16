package com.flytoyou.baseapplication.Helper;

import com.flytoyou.baseapplication.Tools.MsgLog;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp帮助类
 * @author flytoyou
 * @version 1.0.0
 * implementation 'com.squareup.okhttp3:okhttp:3.10.0'
 */
public class OkHttpHelper {

    private OkHttpHelper(){}

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder{
        private static OkHttpHelper okHttpHelper = new OkHttpHelper();
    }

    /**
     * 获取单实例
     */
    public static OkHttpHelper getInstance(){
        return SingletonHolder.okHttpHelper;
    }

    /**
     * 进行post请求
     * @param jsonObject 请求参数
     * @param url 请求地址
     * @param im 回掉函数
     */
    public void newClient(JsonObject jsonObject, String url, final OkHttpHelperImpl im){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        final Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MsgLog.e("请求异常","异常");
                im.onFailure(call,e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //MsgLog.longe("请求成功",result);
                im.onResponse(call,result);
            }
        });
    }

    /**
     * 进行get请求
     * @param url 请求地址
     * @param im 回掉函数
     */
    public void newClient(String url, final OkHttpHelperImpl im){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MsgLog.e("请求异常","异常");
                im.onFailure(call,e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MsgLog.e("请求成功",result);
                im.onResponse(call,result);
            }
        });
    }

    public interface OkHttpHelperImpl {

        void onFailure(Call call, IOException e);
        void onResponse(Call call, String response) throws IOException;

    }

}
