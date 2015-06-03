/*******************************************************************************
 * Copyright 2013-2014 Toaker framework-master
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.toaker.framework.core.surface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.Response;
import com.toaker.framework.R;
import com.toaker.framework.core.surface.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator for framework-master
 *
 * author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * Time Create by 2015/4/8 22:44
 */
public class FragmentParameter implements Parcelable{

    public Class              mFragmentClass;

    public Bundle             mParams;

    public String             mTag;

    public int                mRequestCode = -1;

    public int                mResultCode = -1;

    public int                mFlags;

    public int[]              mAnimationRes = new int[]{R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit,
            R.anim.fragment_slide_right_back_in,R.anim.fragment_slide_left_back_out};

    public Intent             mResultParams;

    public <T extends AbsFragment> FragmentParameter(Class<T> fragmentClass){
        if(fragmentClass == null){
            throw new IllegalArgumentException("To jump fragments cannot be NULL");
        }
        this.mFragmentClass = fragmentClass;
        this.mTag = fragmentClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    public Class<? extends AbsFragment> getFragmentClass() {
        return mFragmentClass;
    }

    /**
     * @param flag {@link Intent} of Flags;
     * @return  self;
     */
    public FragmentParameter addFlags(int flag){
        mFlags |= flag;
        return this;
    }

    public FragmentParameter clearFlags(){
        mFlags = 0;
        return this;
    }

    /**
     * @param flag {@link Intent} is Flags;
     * @return  self;
     */
    public FragmentParameter removeFlag(int flag){
        mFlags &= ~flag;
        return this;
    }

    public int getFlags(){
        return mFlags;
    }

    public Intent getResultParams() {
        return mResultParams;
    }

    public FragmentParameter setResultParams(Intent mResultParams) {
        this.mResultParams = mResultParams;
        return this;
    }

    public String getTag() {
        return mTag;
    }

    public FragmentParameter setTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public Bundle getParams() {
        return mParams;
    }

    public FragmentParameter setParams(Bundle mParams) {
        this.mParams = mParams;
        return this;
    }

    public int getRequestCode() {
        return mRequestCode;
    }

    public FragmentParameter setRequestCode(int mRequestCode) {
        this.mRequestCode = mRequestCode;
        return this;
    }

    public int getResultCode() {
        return mResultCode;
    }

    public FragmentParameter setResultCode(int mResultCode) {
        this.mResultCode = mResultCode;
        return this;
    }

    public int[] getAnimationRes() {
        return mAnimationRes;
    }

    public FragmentParameter setAnimationRes(int[] mAnimationRes) {
        this.mAnimationRes = mAnimationRes;
        return this;
    }


    public FragmentParameter(Parcel in) {

        mFragmentClass = (Class) in.readSerializable();

        mParams = in.readBundle();

        mTag = in.readString();

        mRequestCode = in.readInt();

        mResultCode = in.readInt();

        int size = in.readInt();
        mAnimationRes = new int[size];
        in.readIntArray(mAnimationRes);

        mResultParams = in.readParcelable(getClass().getClassLoader());

    }

    public static final Creator<FragmentParameter> CREATOR = new Creator<FragmentParameter>() {

        @Override
        public FragmentParameter createFromParcel(Parcel source) {

            return new FragmentParameter(source);

        }

        @Override
        public FragmentParameter[] newArray(int size) {

            return new FragmentParameter[size];

        }

    };

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeSerializable(mFragmentClass);

        dest.writeBundle(mParams);

        dest.writeString(mTag);

        dest.writeInt(mRequestCode);

        dest.writeInt(mResultCode);

        int size = 0;
        if(mAnimationRes != null){
            size = mAnimationRes.length;
        }
        dest.writeInt(size);

        dest.writeIntArray(mAnimationRes);

        dest.writeParcelable(mResultParams,flags);
    }
}
