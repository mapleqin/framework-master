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

import com.android.volley.toolbox.ResponseWrapper;

/**
 * author : Soulwolf Create by 2015/5/27 16:53
 * email  : ToakerQin@gmail.com.
 */
public class ResultData implements ResponseWrapper {

    public int status;

    public String msg;

    @Override
    public String toString() {
        return "ResultData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
