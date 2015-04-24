package com.toaker.framework.core.surface.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.toaker.framework.R;
import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.fragment.AbsFragment;

public class ReusingActivity extends BaseActionBarActivity {

    private ReusingActivityHelper helper;

    private AbsFragment mCurrentFragment;

    private FragmentParameter mFragmentParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ReusingActivityHelper.isSingleFragmentIntent(this)) {
            helper = new ReusingActivityHelper(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reusing);
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


    public AbsFragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mCurrentFragment != null){
            return super.onKeyDown(keyCode,event) && mCurrentFragment.onKeyDown(keyCode,event);
        }
        return super.onKeyDown(keyCode, event);
    }

    public FragmentParameter getFragmentParameter() {
        return mFragmentParameter;
    }
}
