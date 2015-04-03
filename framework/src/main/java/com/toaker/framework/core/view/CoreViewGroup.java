package com.toaker.framework.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;


public abstract class CoreViewGroup extends ViewGroup {


    public CoreViewGroup(Context context) {
        super(context);
    }


    public CoreViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoreViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }
}
