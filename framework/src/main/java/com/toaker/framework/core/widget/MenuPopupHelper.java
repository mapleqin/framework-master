/*******************************************************************************
 * Copyright 2015-2019 copyright of Soulwolf XiaoDaoW
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
package com.toaker.framework.core.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.toaker.framework.R;
import com.toaker.framework.core.utils.ScaleController;
import com.toaker.framework.core.view.ArrowBackgroundDrawable;
import com.toaker.framework.core.view.PopupWindowCompat;

/**
 * Decorator for XiaoDaoW
 * <p/>
 * author Soulwolf
 * <p/>
 * Create by 2015/5/19 14:21
 */
public class MenuPopupHelper implements PopupWindow.OnDismissListener {

    private static final String TAG = "MenuPopupHelper";

    static final int LAYOUT = R.layout.menu_popup_helper_layout;

    private Context mContext;

    private LayoutInflater mInflater;

    private PopupWindowCompat mPopup;

    private View mAnchorView;

    private ListView mListView;

    private int width;

    private OnMenuPopupWindowListener mListener;

    public MenuPopupHelper(Context context,int width) {
        this(context, null,width);
    }

    public MenuPopupHelper(Context context, View anchorView,int width) {
        mContext = context;
        this.width = width;
        if(ScaleController.getInstance() != null){
            this.width = ScaleController.getInstance().scaleWidth(this.width);
        }
        mInflater = LayoutInflater.from(context);
        mAnchorView = anchorView;
        initialize();
    }

    private void initialize() {
        View contentView = mInflater.inflate(LAYOUT,null);
        mListView = (ListView) contentView.findViewById(R.id.menu_popup_list);
        mPopup = new PopupWindowCompat(contentView,width,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        contentView.setPadding(0, (int) mPopup.getTriangleSize(), 0, 0);
        mPopup.setBackgroundDrawable(new ArrowBackgroundDrawable(mPopup.getTriangleSize()));
        mPopup.setOutsideTouchable(true);
        mPopup.setFocusable(true);
        mPopup.setOnDismissListener(this);
    }

    public void setOnMenuPopupWindowListener(OnMenuPopupWindowListener listener){
        this.mListener = listener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener){
        mListView.setOnItemClickListener(clickListener);
    }

    public void setAdapter(ListAdapter adapter){
        mListView.setAdapter(adapter);
    }

    /**
     * Returns the drawable that will be drawn between each item in the list.
     *
     * @return the current drawable drawn between list elements
     */
    public Drawable getDivider() {
        return mListView.getDivider();
    }

    /**
     * Sets the drawable that will be drawn between each item in the list. If the drawable does
     * not have an intrinsic height, you should also call {@link #setDividerHeight(int)}
     *
     * @param divider The drawable to use.
     */
    public void setDivider(Drawable divider) {
        mListView.setDivider(divider);
    }

    /**
     * @return Returns the height of the divider that will be drawn between each item in the list.
     */
    public int getDividerHeight() {
        return mListView.getDividerHeight();
    }

    /**
     * Sets the height of the divider that will be drawn between each item in the list. Calling
     * this will override the intrinsic height as set by {@link #setDivider(Drawable)}
     *
     * @param height The new height of the divider in pixels.
     */
    public void setDividerHeight(int height) {
        mListView.setDividerHeight(height);
    }

    /**
     * Enables or disables the drawing of the divider for header views.
     *
     * @param headerDividersEnabled True to draw the headers, false otherwise.
     *
     * @see #setFooterDividersEnabled(boolean)
     */
    public void setHeaderDividersEnabled(boolean headerDividersEnabled) {
        mListView.setHeaderDividersEnabled(headerDividersEnabled);
    }

    /**
     * Enables or disables the drawing of the divider for footer views.
     *
     * @param footerDividersEnabled True to draw the footers, false otherwise.
     *
     * @see #setHeaderDividersEnabled(boolean)
     */
    public void setFooterDividersEnabled(boolean footerDividersEnabled) {
        mListView.setFooterDividersEnabled(footerDividersEnabled);
    }

    public void setAnchorView(View anchor) {
        mAnchorView = anchor;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        View anchor = mAnchorView;
        if (anchor != null) {
            mPopup.setAnchorView(anchor);
            mPopup.show();
            if(mListener != null){
                mListener.onShowing(this);
            }
        } else {
            return false;
        }
        mPopup.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        return true;
    }

    public void setBackgroundDrawable(Drawable drawable){
        mPopup.setBackgroundDrawable(drawable);
        mPopup.update();
    }

    public void setBackgroundColor(int color,ArrowBackgroundDrawable.ArrowMode mode){
        ArrowBackgroundDrawable drawable = new ArrowBackgroundDrawable(color);
        drawable.setArrowMode(mode);
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundColor(int color){
        setBackgroundColor(color, ArrowBackgroundDrawable.ArrowMode.CENTER);
    }

    public void dismiss() {
        if (isShowing()) {
            mPopup.dismiss();
        }
    }

    public boolean isShowing() {
        return mPopup.isShowing();
    }

    @Override
    public void onDismiss() {
        if(mListener != null){
            mListener.onDismiss(this);
        }
    }

    public interface OnMenuPopupWindowListener{

        public void onShowing(MenuPopupHelper helper);

        public void onDismiss(MenuPopupHelper helper);
    }
}
