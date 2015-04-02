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

import android.view.MotionEvent;
import android.view.View;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/1 17:43
 */
public final class PressSelector implements View.OnTouchListener {

    private int defaultColor        = 0x00000000;

    private int pressColor          = 0x5531B9EC;

    public PressSelector(){

    }

    public PressSelector(int defaultColor, int pressColor) {
        this.defaultColor = defaultColor;
        this.pressColor = pressColor;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v != null && event != null){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                v.setBackgroundColor(this.pressColor);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                v.setBackgroundColor(this.defaultColor);
            }
        }
        return false;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public int getPressColor() {
        return pressColor;
    }

    public void setPressColor(int pressColor) {
        this.pressColor = pressColor;
    }
}
