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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.ListenerWrapper;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.entry.MultiPartEntity;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/8 14:24
 */
public class JsonDataRequest<T extends ResponseWrapper> extends Request<T> {

    private RequestParameter params;

    private ListenerWrapper<T> mListener;

    private Gson    mJsonUtils;

    protected Class<T>  mTypeClass;

    public JsonDataRequest(Class<T> tClass,int method, String url, RequestParameter params,ListenerWrapper<T> listener,boolean cache,boolean needRefresh) {
        super(method, url, listener);
        this.mTypeClass = tClass;
        this.params = params;
        this.mListener = listener;
        mJsonUtils = new Gson();
        setRetryPolicy(new DefaultRetryPolicy(8000, 0, 1.0f));
        setRefreshNeeded(needRefresh);
        setShouldCache(cache);
    }

    public JsonDataRequest(Class<T> tClass,int method, String url, RequestParameter params,ListenerWrapper<T> listener,boolean cache) {
        this(tClass,method, url, params, listener, cache, true);
    }

    public JsonDataRequest(Class<T> tClass,int method, String url, RequestParameter params,ListenerWrapper<T> listener) {
        this(tClass,method,url,params,listener,true);
    }

    public JsonDataRequest(Class<T> tClass,int method, String url,ListenerWrapper<T> listener) {
        this(tClass,method,url,null,listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            VolleyLog.v("++++++:" + new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers)));
            String result = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            T instanceResponse = mJsonUtils.fromJson(result, mTypeClass);
            return Response.success(instanceResponse, HttpHeaderParserWrapper.parseCacheHeaders(networkResponse));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public String getUrl() {
        switch (getMethod()) {
            case Method.GET:
                String p = encodingParams();
                return TextUtils.isEmpty(p) ? super.getUrl() : super.getUrl() + "?" + p;
        }
        return super.getUrl();
    }

    /**
     * url编码
     *
     * @return
     */
    private String encodingParams() {
        if (params == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.getStringParams().entrySet()) {
                builder.append(URLEncoder.encode(entry.getKey(), getParamsEncoding()));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), getParamsEncoding()));
                builder.append("&");
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (params == null) return null;
        try {
            return params.getStringParams();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthFailureError();
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return parseBody();
    }

    public byte[] parseBody() throws AuthFailureError{

        if(params == null || params.getFileParams().isEmpty()){
            return super.getBody();
        }
        if(getMethod() != Method.POST){
            return super.getBody();
        }

        MultiPartEntity multipartEntity = new MultiPartEntity();
        for (Map.Entry<String, String> entry : params.getStringParams().entrySet()) {
            multipartEntity.addPart(entry.getKey(), entry.getValue());
        }
        int currentIndex = 0;
        int lastIndex = params.getFileParams().entrySet().size() - 1;
        for (Map.Entry<String, File> entry : params.getFileParams().entrySet()) {
            multipartEntity.addPart(entry.getKey(),entry.getValue(),currentIndex == lastIndex);
            currentIndex++;
        }
        return multipartEntity.getBody();
    }
}
