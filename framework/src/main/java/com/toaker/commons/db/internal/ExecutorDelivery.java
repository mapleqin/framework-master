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

import android.os.Handler;

import com.toaker.commons.db.callback.DbResponseCallBack;
import com.toaker.commons.db.exception.DbException;

import java.util.concurrent.Executor;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/22 11:17
 *
 * Delivers responses and errors.
 */
public class ExecutorDelivery implements ResponseDelivery{

    /** Used for posting responses, typically to the main thread. */
    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler){
        mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    @Override
    public <T> void postResponse(DbResponseCallBack<T> callBack, Response<?> response) {
        mResponsePoster.execute(new ResponseDeliveryRunnable<T>(callBack,response));
    }

    @Override
    public <T> void postError(DbResponseCallBack<T> callBack, DbException error) {
        mResponsePoster.execute(new ResponseDeliveryRunnable<T>(callBack, Response.<T>error(error)));
    }

    /**
     * A Runnable used for delivering network responses to a listener on the
     * main thread.
     */
    @SuppressWarnings("rawtypes")
    private class ResponseDeliveryRunnable<T> implements Runnable {

        private final DbResponseCallBack<T> mCallBack;

        private final Response<?>           mResponse;

        public ResponseDeliveryRunnable(DbResponseCallBack<T> callBack, Response<?> response) {
            mCallBack = callBack;
            mResponse = response;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            if(mCallBack == null){
                return;
            }
            if(mResponse != null){
                if(mResponse.result != null){
                   mCallBack.onAccessSuccess((T) mResponse.result);
                }
                if(mResponse.error != null){
                    mCallBack.onAccessError(mResponse.error);
                }
            }else {
                mCallBack.onAccessError(new DbException("The parameters of the illegal Response!"));
            }
        }
    }
}
