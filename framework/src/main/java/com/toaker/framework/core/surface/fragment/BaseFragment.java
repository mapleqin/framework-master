package com.toaker.framework.core.surface.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xiaodao360.xiaodaow.R;
import com.xiaodao360.xiaodaow.utils.DbUtils;
import com.xiaodao360.xiaodaow.utils.FakerGlobal;
import com.xiaodao360.xiaodaow.utils.GsonUtils;
import com.xiaodao360.xiaodaow.utils.TKImageLoad;
import com.xiaodao360.xiaodaow.view.LoadingDialog;


public abstract class BaseFragment extends AbsFragment implements ReusingActivity.onBackPressedListener, FragmentAnimation,View.OnClickListener{

    protected TKImageLoad mBitmapUtils;

    protected DbUtils mDbManager;

    protected GsonUtils   mGsonManager;

    protected ImageButton mActionLeft;

    protected ImageButton mActionRight;

    protected TextView    mTextMiddle;

    protected RelativeLayout mTitleBarGroup;

    private InputMethodManager mInputManager;

    protected int titleBarH = 0;

    protected com.xiaodao360.xiaodaow.utils.net.request.HttpManager mVolleyManager;

    protected ViewGroup mLoadingLayout;

    protected LoadingDialog mLoadingDialog;

    ViewGroup decorView;

    View v;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
        v = LayoutInflater.from(getActivity()).inflate(R.layout.loading_default_layout,null);
        mLoadingLayout = (ViewGroup) v.findViewById(R.id.xi_loading_on_loading);
        decorView = (ViewGroup) getActivity().getWindow().getDecorView();
        if(decorView != null){
            decorView.addView(v);
        }

        mVolleyManager = new com.xiaodao360.xiaodaow.utils.net.request.HttpManager(getActivity());
        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        titleBarH = (int) (FakerGlobal.SCREEN_HEIGHT / 13.0f);
        mBitmapUtils = TKImageLoad.getInstance();
        mDbManager = DbUtils.getInstance();
        mGsonManager = new GsonUtils();
        mLoadingDialog = LoadingDialog.create(getActivity());
    }

    protected void registerBackListener(){
        if(mActionLeft == null){
            return;
        }
        mActionLeft.setImageResource(R.drawable.back_icon);
        mActionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        loadData();
    }

    public BaseFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActionLeft = F(R.id.xi_titlebar_left_action);
        mActionRight = F(R.id.xi_titlebar_right_action);
        mTextMiddle = F(R.id.xi_titlebar_middle_text);
        mTitleBarGroup = F(R.id.xi_titlebar_group);
        if(mTitleBarGroup != null) mTitleBarGroup.setLayoutParams(new RelativeLayout.LayoutParams(FakerGlobal.SCREEN_WIDTH, titleBarH));
        initializeTitle();
        init();
        setListener();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }

    protected abstract void initializeTitle();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onFinish() {
        if(isTopAnimation){
            getActivity().overridePendingTransition(R.anim.fragment_slide_bottom_back_in,R.anim.fragment_slide_top_back_out);
        }else {
            getActivity().overridePendingTransition(R.anim.fragment_slide_right_back_in, R.anim.fragment_slide_left_back_out);
        }
    }

    /**
	 * 初始化
	 */
	protected abstract void init();

    /**
     * 显示加载图像
     */
    public void showLoading(){
        if(mLoadingLayout != null&&mLoadingLayout.getVisibility() != View.VISIBLE){
            mLoadingLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏加载图像
     */
    public void hideLoading(){
        if(mLoadingLayout != null&&mLoadingLayout.getVisibility() != View.GONE){
            mLoadingLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 找到控件
     * @param resId
     * @param <T>
     * @return
     */
    protected <T> T F(int resId){
        if(getView() != null){
            return (T)(getView().findViewById(resId));
        }
        return null;
    }
	
	/**
	 * 设置监听
	 */
	protected abstract void  setListener();
	
	
	/**
	 * 加载数据
	 */
	protected abstract void  loadData();
	
	@Override
	public void onResume() {
		super.onResume();
        if(getActivity() instanceof ReusingActivity){
            ((ReusingActivity)getActivity()).setOnBackPressedListener(this);
        }
        MobclickAgent.onPageStart(((Object)this).getClass().getSimpleName()); //统计页面
        if(getView() != null)
        mInputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
	
	@Override
	public void onPause() {
		super.onPause();
        if(getActivity() instanceof ReusingActivity){
            ((ReusingActivity)getActivity()).removeOnBackPressedListener();
        }
        MobclickAgent.onPageEnd(((Object)this).getClass().getSimpleName());
        mInputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
	
	@Override
	public void onClick(View v) {
	}


    public TextView getTextMiddle() {
        return mTextMiddle;
    }

    public ImageButton getActionRight() {
        return mActionRight;
    }

    public ImageButton getActionLeft() {
        return mActionLeft;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(decorView != null && v.getParent() != null){
            decorView.removeView(v);
        }

    }
}
