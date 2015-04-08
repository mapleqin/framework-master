package com.toaker.framework.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.toaker.framework.app.Framework;
import com.toaker.framework.base.BaseActionBarActivity;
import com.toaker.framework.core.component.NavigationBar;
import com.toaker.framework.core.widget.NavigationBarImpl;


public class MainActivity extends BaseActionBarActivity implements NavigationBar.NavigationChangeListener {

    private static String [] TITLES = new String[]{"广场","消息","好友","设置"};

    private NavigationBar mNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Framework.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        init();
    }

    private void init() {
        mActionBarWrapper.setCenterTextColor(Color.WHITE);
        mActionBarWrapper.setDisplayShowTitleEnabled(false);
        mActionBarWrapper.setCenterTextSize(18);
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
            startActivity(new Intent(this, VolleyActivity.class));
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onChange(View view, NavigationBar.Navigation navigation, int position) {
        mActionBarWrapper.setCenterTitle(TITLES[position]);
    }
}
