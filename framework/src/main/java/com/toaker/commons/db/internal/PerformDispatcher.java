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

import android.os.Process;

import com.toaker.commons.db.exception.DbException;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/22 15:01
 */
public class PerformDispatcher extends Thread{

    /** The queue of requests that are actually going out to the database. */
    private final PriorityBlockingQueue<Perform<?>> mPerformQueue;

    private final ResponseDelivery   mExecutorDelivery;

    public boolean mQuit;

    public PerformDispatcher(PriorityBlockingQueue<Perform<?>> performs,ResponseDelivery executorDelivery){
        mPerformQueue = performs;
        mExecutorDelivery = executorDelivery;
    }

    public void quit(){
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while (true){
            Perform<?> perform;
            try {
                perform = mPerformQueue.take();
            } catch (InterruptedException e) {
                if(mQuit){
                    return;
                }
                continue;
            }
            try {
                if(perform.isCancel()){
                    continue;
                }
                mExecutorDelivery.postResponse(perform.mCallBack, (Response<?>) perform.execute());
            }catch (DbException error){
                mExecutorDelivery.postError(perform.mCallBack,error);
            }catch (Exception e){
                mExecutorDelivery.postError(perform.mCallBack,new DbException(e));
            }
        }
    }
}
