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

import com.toaker.commons.db.exception.DbException;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/22 11:09
 */
public class Response<T> {

    /**
     * Returns a failed response containing the given error code and an optional
     * localized message displayed to the user.
     */
    public static <T> Response<T> error(DbException error) {
        return new Response<T>(error);
    }

    /** Parsed response, or null in the case of error. */
    public final T result;

    /** Detailed error information if <code>errorCode != OK</code>. */
    public final DbException error;

    /**
     * Returns whether this response is considered successful.
     */
    public boolean isSuccess() {
        return error == null;
    }

    private Response(T result) {
        this.result = result;
        this.error = null;
    }

    private Response(DbException error) {
        this.result = null;
        this.error = error;
    }
}
