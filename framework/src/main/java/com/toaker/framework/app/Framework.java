/*******************************************************************************
 * Copyright 2013-2014 Toaker framework-master
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.toaker.framework.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.utils.ResourceUtils;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/8 11:39
 */
public class Framework {

    private String mBaseUrl = "";

    private int mScreenHeight = 0;

    private int mScreenWidth  = 0;

    private static Framework instance;

    private Context mContext;

    private Framework(Context context){
        this.mContext = context;
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        this.mScreenWidth = metrics.widthPixels;
        this.mScreenHeight = metrics.heightPixels;

        initImageLoad();
    }

    private void initImageLoad() {

    }

    public static Framework getInstance(){
        if(instance == null){
            throw new IllegalArgumentException("The Framework is uninitialized");
        }
        return instance;
    }

    public Context getContext(){
        return mContext;
    }

    /**
     *
     * Resource String :
     *           * 未知错误
     *           volley_error_message_unknown
     *
     *           * 无连接
     *           volley_error_message_no_connection
     *
     *           * 网络错误
     *           volley_error_message_network
     *
     *           * 认证失败
     *           volley_error_message_auth_failure
     *
     *           * 超时
     *           volley_error_message_timeout
     *
     *           * 解析时错误
     *           volley_error_message_parse
     *
     *           * 服务器错误
     *           volley_error_message_server
     *
     * Resource  drawable:
     *           * 加载中图片
     *           image_load_loading
     *
     *           * 加载失败图片
     *           image_load_failure
     *
     *           * 图片为空
     *           image_load_empty
     *
     *
     * <activity
     *   android:name="com.toaker.framework.core.surface.activity.ReusingActivity"
     *   android:allowBackup="true"
     *   android:screenOrientation="portrait"
     *   android:theme="theme"
     *   android:windowSoftInputMode="adjustResize" />
     *
     * initialize
     * @param context
     */
    public static void init(Context context){
        if(context == null){
            throw new IllegalArgumentException("Initialization of the Context cannot be NULL");
        }
        if(instance == null){
            instance = new Framework(context);
        }
        ResourceUtils.init(context);
        ScaleController.init(context);
    }

    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }
}
