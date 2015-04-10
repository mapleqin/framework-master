package com.toaker.framework.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Daniel on 2015/1/13.
 */
public class NoTouchLayout extends RelativeLayout {

    public NoTouchLayout(Context context) {
        super(context);
    }

    public NoTouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getVisibility() == VISIBLE) {
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }
}
