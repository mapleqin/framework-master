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
package com.toaker.framework.core.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toaker.framework.core.component.ActionBar;
import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.core.widget.PressSelector;

/**
 * Decorator for framework-master
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/1 19:51
 */
public class SubMenuView extends LinearLayout {

    private TextView mTextView;

    private View     mSpacingView;

    private ImageView mIconView;

    private float mTextSize = 14;

    private int   mTextColor = Color.WHITE;

    private int   mSort       = 0;

    private PressSelector mPressSelector;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    private LinearLayout.LayoutParams spacingParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    public SubMenuView(Context context) {
        super(context);
        initialize();
    }

    /**
     * ActionView init
     */
    private void initialize() {
        setOrientation(HORIZONTAL);
        mTextView = new TextView(getContext());
        mIconView = new ImageView(getContext());
        mSpacingView = new View(getContext());
        mTextView.setSingleLine();
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        mTextView.setVisibility(GONE);
        mIconView.setVisibility(GONE);
        params.gravity = Gravity.CENTER;
        reset();
        recycle();
    }

    private void recycle() {
        if(mTextView.getParent() != null){
            this.removeView(mTextView);
        }
        if(mSpacingView.getParent() != null){
            this.removeView(mSpacingView);
        }
        if(mIconView.getParent() != null){
            this.removeView(mIconView);
        }
        if(this.mSort == 0){
            addView(mIconView,params);
            addView(mSpacingView,spacingParams);
            addView(mTextView,params);
        }else {
            addView(mTextView,params);
            addView(mSpacingView,spacingParams);
            addView(mIconView,params);
        }
    }

    public void reset(){
        if(ScaleController.getInstance() != null){
            this.mTextSize = ScaleController.getInstance().scaleTextSize(this.mTextSize);
        }
        mTextView.setTextColor(this.mTextColor);
        mTextView.setTextSize(this.mTextSize);
        mTextView.setVisibility(VISIBLE);
        mIconView.setVisibility(VISIBLE);
    }

    public void setSort(int desc){
        this.mSort = desc;
        recycle();
    }

    public void setTextSize(float size){
        this.mTextSize = size;
        reset();
    }

    public void setTextColor(int color){
        this.mTextColor = color;
        reset();
    }

    public void setText(CharSequence text){
        this.mTextView.setText(text);
        reset();
    }

    public void setText(int resId){
        this.mTextView.setText(resId);
        reset();
    }

    public void setIcon(int resId){
        this.mIconView.setImageResource(resId);
        reset();
    }

    public void setIcon(Drawable drawable){
        this.mIconView.setImageDrawable(drawable);
        reset();
    }

    public PressSelector getPressSelector() {
        return mPressSelector;
    }

    public void setPressSelector(PressSelector mPressSelector) {
        this.setOnTouchListener(mPressSelector);
        this.mPressSelector = mPressSelector;
    }

    public void setSubMenuSize(int width,int height){
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }

    public void setSubMenuClickListener(ActionBar.SubMenuClickListener listener){
        this.setOnClickListener(listener);
    }

    public void setSubMenuLongClickListener(ActionBar.SubMenuLongClickListener listener){
        this.setOnLongClickListener(listener);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        if(ScaleController.getInstance() != null){
            ScaleController.getInstance().scalePadding(this);
        }
    }

    public void setSpacing(float spacing){
        spacingParams.weight = spacing;
        if(ScaleController.getInstance() != null){
            spacingParams.weight = ScaleController.getInstance().scaleWidth(spacingParams.width);
        }
        mSpacingView.setLayoutParams(spacingParams);
    }
}
