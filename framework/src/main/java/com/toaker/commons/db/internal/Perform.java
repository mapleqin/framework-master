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
package com.toaker.commons.db.internal;

import com.toaker.commons.db.callback.DbResponseCallBack;
import com.toaker.commons.db.exception.DbException;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/22 11:58
 */
public class Perform <T>{

    private boolean isCancel;

    private final PerformDelivery<T> mPerformDelivery;

    public final DbResponseCallBack<T> mCallBack;

    public Perform(PerformDelivery<T> performDelivery,DbResponseCallBack<T> callBack){
        if(performDelivery == null){
            throw new IllegalArgumentException("PerformDelivery cannot be NULL!");
        }
        mCallBack = callBack;
        mPerformDelivery = performDelivery;
    }

    public T execute() throws DbException{
        return mPerformDelivery.execute();
    }

    public void cancel(){
        this.isCancel = true;
    }

    public boolean isCancel(){
        return this.isCancel;
    }

    public interface PerformDelivery<T>{

        public T execute() throws DbException;
    }
}
