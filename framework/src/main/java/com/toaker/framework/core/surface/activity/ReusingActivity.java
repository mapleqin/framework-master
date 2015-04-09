package com.toaker.framework.core.surface.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.fragment.AbsFragment;

public class ReusingActivity extends FragmentActivity {

    private ReusingActivityHelper helper;

    private AbsFragment mCurrentFragment;

    private FragmentParameter mFragmentParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ReusingActivityHelper.isSingleFragmentIntent(this)) {
            helper = new ReusingActivityHelper(this);
        }
        super.onCreate(savedInstanceState);
        if(getIntent() != null){
            mFragmentParameter = getIntent().getParcelableExtra(ReusingActivityHelper.SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM);
        }
        if (helper != null && mFragmentParameter != null) {
            overridePendingTransition(mFragmentParameter.mAnimationRes[0],mFragmentParameter.mAnimationRes[1]);
            mCurrentFragment = helper.ensureFragment(mFragmentParameter);
        }

    }

    @Override
    public void finish() {
        if(mFragmentParameter != null && mFragmentParameter.mResultCode != -1){
            setResult(mFragmentParameter.getResultCode(),mFragmentParameter.getResultParams());
        }
        super.finish();
        if(mFragmentParameter != null){
            overridePendingTransition(mFragmentParameter.mAnimationRes[2],mFragmentParameter.mAnimationRes[3]);
        }
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }


    @Override
    public ActionBar getActionBar() {
        return super.getActionBar();
    }

    public AbsFragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
