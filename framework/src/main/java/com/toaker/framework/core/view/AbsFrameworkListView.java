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
import android.util.AttributeSet;
import android.widget.ListView;

import com.toaker.framework.core.inter.OnFrameworkListView;
import com.toaker.framework.utils.FrameworkLog;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/9 14:19
 */
public class AbsFrameworkListView extends ListView implements OnFrameworkListView {

    public AbsFrameworkListView(Context context) {
        super(context);
    }

    public AbsFrameworkListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsFrameworkListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean checkCanDoRefresh() {
        if (getAdapter() == null || getAdapter().getCount() == 0) {
            return true;
        }
        FrameworkLog.d("test", "checkCanDoRefresh: %s %s", getFirstVisiblePosition(), getChildAt(0).getTop());
        return getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0;
    }

    @Override
    public void completeLoadMore(){

    }
}
