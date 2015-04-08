package com.toaker.framework.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Field;

public class ResourceUtils {

    private Context mContext;

    private static final String LOG_TAG = ResourceUtils.class.getName();

    private static ResourceUtils instance = null;

    private static String packageName = null;

    private static Class R_id = null;

    private static Class R_drawable = null;

    private static Class R_layout = null;

    private static Class R_anim = null;

    private static Class R_style = null;

    private static Class R_string = null;

    private static Class R_array = null;

    private static Class R_color = null;

    private static Class R_dimen = null;

    private static Class R_integer = null;

    private static Class R_bool = null;

    private static Class R_styleable = null;

    private static Class R_attr = null;

    private ResourceUtils(Context context,String packageName) {
        this.mContext = context;
        try {
            R_drawable = Class.forName(packageName + ".R$drawable");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_layout = Class.forName(packageName + ".R$layout");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_id = Class.forName(packageName + ".R$id");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_anim = Class.forName(packageName + ".R$anim");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_style = Class.forName(packageName + ".R$style");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_string = Class.forName(packageName + ".R$string");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_array = Class.forName(packageName + ".R$array");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_color = Class.forName(packageName + ".R$color");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_dimen = Class.forName(packageName + ".R$dimen");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_integer = Class.forName(packageName + ".R$integer");
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        try {
            R_bool = Class.forName(packageName + ".R$bool");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            R_styleable = Class.forName(packageName + ".R$styleable");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            R_attr = Class.forName(packageName + ".R$attr");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void init(Context context){
        if(instance == null){
            packageName = packageName != null ? packageName : context.getPackageName();

            instance = new ResourceUtils(context,packageName);
        }
    }

    public static ResourceUtils getInstance() {
        if (instance == null) {
            throw new IllegalArgumentException("The ResourceUtils is uninitialized");
        }
        return instance;
    }

    public static void setPackageName(String pn) {
        packageName = pn;
    }

    public int anim(String field) {
        return getRes(R_anim, field);
    }

    public int id(String field) {
        return getRes(R_id, field);
    }

    public int drawable(String field) {
        return getRes(R_drawable, field);
    }

    public int layout(String field) {
        return getRes(R_layout, field);
    }

    public int style(String field) {
        return getRes(R_style, field);
    }

    public int string(String field) {
        return getRes(R_string, field);
    }

    public int array(String field) {
        return getRes(R_array, field);
    }

    public int color(String field) {
        return getRes(R_color, field);
    }

    public int dimen(String field) {
        return getRes(R_dimen, field);
    }

    public int integer(String field) {
        return getRes(R_integer, field);
    }

    public int bool(String field) {
        return getRes(R_bool, field);
    }

    public int styleable(String field) {
        return getRes(R_styleable, field);
    }

    public int attr(String field) {
        return getRes(R_attr, field);
    }

    public int[] styleables(String field) {
        return getResArr(R_styleable, field);
    }


    public Drawable getDrawable(int resId) {
        return mContext.getResources().getDrawable(resId);
    }

    public String getString(int resId) {
        return mContext.getResources().getString(resId);
    }

    public int[] getIntArray(int resId) {
        return mContext.getResources().getIntArray(resId);
    }

    public String[] getStringArray(int resId) {
        return mContext.getResources().getStringArray(resId);
    }

    public CharSequence[] getTextArray(int resId) {
        return mContext.getResources().getTextArray(resId);
    }

    public int getColor(int resId) {
        return mContext.getResources().getColor(resId);
    }

    public float getDimension(int resId) {
        return mContext.getResources().getDimension(resId);
    }

    public Integer getInteger(int resId) {
        return mContext.getResources().getInteger(resId);
    }

    public boolean getBoolean(int resId) {
        return mContext.getResources().getBoolean(resId);
    }

    private int getRes(Class<?> ResClass, String field) {
        if (ResClass == null) {
            Log.e(LOG_TAG, "getRes(null," + field + ")");
            throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added necessary resources. Also make sure you have " + packageName + ".R$* configured in obfuscation. field=" + field);
        }
        try {
            Field idField = ResClass.getField(field);
            return idField.getInt(field);
        } catch (Exception e) {
            Log.e(LOG_TAG, "getRes(" + ResClass.getName() + ", " + field + ")");
            Log.e(LOG_TAG, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
        }
        return -1;
    }

    private int[] getResArr(Class<?> ResClass, String field) {
        try {
            if ((ResClass != null) && (ResClass.getDeclaredField(field).get(ResClass) != null) && (ResClass.getDeclaredField(field).get(ResClass).getClass().isArray()))
                return (int[]) ResClass.getDeclaredField(field).get(ResClass);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}