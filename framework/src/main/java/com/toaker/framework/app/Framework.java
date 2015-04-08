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
package com.toaker.framework.app;

import android.content.Context;

import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.utils.ResourceUtils;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/8 11:39
 */
public class Framework {

    private static Framework instance;

    private Context mContext;

    private Framework(Context context){
        this.mContext = context;
    }

    public static Framework getInstance(){
        if(instance == null){
            throw new IllegalArgumentException("The Framework is uninitialized");
        }
        return instance;
    }

    public Context getContext(){
        return mContext;
    }

    /**
     * initialize
     * @param context
     */
    public static void init(Context context){
        if(context == null){
            throw new IllegalArgumentException("Initialization of the Context cannot be NULL");
        }
        if(instance != null){
            instance = new Framework(context);
        }
        ResourceUtils.init(context);
        ScaleController.init(context);
    }
}
