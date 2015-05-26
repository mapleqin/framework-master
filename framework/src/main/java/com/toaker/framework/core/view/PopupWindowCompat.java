/*******************************************************************************
 * Copyright 2015-2019 copyright of Soulwolf XiaoDaoW
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
package com.toaker.framework.core.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

import com.toaker.framework.core.utils.ScaleController;

import java.lang.reflect.Field;

/**
 * Decorator for XiaoDaoW
 *
 * author Soulwolf
 *
 * Create by 2015/5/19 11:23
 */
public class PopupWindowCompat extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final Field superListenerField;

    static {
        Field f = null;
        try {
            f = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            /* ignored */
        }
        superListenerField = f;
    }

    private View mAnchorView;

    private ShowMode mShowMode = ShowMode.CENTER;

    private float mTriangleSize = 20.0f;

    public PopupWindowCompat(Context context) {
        super(context);
        init();
    }

    public PopupWindowCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PopupWindowCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public PopupWindowCompat(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public PopupWindowCompat(int width, int height) {
        super(width, height);
        init();
    }

    public PopupWindowCompat(View contentView) {
        super(contentView);
        init();
    }

    public PopupWindowCompat(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        init();
    }

    public PopupWindowCompat(View contentView, int width, int height) {
        super(contentView, width, height);
        init();
    }

    private void init() {
        if(ScaleController.getInstance() != null){
            mTriangleSize = ScaleController.getInstance().scaleHeight((int) mTriangleSize);
        }
    }

    public float getTriangleSize() {
        return mTriangleSize;
    }

    public void setTriangleSize(float mTriangleSize) {
        this.mTriangleSize = mTriangleSize;
    }

    public void setShowMode(ShowMode mode){
        this.mShowMode = mode;
        if(isShowing() && mAnchorView != null){
           update();
        }
    }

    public void setAnchorView(View view){
        this.mAnchorView = view;
    }

    public void show(){
        if(!isShowing()){
            if(mAnchorView != null){
                if(mShowMode == ShowMode.LEFT){
                    showAsDropDown(mAnchorView,0, (int) -mTriangleSize);
                }else if(mShowMode == ShowMode.RIGHT){
                    showAsDropDown(mAnchorView,- (getWidth() - mAnchorView.getWidth()),(int) -mTriangleSize);
                }else {
                    showAsDropDown(mAnchorView,mAnchorView.getWidth() / 2 - getWidth() / 2 ,(int) -mTriangleSize);
                }
            }
        }
    }

    @Override
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        if(contentView != null){
            ViewTreeObserver vto = contentView.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(this);
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void update(View anchor, int xoff, int yoff, int width, int height) {
        super.update(anchor, xoff, yoff, width, height);
    }

    @Override
    public void update(View anchor, int width, int height) {
        super.update(anchor, width, height);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onGlobalLayout() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
            getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        setWidth(getContentView().getWidth());
        setHeight(getContentView().getHeight());
    }

    public enum ShowMode{
        LEFT,
        CENTER,
        RIGHT
    }
}
