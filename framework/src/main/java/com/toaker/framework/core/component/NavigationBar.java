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

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/6 2:01
 */
public interface NavigationBar {

    public void setBackgroundColor(int color);

    public void setBackgroundResource(int resid);

    public void setBackground(Drawable background);

    public void setNavigationChangeListener(NavigationChangeListener listener);

    public Navigation getNavigation(int position);

    public int getCurrentPosition();

    public void setCurrentItem(int position);

    public void setNavigationBarHeight(int height);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void addNavigation(Navigation navigation);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void removeNavigation(Navigation navigation);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public interface Navigation{

        public void setCustomView(View v);

        public View getView();

        public void setOnClickListener(View.OnClickListener clickListener);

        public void setCheckStatus(boolean status);

        public boolean getCheckStatus();

        public void setPadding(int left, int top, int right, int bottom);

        public void setPadding(int spacer);

        public void setSpacer(int spacer);
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public interface NavigationChangeListener{

        public void onChange(View view,Navigation navigation,int position);
    }
}
