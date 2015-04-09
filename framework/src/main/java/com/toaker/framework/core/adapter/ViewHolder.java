package com.toaker.framework.core.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toaker.framework.app.Framework;


public class ViewHolder {

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag  
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     *
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId  控件的id
     * @param wScreenProportion 宽度所占屏幕宽度的百分比(0.0 ~ 1.0)
     * @param wProportion       宽度的比例
     * @param hProportion       高度的比例
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId,float wScreenProportion,float wProportion,float hProportion) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            if(view != null){
                int width = (int) (Framework.getInstance().getScreenWidth() * wScreenProportion);
                int height = (int) ((width / wProportion ) * hProportion);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                if(params == null){
                    params = new ViewGroup.LayoutParams(width,height);
                }else{
                    params.width = width;
                    params.height = height;
                }
                view.setLayoutParams(params);
            }
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if(view != null){
            view.setText(text);
        }
        return this;
    }

    /**
     * 为view设置点击事件
     * @param viewId
     * @param tag
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId, Object tag,View.OnClickListener listener) {
        View view = getView(viewId);
        if(view != null){
            view.setTag(tag);
            view.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置View的显示状态
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolder setVisibility(int viewId,int visibility) {
        View view = getView(viewId);
        if(view != null){
            view.setVisibility(visibility);
        }
        return this;
    }

    /**
     * 为view设置点击事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId,View.OnClickListener listener) {
        View view = getView(viewId);
        if(view != null){
            view.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 为TextView设置颜色
     *
     * @param viewId
     * @param textColor
     * @return
     */
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        if(view != null){
            view.setTextColor(textColor);
        }
        return this;
    }
    /**
     * 为TextView设置背景
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = getView(viewId);
        if(view != null){
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    /**
     * 设置背景
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHolder setBackgroundResource(int viewId, int drawable) {
        View view = getView(viewId);
        if(view != null){
            view.setBackgroundResource(drawable);
        }
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        //TKImageLoad.getInstance().displayForDrawable(view,drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder display(int viewId, String url) {
        ImageView view = getView(viewId);
        ///TKImageLoad.getInstance().display(view,url);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder displayPhoto(int viewId, String url) {
        ImageView view = getView(viewId);
        //TKImageLoad.getInstance().displayPhoto(view,url);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder displayRelative(int viewId, String url) {
        return display(viewId, /*FakerParams.SERVER_URL +*/ url);
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder displayPhotoRelative(int viewId, String url) {
        return displayPhoto(viewId, /*FakerParams.SERVER_URL +*/ url);
    }

    public int getPosition() {
        return mPosition;
    }

}  