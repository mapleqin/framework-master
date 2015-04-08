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
package com.android.volley;

import com.android.volley.toolbox.VolleyErrorWrapper;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/8 12:21
 */
public abstract class ListenerWrapper<T> implements Response.Listener<T>,Response.ErrorListener {

    @Override
    public void onResponse(T response) {
        onSuccess(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onError(VolleyErrorWrapper.allocate(error));
    }

    public abstract void onSuccess(T response);

    public abstract void onError(VolleyErrorWrapper error);
}
