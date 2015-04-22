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
 * @Time Create by 2015/4/22 11:00
 */
public interface ResponseDelivery {

    /**
     * Parses a response from the database.
     */
    public <T> void postResponse(DbResponseCallBack<T> callBack, Response<?> response);

    /**
     * Posts an error for the given request.
     */
    public <T> void postError(DbResponseCallBack<T> callBack, DbException error);
}
