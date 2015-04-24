package com.toaker.framework.core.surface.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.toaker.framework.R;
import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.fragment.AbsFragment;
import com.toaker.framework.core.surface.fragment.BaseFragment;

public class ReusingActivityHelper {

    public static final String SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM = "SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM";

    ReusingActivity mActivity;

    ReusingActivityHelper(ReusingActivity activity) {
        mActivity = activity;
    }

    /**
     * 初始化fragment
     */
    AbsFragment ensureFragment(FragmentParameter param) {
      return  addFragmentByTag(param);
    }

    /**
     * 增加fragment
     * 
     * @return
     */
    private AbsFragment addFragmentByTag(FragmentParameter parameter) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        AbsFragment fragment = (BaseFragment) fm.findFragmentByTag(parameter.getTag());
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = (AbsFragment) Fragment.instantiate(mActivity, parameter.getFragmentClass().getName(), parameter.getParams());
            ft.add(R.id.content,fragment, parameter.getTag());
            ft.commit();
        } else if (fragment.isDetached()) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(fragment);
            ft.commit();
        }
        return fragment;
    }

    static boolean isSingleFragmentIntent(Activity activity) {
        FragmentParameter param = activity.getIntent().getParcelableExtra(
                SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM);
        return param != null;
    }

    public static IntentBuilder builder(Context context) {
        IntentBuilder b = new IntentBuilder();
        b.create(context, ReusingActivity.class);
        return b;
    }

    public static class IntentBuilder {

        private FragmentParameter mParams;

        private Intent intent;

        public IntentBuilder create(Context context,
                Class<? extends Activity> clazz) {
            intent = new Intent(context, clazz);
            return this;
        }

        public IntentBuilder setFragmentParameter(FragmentParameter parameter) {
            this.mParams = parameter;
            return this;
        }


        public Intent build() {
            intent.putExtra(SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM, mParams);
            return intent;
        }
    }
}
