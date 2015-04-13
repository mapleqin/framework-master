package com.toaker.framework.core.surface.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.toaker.framework.core.view.NoTouchLayout;

public abstract class BaseFragment extends AbsFragment implements View.OnClickListener{

    protected View        mEmptyLayout;

    protected View        mLoadingLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View getCustomView(LayoutInflater inflater, FrameLayout containerView, Bundle savedInstanceState, boolean isRoot) {
        super.getCustomView(inflater, containerView, savedInstanceState,isRoot);
        mEmptyLayout = attachEmptyLayout(inflater,containerView);
        mLoadingLayout =  attachLoadingLayout(inflater,containerView);
        return containerView;
    }

    /**
     * see Attach an empty status page
     * @param inflater
     * @param container
     * @return
     */
    protected View attachEmptyLayout(LayoutInflater inflater,ViewGroup container){
        return null;
    }

    /**
     * see Attach an loading status page
     * @param inflater
     * @param container
     * @return
     */
    protected View attachLoadingLayout(LayoutInflater inflater,ViewGroup container){
        NoTouchLayout mNoTouchLayout = new NoTouchLayout(getActivity());
        mNoTouchLayout.setBackgroundColor(Color.WHITE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ProgressBar mProgressBar = new ProgressBar(getActivity());
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mNoTouchLayout.addView(mProgressBar,params);
        container.addView(mNoTouchLayout,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mNoTouchLayout.setVisibility(View.GONE);
        return mNoTouchLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayEmptyLayout(true);
        displayLoadingLayout(true);
    }

    /**
     * Switch the empty status page displays a status
     * @param display
     */
    public void displayEmptyLayout(boolean display){
        if(mEmptyLayout != null){
            if(display && mEmptyLayout.getVisibility() != View.VISIBLE){
                mEmptyLayout.setVisibility(View.VISIBLE);
            }else if(!display && mEmptyLayout.getVisibility() != View.GONE){
                mEmptyLayout.setVisibility(View.GONE);
            }else {
                // Noting
            }
        }
    }

    /**
     * Switch the loading status page displays a status
     * @param display
     */
    public void displayLoadingLayout(boolean display){
        if(mLoadingLayout != null){
            if(display && mLoadingLayout.getVisibility() != View.VISIBLE){
                mLoadingLayout.setVisibility(View.VISIBLE);
            }else if(!display && mLoadingLayout.getVisibility() != View.GONE){
                mLoadingLayout.setVisibility(View.GONE);
            }else {
                // Noting
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
