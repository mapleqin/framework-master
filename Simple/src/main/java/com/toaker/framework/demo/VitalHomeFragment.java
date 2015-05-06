package com.toaker.framework.demo;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.toaker.framework.base.BasePtrFrameworkFragment;

import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class VitalHomeFragment extends BasePtrFrameworkFragment<HomeData> {


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

        if(getTitleBar() != null){
            getTitleBar().setLeftText("返回");
        }
    }



    public void onSuccess(HomeData o) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startNetWork();
    }

    protected String getRequestUrl() {
        return "http://www.xiaodao360.com/dao.php/App/Activity/activity_list";
    }

    @Override
    protected Class getTypeClass() {
        return HomeData.class;
    }

    @Override
    public int getResourceLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        Log.e("PtrFrameLayout","checkCanDoRefresh:" + mPtrLayout);
        return true;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
