package com.toaker.framework.core.component;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.toaker.framework.app.Framework;

/**
 * Created by Daniel on 2015/3/9.
 */
public class FrameworkImageLoad extends ImageLoader {

    // from SD card
    public static String SD_BASE_PATH = "file://";

    // from content provider
    public static String MEDIA_BASE_PATH = "content://";

    // from assets
    public static String ASSETS_BASE_PATH = "assets://";

    // from drawables (only images, non-9patch)
    public static String DRAWABLE_BASE_PATH = "drawable://" ;

    private volatile static FrameworkImageLoad instance;

    /** Returns singleton class instance */
    public static FrameworkImageLoad getInstance() {
        if (instance == null) {
            synchronized (FrameworkImageLoad.class) {
                if (instance == null) {
                    instance = new FrameworkImageLoad();
                }
            }
        }
        return instance;
    }

    protected FrameworkImageLoad() {
    }

    public <T extends ImageView> void displayRelative(T container, String uri) {
        super.displayImage(Framework.getInstance() + uri,container);
    }


    public <T extends ImageView> void displayRelative(T container, String uri, DisplayImageOptions displayConfig) {
        super.displayImage(Framework.getInstance().getBaseUrl() + uri,container,displayConfig);
    }

    public <T extends ImageView> void displayForSDCard(T container, String uri) {
        super.displayImage(FrameworkImageLoad.SD_BASE_PATH + uri,container);
    }

    public <T extends ImageView> void displayForMedia(T container, String uri) {
        super.displayImage(FrameworkImageLoad.MEDIA_BASE_PATH + uri,container);
    }

    public <T extends ImageView> void displayForDrawable(T container, int uri) {
        super.displayImage(FrameworkImageLoad.DRAWABLE_BASE_PATH + uri,container);
    }

    public <T extends ImageView> void displayForAssets(T container, String uri) {
        super.displayImage(FrameworkImageLoad.ASSETS_BASE_PATH + uri,container);
    }

    public <T extends ImageView> void display(T container, String uri) {
        super.displayImage(uri,container);
    }

    public <T extends ImageView> void display(T container, String uri, DisplayImageOptions displayConfig) {
        super.displayImage(uri,container,displayConfig);
    }

    public <T extends ImageView> void display(T container, String uri, ImageLoadingListener callBack) {
        super.displayImage(uri,container,callBack);
    }

    public <T extends ImageView> void display(T container, String uri, DisplayImageOptions displayConfig, ImageLoadingListener callBack) {
        super.displayImage(uri,container,displayConfig,callBack);
    }

    public Bitmap loadImageSync(String uri){
        return super.loadImageSync(uri);
    }
}
