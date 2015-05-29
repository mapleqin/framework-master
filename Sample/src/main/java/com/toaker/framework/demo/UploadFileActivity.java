/**
 * <pre>
 * Copyright 2014-2019 Soulwolf framework-master
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 */
package com.toaker.framework.demo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.ListenerWrapper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonDataRequest;
import com.android.volley.toolbox.RequestParameter;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.VolleyErrorWrapper;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * author : Soulwolf Create by 2015/5/27 16:12
 * email  : ToakerQin@gmail.com.
 */
public class UploadFileActivity extends Activity{

    private static final String BASE_DOMIN = "http://test.xiaodao360.com/";

    private static final int IMAGE_REQUEST_CODE = 10086;

    RequestQueue mRequestQueue;

    ImageView mImageView;

    ProgressBar mProgressBar;

    File      mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_upload);
        mRequestQueue = Volley.newRequestQueue(this);
        mImageView = (ImageView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    /**
     * Select picture;
     * @param view
     */
    public void onClick(View view){
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
    }

    public void onUpload(View view){
        if(mFilePath == null || !mFilePath.exists()){
            Toast.makeText(this,"Please select a picture",Toast.LENGTH_SHORT).show();
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        RequestParameter params = new RequestParameter();
        params.add("mid", "97706");
        params.add("photo", /*mFilePath.getName().replace(".", ""));
        params.add(mFilePath.getName().replace(".", ""), */mFilePath);
        JsonDataRequest<ResultData> request  = new JsonDataRequest<ResultData>(ResultData.class, Request.Method.POST,
                BASE_DOMIN + "dao.php/App/Member/logo", params, new ListenerWrapper<ResultData>() {
            @Override
            public void onSuccess(ResultData response) {
                if(response != null){
                    if(response.status == 1){
                        showImage(response.msg);
                    }else {
                        Toast.makeText(getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(),"Upload file error!",Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(VolleyErrorWrapper error) {
                Toast.makeText(getContext(),error.errMessage,Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        }, false);
        mRequestQueue.add(request);
    }

    public void showImage(String url){
        if(TextUtils.isEmpty(url)){
            Toast.makeText(getContext(),"Show image Url == NULL",Toast.LENGTH_SHORT).show();
            return;
        }
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(this) //
                .load(String.format("%s%s",BASE_DOMIN,url)) //
//                .placeholder(R.drawable.placeholder) //
//                .error(R.drawable.error) //
                .fit() //
                .tag(this) //
                .into(mImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            mFilePath = getImageAbsolutePath(data.getData());
        }
    }

    public File getImageAbsolutePath(Uri uri){
        String[] pro = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor actualImageCursor = managedQuery(uri, pro, null, null,
                null);
        int actual_image_column_index = actualImageCursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualImageCursor.moveToFirst();
        return new File(actualImageCursor.getString(actual_image_column_index));
    }

    public Activity getContext(){
        return this;
    }
}
