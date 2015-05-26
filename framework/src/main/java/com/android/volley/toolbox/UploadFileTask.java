/*******************************************************************************
 * Copyright 2013-2014 Toaker XiaoDaoW
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

import com.android.volley.ListenerWrapper;
import com.android.volley.ParseError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.toaker.framework.app.Framework;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Create by 2015/5/7 16:47
 */
public class UploadFileTask<T extends ResponseWrapper> {

    Class<T> mClassType;

    String url;

    RequestParameter mRequestParameter;

    ListenerWrapper<T> mListener;

    AsyncHttpClient    mAsyncHttpClient;

    RequestParams mRequestParams;

    public UploadFileTask(Class<T> classType,String url,RequestParameter params,ListenerWrapper<T> listenerWrapper) {
        mClassType = classType;
        this.url = url;
        mRequestParameter = params;
        mListener = listenerWrapper;
        mAsyncHttpClient = new AsyncHttpClient();
        mRequestParams = new RequestParams(mRequestParameter.getStringParams());
        parserParams();
    }

    private void parserParams() {
        try {
            for (Map.Entry<String,File> entry:mRequestParameter.getFileParams().entrySet()){
                mRequestParams.put(entry.getKey(),entry.getValue());
            }
        } catch (FileNotFoundException e) {
        }
    }

    public void start(){
        mAsyncHttpClient.post(Framework.getInstance().getContext(),
                url,mRequestParams,new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode != 200){
                            mListener.onError(VolleyErrorWrapper.allocate(new VolleyError("statusCode error :" + statusCode)));
                            return;
                        }
                        Map<String,String> h = new HashMap<>();
                        for (Header header:headers){
                            h.put(header.getName(),header.getValue());
                        }
                        try {
                            String result = new String(responseBody,HttpHeaderParser.parseCharset(h));
                            mListener.onSuccess(new Gson().fromJson(result,mClassType));
                        } catch (UnsupportedEncodingException e) {
                            mListener.onError(VolleyErrorWrapper.allocate(new ParseError(e)));
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        mListener.onError(VolleyErrorWrapper.allocate(new VolleyError(error)));
                    }
                });
    }

}
