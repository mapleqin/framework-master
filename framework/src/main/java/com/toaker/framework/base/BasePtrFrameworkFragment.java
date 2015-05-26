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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.ListenerWrapper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonDataRequest;
import com.android.volley.toolbox.RequestParameter;
import com.android.volley.toolbox.ResponseWrapper;
import com.android.volley.toolbox.VolleyErrorWrapper;
import com.toaker.framework.R;
import com.toaker.framework.core.surface.fragment.BaseFragment;
import com.toaker.framework.utils.VolleyHelper;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Decorator for framework-master
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/9 11:48
 */
public abstract class BasePtrFrameworkFragment<T extends ResponseWrapper> extends BaseFragment implements PtrHandler {

    protected RequestParameter mRequestParameter;

    protected RequestQueue     mRequestQueue;

    protected PtrClassicFrameLayout mPtrLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = VolleyHelper.newRequestQueue();
    }

    @Override
    protected View getCustomView(LayoutInflater inflater, FrameLayout containerView, Bundle savedInstanceState, boolean isRoot) {
        inflater.inflate(R.layout.ptr_container_view,containerView);
        mPtrLayout = (PtrClassicFrameLayout) containerView.findViewById(R.id.fi_ptr_layout);
        FrameLayout container = (FrameLayout) containerView.findViewById(R.id.fi_ptr_container);
        return super.getCustomView(inflater, container, savedInstanceState,isRoot);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPtrLayout != null){
            mPtrLayout.setPtrHandler(this);
        }
    }

    ListenerWrapper<T> mListenerWrapper = new ListenerWrapper<T>() {
        @Override
        public void onSuccess(T response) {
            BasePtrFrameworkFragment.this.onSuccess(response);
        }

        @Override
        public void onError(VolleyErrorWrapper error) {
            BasePtrFrameworkFragment.this.onError(error);
        }
    };

    protected void startNetWork(){
        startNetWork(Request.Method.GET);
    }

    protected void startNetWork(int method){
        startNetWork(method,null);
    }

    protected void startNetWork(int method,RequestParameter params){
        this.mRequestParameter = params;
        mRequestQueue.add(new JsonDataRequest<T>(getTypeClass(),method,getRequestUrl(),params,mListenerWrapper));
    }

    public abstract void onSuccess(T response);

    /**
     *
     * @param error
     */
    protected void onError(VolleyErrorWrapper error){
    }

    protected abstract String getRequestUrl();

    @Override
    public void onClick(View v) {

    }


    protected abstract Class<T> getTypeClass();
}
