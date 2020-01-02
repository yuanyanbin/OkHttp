package com.yyb.httputils.remote;

import android.content.Context;

import com.yyb.httputils.util.CommonUtil;

import java.util.HashMap;

import static com.yyb.httputils.constant.Constants.TYPE_POST_FORM;
import static com.yyb.httputils.constant.Constants.getAppVersion;


/**
 * Copyright (C)
 * FileName: HttpManager
 * Author: 员外
 * Date: 2019-05-23 16:53
 * Description: TODO<Java类描述>
 * Version: 1.0
 */
public class HttpManager {
    public static HttpManager mInstance = null;
    public OKHttpRequest okHttpRequest;
    public Context mContext;

    public HttpManager(Context context) {
        this.mContext = context;
        okHttpRequest = OKHttpRequest.getInstance(context);//new OKHttpRequest();
    }

    public static HttpManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    /**
     * 检查更新
     *
     * @param callBack
     */
    public void getCheckForceUpdate(ReqCallBack<String> callBack) {
        HashMap<String, String> netParams = new HashMap<>();
        netParams.put("osType", "2"); //系统类型(1:IOS;2:安卓)
        netParams.put("osVersion", CommonUtil.getSystemVersion());//系统版本
        netParams.put("deviceId", CommonUtil.getDeviceBrand());//设备标识
        netParams.put("deviceModel", CommonUtil.getSystemModel()); //设备型号
        netParams.put("appVersion", CommonUtil.getVersionName(mContext)); //应用版本
        okHttpRequest.requestAsyn(getAppVersion, TYPE_POST_FORM, netParams, callBack);
    }

}
