package com.toaker.framework.demo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.toaker.framework.base.BaseActionBarActivity;


public class VolleyActivity extends BaseActionBarActivity {


    private String[] mHomeData;

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

        request.setShouldCache(true);
        request.setRefreshNeeded(false);
        mRequestQueue.add(request);
    }

    StringRequest request = new StringRequest(Request.Method.GET, "http://www.svmeng.com/volley_test.php?page=1",new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mHomeData = mGson.fromJson(response, String[].class);
            if(mHomeData != null){
                mListAdapter.notifyDataSetChanged();
            }

        }
    },new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });


    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(mHomeData == null){
                return 0;
            }
            return mHomeData.length;
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
            textView.setText(mHomeData[position]);
            return convertView;
        }
    }
}
