package com.toaker.framework.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.ListenerWrapper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonDataRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.VolleyErrorWrapper;
import com.google.gson.Gson;
import com.toaker.commons.db.NativeDbManager;
import com.toaker.commons.db.exception.DbException;
import com.toaker.framework.core.surface.FragmentParameter;
import com.toaker.framework.core.surface.activity.BaseActionBarActivity;


public class VolleyActivity extends BaseActionBarActivity implements AdapterView.OnItemClickListener {

    private HomeData  mData;

    RequestQueue mRequestQueue;

    Gson mGson = new Gson();

    private ListAdapter mListAdapter;

    private ListView     mListView;

    private NativeDbManager nativeDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NativeDbManager.init(this, "CitySlogan.db");
        nativeDbManager = NativeDbManager.getInstance("CitySlogan.db");
        setContentView(R.layout.activity_volley);
        mRequestQueue = Volley.newRequestQueue(this);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setOnItemClickListener(this);
        mListAdapter = new ListAdapter();
        mListView.setAdapter(mListAdapter);

        mJsonData.setShouldCache(true);
        mJsonData.setRefreshNeeded(false);
        mRequestQueue.add(mJsonData);

        try {
            nativeDbManager.findAll(CitySlogan.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    JsonDataRequest<HomeData> mJsonData = new JsonDataRequest<HomeData>(HomeData.class,Request.Method.GET,
            "http://www.xiaodao360.com/dao.php/App/Activity/activity_list",new ListenerWrapper<HomeData>() {
        @Override
        public void onSuccess(HomeData response) {
            mData = response;
            if(mData != null){
                mListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(VolleyErrorWrapper error) {
            Log.e("DDDD",error.errMessage);
        }
    });

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        jumpFragment(new FragmentParameter(VitalHomeFragment.class));
    }


    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(mData == null || mData.data == null){
                return 0;
            }
            return mData.data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView == null){
                textView = new TextView(VolleyActivity.this);
                convertView = textView;
                textView.setPadding(20,20,20,20);
            }else {
                textView = (TextView) convertView;
            }
            textView.setText(mData.data.get(position).toString());
            return convertView;
        }
    }
}
