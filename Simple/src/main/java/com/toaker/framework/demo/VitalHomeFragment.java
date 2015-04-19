package com.toaker.framework.demo;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.android.volley.toolbox.ResponseWrapper;
import com.toaker.framework.base.BasePtrListFrameworkFragment;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class VitalHomeFragment extends BasePtrListFrameworkFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayEmptyLayout(false);
                displayLoadingLayout(false);
            }
        },3000);
    }


    public void onSuccess(ResponseWrapper o) {

    }

    public void onLoadMoreSuccess(ResponseWrapper o) {

    }

    protected String getRequestUrl() {
        return null;
    }

    @Override
    protected Class getTypeClass() {
        return String.class;
    }

    @Override
    public int getResourceLayoutId() {
        return R.layout.fragment_home;
    }
}
