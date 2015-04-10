package com.toaker.framework.core.surface.fragment;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.activity.ReusingActivity;
import com.toaker.framework.core.surface.activity.ReusingActivityHelper;


public abstract class AbsFragment extends Fragment {

	
	public AbsFragment() {
	}

	/**
	 * View
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(getResourceLayoutId(),container,false);
	}

    public abstract int getResourceLayoutId();

	/**
	 * 关闭当前页面
	 */
	public void finish() {
		getActivity().onBackPressed();
	}


	/**
	 * 注册广播接收者
	 * 
	 * @param receiver
	 * @param filter
	 * @return
	 */
	public Intent registerReceiver(BroadcastReceiver receiver,
			IntentFilter filter) {
		return getActivity().registerReceiver(receiver, filter);
	}


    public void sendBroadcast(Intent intent) {
        getActivity().sendBroadcast(intent);
    }

    public void sendBroadcast(Intent intent, String receiverPermission) {
        getActivity().sendBroadcast(intent, receiverPermission);
    }

	/**
	 * Remove the data previously sent with sendStickyBroadcast, so that it is
	 * as if the sticky broadcast had never happened.
	 * 
	 * You must hold the android.Manifest.permission.BROADCAST_STICKY permission
	 * in order to use this API. If you do not hold that permission,
	 * SecurityException will be thrown.
	 * 
	 * 
	 * @param intent
	 */
	public void removeStickyBroadcast(Intent intent) {
        getActivity().removeStickyBroadcast(intent);
	}

	/**
	 * 启动服务
	 * @param service
	 * @return
	 */
	public ComponentName startService(Intent service) {
		return getActivity().startService(service);
	}

	/**
	 * 停止服务
	 * @param name
	 * @return
	 */
	public boolean stopService(Intent name) {
		return getActivity().stopService(name);
	}

	/**
	 * 绑定服务
	 * @param service
	 * @param conn
	 * @param flags
	 * @return
	 */
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return getActivity().bindService(service, conn, flags);
	}

	/**
	 * 解除服务的绑定
	 * @param conn
	 */
	public void unbindService(ServiceConnection conn) {
		getActivity().unbindService(conn);
	}

	/**
	 * 解除广播接收者
	 * @param receiver
	 */
	public void unregisterReceiver(BroadcastReceiver receiver) {
		getActivity().unregisterReceiver(receiver);
	}

	/**
	 * 获取一个系统服务
	 * 
	 * @param name
	 * @return
	 */
	public Object getSystemService(String name) {
		return getActivity().getSystemService(name);
	}

	/**
	 * 开启一个新的Activity
	 */
	public void startActivity(Intent intent) {
		getActivity().startActivity(intent);
	}

	/**
	 * 找到控件
	 * 
	 * @param resId
	 * @return
	 */
	public<T> T findViewById(int resId) {
		return (T) this.getView().findViewById(resId);
	}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    public FragmentParameter getFragmentParameter(){
        if(getReusingActivity() != null){
            return getReusingActivity().getFragmentParameter();
        }
        return null;
    }

    public ReusingActivity getReusingActivity(){
        if(getActivity() instanceof ReusingActivity){
            return (ReusingActivity)getActivity();
        }
        return null;
    }

    public void jumpFragment(FragmentParameter parameter){
        if(parameter == null || parameter.getFragmentClass() == null){
            throw new IllegalArgumentException("Want to jump the fragments of information cannot be NULL");
        }
        Intent intent = ReusingActivityHelper.builder(getActivity()).setFragmentParameter(parameter).build();
        if(parameter.getRequestCode() != -1){
            startActivityForResult(intent,parameter.getRequestCode());
        }else {
            startActivity(intent);
        }
    }
}
