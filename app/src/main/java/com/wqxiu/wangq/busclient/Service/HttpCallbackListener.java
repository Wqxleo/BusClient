package com.wqxiu.wangq.busclient.Service;

/**
 * Created by wangq on 2018/4/25.
 */

public interface HttpCallbackListener {
    void onFinish(String response);//当服务器成功响应的时候会调用该方法

    void onError(Exception e);//当进行网络操作错误的时候会调用该方法

}
