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

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.toaker.framework.R;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/2 18:45
 */
public class ActionBarView extends CoreViewGroup {

    private Activity mActivity;

    private FrameLayout mGroupLayout;

    private float defaultHeight;

    private ActionBarView(Activity activity) {
        super(activity);
        if(activity == null){
            throw new IllegalArgumentException("The parameters of the illegal Activity is NULL");
        }
        this.mActivity = activity;
        this.defaultHeight = mActivity.getResources().getDimension(R.dimen.abc_action_bar_default_height);
        attach();
    }

    private void attach() {
        mGroupLayout = new FrameLayout(mActivity);
        ViewGroup mDecorView = (ViewGroup) mActivity.getWindow().getDecorView();
        View      mContentView = null;
        if(mDecorView.getChildCount() > 0){
            mContentView = mDecorView.getChildAt(0);
            mDecorView.removeView(mContentView);
        }
        LinearLayout mLinearLayout = new LinearLayout(mActivity);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(mGroupLayout,LayoutParams.MATCH_PARENT, (int) defaultHeight);
        if(mContentView != null){
            mLinearLayout.addView(mContentView);
        }
        mDecorView.addView(mLinearLayout);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            mGroupLayout.layout(l,t,r,b);
        }
    }
}
