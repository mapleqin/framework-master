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
package com.toaker.framework.core.surface.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.toaker.framework.core.component.ActionBarWrapper;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/6 21:25
 */
public class BaseActionBarActivity extends Activity {

    protected ActionBarWrapper mActionBarWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBarWrapper = new ActionBarWrapper(getActionBar());
    }

    @Override
    @Deprecated
    public ActionBar getActionBar() {
        return super.getActionBar();
    }

    public ActionBarWrapper getActionBarWrapper() {
        return mActionBarWrapper;
    }
}
