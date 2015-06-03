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
package com.toaker.framework.base;

import android.os.Bundle;
import android.view.View;

import com.android.volley.ListenerWrapper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonDataRequest;
import com.android.volley.toolbox.RequestParameter;
import com.android.volley.toolbox.ResponseWrapper;
import com.android.volley.toolbox.VolleyErrorWrapper;
import com.toaker.framework.core.surface.fragment.BaseFragment;
import com.toaker.framework.utils.VolleyHelper;

/**
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/9 11:48
 */
public abstract class BaseFrameworkFragment<T extends ResponseWrapper> extends BaseFragment {

    protected RequestParameter mRequestParameter;

    protected RequestQueue     mRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = VolleyHelper.newRequestQueue();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    ListenerWrapper<T> mListenerWrapper = new ListenerWrapper<T>() {
        @Override
        public void onSuccess(T response) {
        }

        @Override
        public void onSuccess(T response, boolean cache) {
            super.onSuccess(response, cache);
            BaseFrameworkFragment.this.onSuccess(response,cache);
        }

        @Override
        public void onError(VolleyErrorWrapper error) {
            BaseFrameworkFragment.this.onError(error);
        }
    };

    protected void startNetWork(){
        startNetWork(Request.Method.GET);
    }

    protected void startNetWork(int method){
        startNetWork(method,null);
    }

    protected void startNetWork(int method,RequestParameter params){
        startNetWork(method,params,true);
    }

    protected void startNetWork(int method,RequestParameter params,boolean isCache){
        this.mRequestParameter = params;
        JsonDataRequest<T> request = new JsonDataRequest<>(getTypeClass(), method, getRequestUrl(), params, mListenerWrapper);
        request.setShouldCache(isCache);
        mRequestQueue.add(request);
    }

    public abstract void onSuccess(T response);


    public void onSuccess(T response,boolean cache){
        onSuccess(response);
    }

    /**
     *
     * @param error
     */
    protected void onError(VolleyErrorWrapper error){
    }

    protected abstract String getRequestUrl();

    protected abstract Class<T> getTypeClass();
}
