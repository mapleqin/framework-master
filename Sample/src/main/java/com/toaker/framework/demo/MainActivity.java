package com.toaker.framework.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.toaker.common.tlog.TLog;
import com.toaker.commons.db.DbUtils;
import com.toaker.commons.db.callback.DbResponseCallBack;
import com.toaker.commons.db.exception.DbException;
import com.toaker.framework.app.Framework;
import com.toaker.framework.core.component.NavigationBar;
import com.toaker.framework.core.surface.activity.BaseActionBarFragmentActivity;
import com.toaker.framework.core.widget.NavigationBarImpl;

import java.util.List;


public class MainActivity extends BaseActionBarFragmentActivity implements NavigationBar.NavigationChangeListener, DbResponseCallBack<List<Test>> {

    private static int VERSION = 1;

    private static final String LOG_TAG = "MainActivity " + ++VERSION;

    private static final boolean DEBUG = true;

    private static String [] TITLES = new String[]{"广场","消息","好友","设置"};

    private NavigationBar mNavigationBar;

    private DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Framework.init(this);
        super.onCreate(savedInstanceState);
        DbUtils.init(this);
        dbUtils = DbUtils.getInstance();
        setContentView(R.layout.activity_main);
        mNavigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        init();

//        List<Test> data = new ArrayList<Test>();
//        for (int i=0;i<50;i++){
//            data.add(new Test("name:" + i));
//        }
        dbUtils.findAll(Test.class,this);
        if(DEBUG){
            TLog.d(LOG_TAG,"》》》》》》》");
        }
    }

    private void init() {
//        mActionBarWrapper.setCenterTextColor(Color.WHITE);
//        mActionBarWrapper.setDisplayShowTitleEnabled(false);
//        mActionBarWrapper.setCenterTextSize(18);
//        mActionBarWrapper.setDisplayShowHomeEnabled(false);

        mNavigationBar.setBackgroundColor(0xFF484848);
        mNavigationBar.setNavigationChangeListener(this);

        NavigationBarImpl.IconNavigationImpl square = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_square_def, R.drawable.tab_square_sel, 0xFFFFFFFF, 0xFF4DD487, TITLES[0]);
        square.setPadding(15);
        square.setSpacer(10);
        mNavigationBar.addNavigation(square);

        NavigationBarImpl.IconNavigationImpl message = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_message_def, R.drawable.tab_message_sel, 0xFFFFFFFF, 0xFF4DD487, TITLES[1]);
        message.setPadding(15);
        message.setSpacer(10);
        mNavigationBar.addNavigation(message);

        NavigationBarImpl.IconNavigationImpl friend = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_friend_def, R.drawable.tab_friend_sel, 0xFFFFFFFF, 0xFF4DD487, TITLES[2]);
        friend.setPadding(15);
        friend.setSpacer(10);
        mNavigationBar.addNavigation(friend);

        NavigationBarImpl.IconNavigationImpl setting = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_setting_def, R.drawable.tab_setting_sel, 0xFFFFFFFF, 0xFF4DD487, TITLES[3]);
        setting.setPadding(15);
        setting.setSpacer(10);
        mNavigationBar.addNavigation(setting);
        mNavigationBar.setCurrentItem(0);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(VitalHomeFragment.class.getSimpleName());
        if(fragment == null){
            fragment = Fragment.instantiate(this,VitalHomeFragment.class.getName());
            fragmentTransaction.add(R.id.replace, fragment, VitalHomeFragment.class.getSimpleName());
        }else {
            fragmentTransaction.attach(fragment);
        }
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.volley){
            //jumpFragment(new FragmentParameter(VitalHomeFragment.class));
            startActivity(new Intent(this,VolleyActivity.class));
            return true;
        }else if(item.getItemId() == R.id.upload){
            startActivity(new Intent(this,UploadFileActivity.class));
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onChange(View view, NavigationBar.Navigation navigation, int position) {
//        mActionBarWrapper.setCenterTitle(TITLES[position]);
    }

    @Override
    public void onAccessSuccess(List<Test> response) {
        if(DEBUG){
            TLog.d(LOG_TAG,"onAccessSuccess ,%s",response);
        }
        mNavigationBar.setBackgroundColor(0xFFFF0000);
    }

    @Override
    public void onAccessError(DbException error) {
        if(DEBUG){
            TLog.d(LOG_TAG,"onAccessError : %s",error.getLocalizedMessage());
        }
    }
}
