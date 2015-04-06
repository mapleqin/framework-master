package com.toaker.framework.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.toaker.framework.core.component.NavigationBar;
import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.core.widget.NavigationBarImpl;


public class MainActivity extends ActionBarActivity implements NavigationBar.NavigationChangeListener {

    private NavigationBar mNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScaleController.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationBar = (NavigationBar) findViewById(R.id.navigation_bar);

        init();
    }

    private void init() {
        mNavigationBar.setBackgroundColor(Color.CYAN);
        mNavigationBar.setNavigationChangeListener(this);

        NavigationBarImpl.IconNavigationImpl square = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_square_def, R.drawable.tab_square_sel, 0xFFFFFFFF, 0xFF4DD487, "广场");
        square.setPadding(15);
        square.setSpacer(10);
        mNavigationBar.addNavigation(square);

        NavigationBarImpl.IconNavigationImpl message = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_message_def, R.drawable.tab_message_sel, 0xFFFFFFFF, 0xFF4DD487, "消息");
        message.setPadding(15);
        message.setSpacer(10);
        mNavigationBar.addNavigation(message);

        NavigationBarImpl.IconNavigationImpl friend = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_friend_def, R.drawable.tab_friend_sel, 0xFFFFFFFF, 0xFF4DD487, "好友");
        friend.setPadding(15);
        friend.setSpacer(10);
        mNavigationBar.addNavigation(friend);

        NavigationBarImpl.IconNavigationImpl setting = new NavigationBarImpl.IconNavigationImpl(
                this, R.drawable.tab_setting_def, R.drawable.tab_setting_sel, 0xFFFFFFFF, 0xFF4DD487, "设置");
        setting.setPadding(15);
        setting.setSpacer(10);
        mNavigationBar.addNavigation(setting);
        mNavigationBar.setCurrentItem(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChange(View view, NavigationBar.Navigation navigation, int position) {
        Toast.makeText(this,"点击"+position,Toast.LENGTH_LONG).show();
    }
}
