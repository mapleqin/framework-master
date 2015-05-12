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
package com.android.volley.toolbox;

import android.text.TextUtils;

import com.android.volley.Request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/8 14:03
 */
public final class RequestParameter {

    private int                mHttpMethod = Request.Method.GET;

    private Map<String,String> mStringParams;

    private Map<String,File>   mFileParams;

    public RequestParameter(){
        mStringParams = new HashMap<String, String>();
        mFileParams = new HashMap<String, File>();
    }

    public RequestParameter add(String key, String value) {
        if(TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
            throw new IllegalArgumentException("Request parameter cannot be NULL");
        }
        mStringParams.put(key, value);
        return this;
    }

    public RequestParameter add(String key, long value) {
        add(key,String.format("%s",value));
        return this;
    }

    public RequestParameter addBodyParameter(String key, String value) {
        add(key, value);
        return this;
    }

    public RequestParameter add(String key, File file) {
        if(TextUtils.isEmpty(key) || file == null){
            throw new IllegalArgumentException("Request parameter cannot be NULL");
        }
        mFileParams.put(key,file);
        return this;
    }

    public RequestParameter addQueryStringParameter(String key, String value) {
        add(key, value);
        return this;
    }

    public int getMethod() {
        return mHttpMethod;
    }

    public void setMethod(int mHttpMethod) {
        this.mHttpMethod = mHttpMethod;
    }

    public Map<String, String> getStringParams() {
        return mStringParams;
    }

    public Map<String, File> getFileParams() {
        return mFileParams;
    }
}
