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

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/22 11:47
 */
public class PerformQueue {

    /** The queue of requests that are actually going out to the database. */
    private final PriorityBlockingQueue<Perform<?>> mPerformQueue =
            new PriorityBlockingQueue<Perform<?>>();

    private final ResponseDelivery mResponseDelivery;

    private final PerformDispatcher[] mPerformDispatchers;

    public PerformQueue(ResponseDelivery responseDelivery,int threadPoolSize){
        mResponseDelivery = responseDelivery;
        mPerformDispatchers = new PerformDispatcher[threadPoolSize];
    }

    public PerformQueue(ResponseDelivery responseDelivery){
        this(responseDelivery,10);
    }

    public void start(){
        for(int i=0;i<mPerformDispatchers.length;i++){
            mPerformDispatchers[i] = new PerformDispatcher(mPerformQueue,mResponseDelivery);
            mPerformDispatchers[i].start();
        }
    }

    public void stop(){
        for(int i=0;i<mPerformDispatchers.length;i++){
            mPerformDispatchers[i].quit();
        }
    }

    public void cancelAll(){
        for(Perform<?> perform: mPerformQueue){
            if(perform != null){
                perform.cancel();
            }
        }
    }

    public<T> Perform<T> add(Perform<T> perform){
        if(perform == null){
            return null;
        }
        mPerformQueue.add(perform);
        return perform;
    }
}
