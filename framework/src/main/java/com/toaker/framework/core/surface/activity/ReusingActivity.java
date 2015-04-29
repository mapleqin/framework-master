package com.toaker.framework.core.surface.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.toaker.framework.R;
import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.fragment.AbsFragment;
import com.toaker.framework.core.widget.TitleBar;

public class ReusingActivity extends BaseActionBarFragmentActivity {

    private ReusingActivityHelper helper;

    private AbsFragment mCurrentFragment;

    private FragmentParameter mFragmentParameter;

    private FrameLayout mTitleBarGroup;

    private TitleBar    mTitleBar;

    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ReusingActivityHelper.isSingleFragmentIntent(this)) {
            helper = new ReusingActivityHelper(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reusing);
        mInflater = LayoutInflater.from(this);
        mTitleBarGroup = (FrameLayout) findViewById(R.id.fi_title_bar_group);
        if(getIntent() != null){
            mFragmentParameter = getIntent().getParcelableExtra(ReusingActivityHelper.SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM);
        }
        if (helper != null && mFragmentParameter != null) {
            overridePendingTransition(mFragmentParameter.mAnimationRes[0],mFragmentParameter.mAnimationRes[1]);
            mCurrentFragment = helper.ensureFragment(mFragmentParameter);
        }
        if(mCurrentFragment != null){
            if(mCurrentFragment.attachTitleBar(mInflater,mTitleBarGroup)){
                if(mTitleBar != null){
                    mTitleBar.setVisibility(View.GONE);
                }
            }else {
                if(mTitleBar != null && mTitleBarGroup.indexOfChild(mTitleBar) != -1){
                    mTitleBar.setVisibility(View.VISIBLE);
                }else {
                    mTitleBarGroup.removeAllViews();
                    mInflater.inflate(R.layout.title_bar_default,mTitleBarGroup);
                    mTitleBar = (TitleBar) mTitleBarGroup.findViewById(R.id.title_bar);
                }
            }
        }
    }

    public TitleBar getTitleBar(){
        return mTitleBar;
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
