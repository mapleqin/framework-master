// Copyright (c) 2014-2-12 wanghb@dearcoin.com. All rights reserved.
package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.toaker.framework.utils.ResourceUtils;

public class VolleyErrorWrapper extends Exception {

    private static final long serialVersionUID = -7427897454872730424L;

    public static VolleyErrorWrapper allocate(VolleyError error) {
        int code = ErrorCode.unknownError;
        int emResId = ResourceUtils.getInstance().string("volley_error_message_unknown");
        if (error instanceof NoConnectionError) {
            code = ErrorCode.noConnection;
            emResId = ResourceUtils.getInstance().string("volley_error_message_no_connection");
        } else if (error instanceof NetworkError) {
            code = ErrorCode.networkError;
            emResId = ResourceUtils.getInstance().string("volley_error_message_network");
        } else if (error instanceof ServerError) {
            code = ErrorCode.serverError;
            emResId = ResourceUtils.getInstance().string("volley_error_message_server");
        } else if (error instanceof TimeoutError) {
            code = ErrorCode.timeoutError;
            emResId = ResourceUtils.getInstance().string("volley_error_message_timeout");
        } else if (error instanceof ParseError) {
            code = ErrorCode.parseError;
            emResId = ResourceUtils.getInstance().string("volley_error_message_parse");
        } else if (error instanceof AuthFailureError) {
            code = ErrorCode.authFailureError;
            emResId = ResourceUtils.getInstance().string("volley_error_message_auth_failure");
        }
        VolleyLog.d("volley error raw message : " + error.getMessage());
        VolleyErrorWrapper ex = new VolleyErrorWrapper(code,ResourceUtils.getInstance().getString(emResId));
        ex.error = error;
        return ex;
    }

    public int code;

    public VolleyError error;

    public String errMessage;

    public VolleyErrorWrapper(int code, String message) {
        this(code, message, null);
    }

    public VolleyErrorWrapper(int code, String message, Throwable tr) {
        super(message, tr);
        this.code = code;
        errMessage = message;
    }

    public interface ErrorCode {

        /**
         * 未知错误
         *
         * String Resource volley_error_message_unknown
         */
        int unknownError                                = 0x00000002;

        /**
         * 无连接
         *
         * String Resource volley_error_message_no_connection
         */
        int noConnection                                = 0x00000004;

        /**
         * 网络错误
         *
         * String Resource volley_error_message_network
         */
        int networkError                                = 0x00000008;

        /**
         * 认证失败
         *
         * String Resource volley_error_message_auth_failure
         */
        int authFailureError                            = 0x00000020;

        /**
         * 超时
         *
         * String Resource volley_error_message_timeout
         */
        int timeoutError                                = 0x00000040;

        /**
         * 解析时错误
         *
         * String Resource volley_error_message_parse
         */
        int parseError                                  = 0x00000080;

        /**
         * 服务器错误
         *
         * String Resource volley_error_message_server
         */
        int serverError                                 = 0x00000200;

    }
}
