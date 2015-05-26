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
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.toaker.framework.core.component.ActionBar;
import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.core.widget.PressSelector;

/**
 * Decorator for framework-master
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/1 17:34
 */
public class ActionView extends FrameLayout {

    private TextView mTextView;

    private ImageView mIconView;

    private float mTextSize = 14;

    private int   mTextColor = Color.WHITE;

    private boolean isText = false;

    private PressSelector mPressSelector;

    private FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);

    public ActionView(Context context) {
        super(context);
        initialize();
    }

    /**
     * ActionView init
     */
    private void initialize() {
        mTextView = new TextView(getContext());
        mIconView = new ImageView(getContext());
        mTextView.setSingleLine();
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        mTextView.setVisibility(GONE);
        mIconView.setVisibility(GONE);
        params.gravity = Gravity.CENTER;
        reset();
        addView(mTextView,params);
        addView(mIconView,params);
    }

    public void reset(){
        if(ScaleController.getInstance() != null){
            this.mTextSize = ScaleController.getInstance().scaleTextSize(this.mTextSize);
        }
        mTextView.setTextColor(this.mTextColor);
        mTextView.setTextSize(this.mTextSize);
        if(isText){
            mIconView.setVisibility(GONE);
            mTextView.setVisibility(VISIBLE);
        }else {
            mTextView.setVisibility(GONE);
            mIconView.setVisibility(VISIBLE);
        }
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
        this.isText = true;
        reset();
    }

    public void setText(int resId){
        this.mTextView.setText(resId);
        this.isText = true;
        reset();
    }

    public void setIcon(int resId){
        this.mIconView.setImageResource(resId);
        this.isText = false;
        reset();
    }

    public void setIcon(Drawable drawable){
        this.mIconView.setImageDrawable(drawable);
        this.isText = false;
        reset();
    }

    public PressSelector getPressSelector() {
        return mPressSelector;
    }

    public void setPressSelector(PressSelector mPressSelector) {
        this.setOnTouchListener(mPressSelector);
        this.mPressSelector = mPressSelector;
    }

    public void setActionSize(int width,int height){
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }

    public void setActionClickListener(ActionBar.ActionClickListener listener){
        this.setOnClickListener(listener);
    }

    public void setActionLongClickListener(ActionBar.ActionLongClickListener listener){
        this.setOnLongClickListener(listener);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        if(ScaleController.getInstance() != null){
            ScaleController.getInstance().scalePadding(this);
        }
    }
}
