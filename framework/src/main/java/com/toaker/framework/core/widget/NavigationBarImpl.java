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
package com.toaker.framework.core.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toaker.framework.R;
import com.toaker.framework.core.component.NavigationBar;
import com.toaker.framework.core.utils.ScaleController;

import java.util.Map;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/6 15:35
 */
public class NavigationBarImpl extends FrameLayout implements NavigationBar, View.OnClickListener {

    private LinearLayout mViewGroup;

    private int mCurPosition = 0;

    private ArrayMap<Integer,Navigation> mNavigates;

    private NavigationChangeListener mListener;

    private float mNavigationHeight;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);

    public NavigationBarImpl(Context context) {
        this(context, null);
    }

    public NavigationBarImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description: init
     * @Time Create by 2015/4/6 15:35
     */
    private void initialize() {
        mViewGroup = new LinearLayout(getContext());
        mNavigates = new ArrayMap<Integer,Navigation>();
        mViewGroup.setOrientation(LinearLayout.HORIZONTAL);
        params.weight = 1;
        params.gravity = Gravity.CENTER;
        super.addView(mViewGroup);
        this.mNavigationHeight = super.getResources().getDimension(R.dimen.abs__navigation_bar_default_height);
        if(ScaleController.getInstance() != null){
            mNavigationHeight = ScaleController.getInstance().getScreenHeight() * 0.23f;
        }
        setNavigationBarHeight((int) this.mNavigationHeight);

    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    public void setNavigationChangeListener(NavigationChangeListener listener) {
        this.mListener = listener;
    }

    @Override
    public Navigation getNavigation(int position) {
        return mNavigates.get(position);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }


    @Override
    public int getCurrentPosition() {
        return this.mCurPosition;
    }

    @Override
    public void setCurrentItem(int position) {
        this.mCurPosition = position;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        for (Map.Entry<Integer,Navigation> entry:mNavigates.entrySet()){
            if(entry.getKey() == mCurPosition){
                entry.getValue().setCheckStatus(true);
                if(mListener != null){
                    mListener.onChange(entry.getValue().getView(),entry.getValue(),entry.getKey());
                }
            }else {
                entry.getValue().setCheckStatus(false);
            }
        }
    }

    @Override
    public void setNavigationBarHeight(int height) {
        if(ScaleController.getInstance() != null){
            height = ScaleController.getInstance().scaleHeight(height);
        }
        this.mNavigationHeight = height;
        mViewGroup.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) this.mNavigationHeight));
    }

    @Override
    public void addNavigation(Navigation navigation) {
        if(navigation != null && navigation.getView() != null && navigation.getView().getParent() == null){
            mViewGroup.addView(navigation.getView(),params);
            mNavigates.put(mViewGroup.indexOfChild(navigation.getView()),navigation);
            navigation.getView().setOnClickListener(this);
        }
    }

    @Override
    public void removeNavigation(Navigation navigation) {
        this.mNavigates.remove(navigation);
    }



    @Override
    public void onClick(View v) {
        if(super.indexOfChild(v) != mCurPosition){
            setCurrentItem(mViewGroup.indexOfChild(v));
        }
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 15:35
     */
    public static class IconNavigationImpl extends LinearLayout implements Navigation{

        private Drawable mDefaultDrawable;

        private Drawable mCheckDrawable;

        private CharSequence  mText;

        private int      mDefaultTextColor = 0xFFFFFFFF;

        private int      mCheckTextColor   = 0x00000000;

        private float    mTextSize = 18;

        private TextView mTextView;

        private ImageView mIconView;

        private View      mSpacerView;

        private LayoutParams   mSpacerParams = new LayoutParams(LayoutParams.MATCH_PARENT,0);

        private LayoutParams   mTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        private boolean   mCheckStatus = false;

        public IconNavigationImpl(Context context) {
            super(context);
            this.initialize();
        }

        private void initialize(){
            super.setOrientation(VERTICAL);
            mTextView = new TextView(getContext());
            if(ScaleController.getInstance() != null){
                this.mTextSize = ScaleController.getInstance().scaleTextSize(this.mTextSize);
            }
            mTextView.setTextSize(this.mTextSize);
            mTextView.setGravity(Gravity.CENTER);
            mIconView = new ImageView(getContext());
            mSpacerView = new View(getContext());
            super.addView(mIconView);
            super.addView(mSpacerView,mSpacerParams);
            mTextParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            super.addView(mTextView,mTextParams);
        }

        @Override
        public void setPadding(int left, int top, int right, int bottom) {
            super.setPadding(left, top, right, bottom);
        }

        @Override
        public void setPadding(int spacer) {
            setPadding(spacer,spacer,spacer,spacer);
        }

        @Override
        public void setSpacer(int spacer) {
            mSpacerParams.topMargin = spacer;
            mSpacerView.setLayoutParams(mSpacerParams);
        }

        @Override
        public void requestLayout() {
            super.requestLayout();
            if(mTextView != null && mIconView != null){
                mTextView.setTextColor(mCheckStatus ? mCheckTextColor : mDefaultTextColor);
                mIconView.setImageDrawable(mCheckStatus ? mCheckDrawable : mDefaultDrawable);
            }
        }

        public IconNavigationImpl(Context context,int mDefaultDrawable,int mCheckDrawable,int mDefaultTextColor,int mCheckTextColor) {
            this(context);
            setDefaultDrawable(mDefaultDrawable);
            setCheckDrawable(mCheckDrawable);
            setDefaultTextColor(mDefaultTextColor);
            setCheckTextColor(mCheckTextColor);
        }

        public IconNavigationImpl(Context context,int mDefaultDrawable,int mCheckDrawable,int mDefaultTextColor,int mCheckTextColor,CharSequence mText) {
            this(context, mDefaultDrawable, mCheckDrawable, mDefaultTextColor, mCheckTextColor);
            setText(mText);
        }



        @Override
        public void setCustomView(View v) {
            super.removeAllViews();
            super.addView(v);
        }

        @Override
        public View getView() {
            return this;
        }

        @Override
        public void setOnClickListener(OnClickListener clickListener) {
            super.setOnClickListener(clickListener);
        }

        public Drawable getDefaultDrawable() {
            return mDefaultDrawable;
        }

        public void setDefaultDrawable(Drawable mDefaultDrawable) {
            this.mDefaultDrawable = mDefaultDrawable;
        }

        public void setDefaultDrawable(int resId) {
            setDefaultDrawable(getResources().getDrawable(resId));
        }

        public Drawable getCheckDrawable() {
            return mCheckDrawable;
        }

        public void setCheckDrawable(Drawable mCheckDrawable) {
            this.mCheckDrawable = mCheckDrawable;
        }

        public void setCheckDrawable(int resId) {
            setCheckDrawable(getResources().getDrawable(resId));
        }

        public int getDefaultTextColor() {
            return mDefaultTextColor;
        }

        public void setDefaultTextColor(int mDefaultTextColor) {
            this.mDefaultTextColor = mDefaultTextColor;
        }

        public int getCheckTextColor() {
            return mCheckTextColor;
        }

        public void setCheckTextColor(int mCheckTextColor) {
            this.mCheckTextColor = mCheckTextColor;
        }

        public float getTextSize() {
            return mTextSize;
        }

        public void setTextSize(float mTextSize) {
            if(ScaleController.getInstance() != null){
                mTextSize = ScaleController.getInstance().scaleTextSize(mTextSize);
            }
            this.mTextSize = mTextSize;
            this.mTextView.setTextSize(this.mTextSize);
        }

        public CharSequence getText() {
            return mText;
        }

        public void setText(CharSequence mText) {
            this.mText = mText;
            this.mTextView.setText(mText);
        }

        public void setCheckStatus(boolean mCheckStatus) {
            this.mCheckStatus = mCheckStatus;
            requestLayout();
        }

        @Override
        public boolean getCheckStatus() {
            return mCheckStatus;
        }
    }
}
