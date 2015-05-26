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
package com.toaker.framework.core.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toaker.framework.utils.ReflectUtils;

import java.lang.reflect.Field;

/**
 * Decorator for framework-master
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/23 14:06
 */
public class ToolBarWrapper extends ViewGroup {

    private RelativeLayout mToolBarGroup;

    private TextView       mCenterTextView;

    private TextView       mTitleView;

    private View           mCenterView;

    private int            mCenterTextColor;

    private RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

    public ToolBarWrapper(Context context) {
        super(context);
        initialize();
    }

    public ToolBarWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    public ToolBarWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initialize() {
        mTitleView = ReflectUtils.getFieldValue(this,"mTitleTextView");
        mCenterTextColor = ReflectUtils.getFieldValue(this,"mTitleTextColor");
        mToolBarGroup = new RelativeLayout(getContext());
        super.addView(mToolBarGroup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mCenterTextView = new TextView(getContext());
        mCenterTextView.setTextSize(mTitleView.getTextSize());
        mCenterTextView.setTextColor(mCenterTextColor);
    }

    public void setCenterTitle(CharSequence title){
        if(mCenterView != null && mCenterView.getParent() != null){
            mCenterView.setVisibility(View.GONE);
        }
        if(mCenterTextView.getParent() == null){
            mLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mToolBarGroup.addView(mCenterTextView,mLayoutParams);
        }
        mCenterTextView.setVisibility(View.VISIBLE);
        mCenterTextView.setText(title);
    }

    public void setCenterTitle(int resId){
        setCenterTitle(getContext().getResources().getString(resId));
    }

    public void setCenterView(View centerView){
        if(mCenterView != null && mCenterView.getParent() != null){
            mToolBarGroup.removeView(mCenterView);
        }
        this.mCenterView = centerView;
        if(mCenterView.getParent() == null){
            mLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mToolBarGroup.addView(mCenterView,mLayoutParams);
        }
        mCenterView.setVisibility(View.VISIBLE);
    }

    public void removeCenterView(){
        if(mCenterView != null && mCenterView.getParent() != null){
            mToolBarGroup.removeView(mCenterView);
        }
    }

    public TextView getCenterTitleView(){
        return this.mCenterTextView;
    }

    public void setCenterTextSize(float size) {
        mCenterTextView.setTextSize(size);
    }

    public void setCenterTextColor(int color) {
        mCenterTextView.setTextColor(color);
    }

    public CharSequence getCenterTitle(){
        return mCenterTextView.getText();
    }

    private <T> T getField(Object obj,String fieldName){
        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
