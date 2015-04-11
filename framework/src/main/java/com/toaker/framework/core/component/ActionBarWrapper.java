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

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/6 19:10
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public final class ActionBarWrapper {

    private static final String TAG = "ActionBarWrapper";

    private Context   mContext;

    private ViewGroup mActionView;

    private TextView mTitleView;

    private TextView mCenterTitleView;

    private View     mCenterView;

    private RelativeLayout mCenterGroup;

    private FrameLayout mHomeLayout;

    private FrameLayout mContainerView;

    private LinearLayout mTitleLayout;

    private android.app.ActionBar mActionBar;

    private RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

    public ActionBarWrapper (android.app.ActionBar actionBar){
        this.mActionBar = actionBar;
        this.mContext = getField(mActionBar,"mContext");
        if(mActionBar != null && mContext != null){
            initialize();
        }
    }

    public ActionBarWrapper getActionBarWrapper(){
        return mActionBar == null ? null : this;
    }

    private void initialize() {
        mCenterGroup = new RelativeLayout(mContext);
        mCenterTitleView = new TextView(mContext);
        mActionView = getField(mActionBar,"mActionView");
        mContainerView = getField(mActionBar,"mContainerView");
        mTitleView = getField(mActionView,"mTitleView");
        mHomeLayout = getField(mActionView,"mHomeLayout");
        mTitleLayout = getField(mActionView,"mTitleLayout");
        if(mActionView != null){
            mContainerView.addView(mCenterGroup, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mCenterGroup.setVisibility(View.VISIBLE);
    }

    public void setCenterTitle(CharSequence title){
        if(mCenterView != null && mCenterView.getParent() != null){
            mCenterView.setVisibility(View.GONE);
        }
        if(mCenterTitleView.getParent() == null){
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            mCenterGroup.addView(mCenterTitleView,params);
        }
        mCenterTitleView.setVisibility(View.VISIBLE);
        mCenterTitleView.setText(title);
        Log.d(TAG,mCenterGroup.getMeasuredWidth()+"---" + mCenterGroup.getMeasuredHeight());
    }

    public void setCenterTitle(int resId){
        if(mCenterView != null && mCenterView.getParent() != null){
            mCenterView.setVisibility(View.GONE);
        }
        if(mCenterTitleView.getParent() == null){
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            mCenterGroup.addView(mCenterTitleView,params);
        }
        mCenterTitleView.setVisibility(View.VISIBLE);
        mCenterTitleView.setText(resId);
    }

    public void setCenterView(View centerView){
        if(mCenterView != null && mCenterView.getParent() != null){
            mCenterGroup.removeView(mCenterView);
        }
        this.mCenterView = centerView;
        if(mCenterView.getParent() == null){
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            mCenterGroup.addView(mCenterView,params);
        }
        mCenterView.setVisibility(View.VISIBLE);
    }

    public TextView getCenterTitleView(){
        return this.mCenterTitleView;
    }

    public void setCenterTextSize(float size) {
        mCenterTitleView.setTextSize(size);
    }

    public void setCenterTextColor(int color) {
        mCenterTitleView.setTextColor(color);
    }

    public CharSequence getCenterTitle(){
        return mCenterTitleView.getText();
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

    public void setCustomView(View view) {
        mActionBar.setCustomView(view);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        mActionBar.setCustomView(view,layoutParams);
    }

    public void setCustomView(int resId) {
        mActionBar.setCustomView(resId);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setIcon(int resId) {
        mActionBar.setIcon(resId);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setIcon(Drawable icon) {
        mActionBar.setIcon(icon);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setLogo(int resId) {
        mActionBar.setLogo(resId);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setLogo(Drawable logo) {
        mActionBar.setLogo(logo);
    }

    public void setListNavigationCallbacks(SpinnerAdapter adapter, ActionBar.OnNavigationListener callback) {
        mActionBar.setListNavigationCallbacks(adapter,callback);
    }

    public void setSelectedNavigationItem(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    public int getSelectedNavigationIndex() {
        return mActionBar.getSelectedNavigationIndex();
    }

    public int getNavigationItemCount() {
        return mActionBar.getNavigationItemCount();
    }

    public void setTitle(CharSequence title) {
        mActionBar.setTitle(title);
    }

    public void setTitle(int resId) {
        mActionBar.setTitle(resId);
    }

    public void setSubtitle(CharSequence subtitle) {
        mActionBar.setSubtitle(subtitle);
    }

    public void setSubtitle(int resId) {
        mActionBar.setSubtitle(resId);
    }

    public void setDisplayOptions(int options) {
        mActionBar.setDisplayOptions(options);
    }

    public void setDisplayOptions(int options, int mask) {
        mActionBar.setDisplayOptions(options,mask);
    }

    public void setDisplayUseLogoEnabled(boolean useLogo) {
        mActionBar.setDisplayUseLogoEnabled(useLogo);
    }

    public void setDisplayShowHomeEnabled(boolean showHome) {
        mActionBar.setDisplayShowHomeEnabled(showHome);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        mActionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    public void setDisplayShowTitleEnabled(boolean showTitle) {
        mActionBar.setDisplayShowTitleEnabled(showTitle);
    }

    public void setDisplayShowCustomEnabled(boolean showCustom) {
        mActionBar.setDisplayShowCustomEnabled(showCustom);
    }

    public void setBackgroundDrawable(Drawable d) {
        mActionBar.setBackgroundDrawable(d);
    }

    public View getCustomView() {
        return mActionBar.getCustomView();
    }

    public CharSequence getTitle() {
        return mActionBar.getTitle();
    }

    public CharSequence getSubtitle() {
        return mActionBar.getSubtitle();
    }

    public int getNavigationMode() {
        return mActionBar.getNavigationMode();
    }

    public void setNavigationMode(int mode) {
        mActionBar.setNavigationMode(mode);
    }

    public int getDisplayOptions() {
        return mActionBar.getDisplayOptions();
    }

    public ActionBar.Tab newTab() {
        return mActionBar.newTab();
    }

    public void addTab(ActionBar.Tab tab) {
        mActionBar.addTab(tab);
    }

    public void addTab(ActionBar.Tab tab, boolean setSelected) {
        mActionBar.addTab(tab,setSelected);
    }

    public void addTab(ActionBar.Tab tab, int position) {
        mActionBar.addTab(tab,position);
    }

    public void addTab(ActionBar.Tab tab, int position, boolean setSelected) {
        mActionBar.addTab(tab,position,setSelected);
    }

    public void removeTab(ActionBar.Tab tab) {
        mActionBar.removeTab(tab);
    }

    public void removeTabAt(int position) {
        mActionBar.removeTabAt(position);
    }

    public void removeAllTabs() {
        mActionBar.removeAllTabs();
    }

    public void selectTab(ActionBar.Tab tab) {
        mActionBar.selectTab(tab);
    }

    public ActionBar.Tab getSelectedTab() {
        return mActionBar.getSelectedTab();
    }

    public ActionBar.Tab getTabAt(int index) {
        return mActionBar.getTabAt(index);
    }

    public int getTabCount() {
        return mActionBar.getTabCount();
    }

    public int getHeight() {
        return mActionBar.getHeight();
    }

    public void show() {
        mActionBar.show();
    }

    public void hide() {
        mActionBar.hide();
    }

    public boolean isShowing() {
        return mActionBar.isShowing();
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        mActionBar.addOnMenuVisibilityListener(listener);
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        mActionBar.removeOnMenuVisibilityListener(listener);
    }
}
