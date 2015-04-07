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

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void setBackgroundColor(int color);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void setBackgroundResource(int resid);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void setBackground(Drawable background);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void setNavigationChangeListener(NavigationChangeListener listener);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public Navigation getNavigation(int position);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public int getCurrentPosition();

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
    public void setCurrentItem(int position);

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/6 2:01
     */
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

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void setCustomView(View v);

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public View getView();

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void setOnClickListener(View.OnClickListener clickListener);

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void setCheckStatus(boolean status);

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public boolean getCheckStatus();

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void setPadding(int left, int top, int right, int bottom);

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void setPadding(int spacer);

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
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

        /**
         * Decorator for framework-master
         *
         * @author Toaker [Toaker](ToakerQin@gmail.com)
         *         [Toaker](http://www.toaker.com)
         * @Description:
         * @Time Create by 2015/4/6 2:01
         */
        public void onChange(View view,Navigation navigation,int position);
    }
}
