package com.toaker.framework.demo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import com.toaker.framework.core.surface.activity.BaseActionBarActivity;

import java.util.ArrayList;


public class VolleyActivity extends BaseActionBarActivity {

    private ArrayList<String>  mData;

    RequestQueue mRequestQueue;

    Gson mGson = new Gson();

    private ListAdapter mListAdapter;

    private ListView     mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        mRequestQueue = Volley.newRequestQueue(this);
        mListView = (ListView) findViewById(R.id.list);
        mListAdapter = new ListAdapter();
        mListView.setAdapter(mListAdapter);

        mJsonData.setShouldCache(true);
        mJsonData.setRefreshNeeded(false);
        mRequestQueue.add(mJsonData);
    }


    JsonDataRequest<ArrayList<String>> mJsonData = new JsonDataRequest<ArrayList<String>>(Request.Method.GET,
            "http://www.svmeng.com/volley_test.php?page=1",null,new ListenerWrapper<ArrayList<String>>() {
        @Override
        public void onSuccess(ArrayList<String> response) {
            mData = response;
            if(mData != null){
                mListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(VolleyErrorWrapper error) {

        }
    });


    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(mData == null){
                return 0;
            }
            return mData.size();
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
            textView.setText(mData.get(position));
            return convertView;
        }
    }
}
