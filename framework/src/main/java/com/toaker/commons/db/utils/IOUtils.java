/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.toaker.commons.db.utils;

import android.content.Context;
import android.database.Cursor;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: wyouflf
 * Date: 13-8-26
 * Time: 下午6:02
 */
public class IOUtils {

    private IOUtils() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable e) {
            }
        }
    }

    /**
     * assets资源目录下的address.db拷贝到data/data/
     * .db
     */
    public static String copyDatabase(Context context, String dbName) {
        try {
            File file = new File(context.getFilesDir(), dbName);
            if (file.exists() && file.length() > 0) {

            } else {
                InputStream is = context.getAssets().open(dbName);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                is.close();
            }
            return file.getAbsolutePath();
        } catch (IOException e) {
            return null;
        }
    }
}
