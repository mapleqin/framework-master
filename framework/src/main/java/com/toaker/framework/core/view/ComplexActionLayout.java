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
package com.toaker.framework.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toaker.framework.R;
import com.toaker.framework.utils.ViewUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/4/29 9:23
 */
public class ComplexActionLayout extends FrameLayout {

    /** @hide */
    @IntDef({FLAG_ICON_LEFT, FLAG_ICON_RIGHT,FLAG_SOLE_ICON,FLAG_SOLE_TEXT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OptionMode {}

    public static final int FLAG_ICON_LEFT                           = 0x00000001;

    public static final int FLAG_ICON_RIGHT                          = 0x00000002;

    public static final int FLAG_SOLE_ICON                           = 0x00000004;

    public static final int FLAG_SOLE_TEXT                           = 0x00000008;

    int mTextColor                                                   = 0xFFFFFFFF;

    int mTextSize                                                    = 16;

    int mOptions                                                     = FLAG_ICON_LEFT;

    CharSequence mText                                               = "返回";

    int mIconResId                                                   = 0;

    private ImageView mIconView;

    private TextView  mTextView;

    LinearLayout mActionGroup;

    private View mUnreadView;

    public ComplexActionLayout(Context context) {
        this(context, null);
    }

    public ComplexActionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(attrs != null){
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ComplexActionLayout);
            mTextColor = t.getColor(R.styleable.ComplexActionLayout_textColor,mTextColor);
            mTextSize = t.getDimensionPixelSize(R.styleable.ComplexActionLayout_textSize, mTextSize);
            mText = t.getText(R.styleable.ComplexActionLayout_text);
            mOptions = t.getInt(R.styleable.ComplexActionLayout_options, mOptions);
            mIconResId = t.getResourceId(R.styleable.ComplexActionLayout_icon,mIconResId);
            t.recycle();
        }
        initialize();
    }

    private void initialize() {
        mActionGroup = new LinearLayout(getContext());
        mUnreadView = new CircleView(getContext());
        FrameLayout.LayoutParams layoutParams = new LayoutParams(ViewUtils.dip2px(getContext(),8),ViewUtils.dip2px(getContext(),8));
        layoutParams.gravity = Gravity.RIGHT;
        super.addView(mActionGroup, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        super.addView(mUnreadView,layoutParams);
        mUnreadView.setVisibility(GONE);
        mActionGroup.setOrientation(LinearLayout.HORIZONTAL);
        mActionGroup.setGravity(Gravity.CENTER);
        mIconView = new ImageView(getContext());
        mTextView = new TextView(getContext());
        mTextView.setLinkTextColor(com.toaker.framework.R.drawable.title_text_white_click_bg);
        mTextView.setTextColor(mTextColor);
        mTextView.setTextSize(mTextSize);
        if(mIconResId != 0){
            mIconView.setImageResource(mIconResId);
        }
        if(!TextUtils.isEmpty(mText)){
            mTextView.setText(mText);
        }
        reset();
    }

    private void reset() {
        mActionGroup.removeAllViews();
        switch (mOptions){
            case FLAG_ICON_LEFT:
                mIconView.setVisibility(VISIBLE);
                mTextView.setVisibility(VISIBLE);
                mActionGroup.addView(mIconView);
                mActionGroup.addView(mTextView);
                break;

            case FLAG_ICON_RIGHT:
                mIconView.setVisibility(VISIBLE);
                mTextView.setVisibility(VISIBLE);
                mActionGroup.addView(mTextView);
                mActionGroup.addView(mIconView);
                break;

            case FLAG_SOLE_ICON:
                mIconView.setVisibility(VISIBLE);
                mTextView.setVisibility(GONE);
                mActionGroup.addView(mIconView);
                break;

            case FLAG_SOLE_TEXT:
                mIconView.setVisibility(GONE);
                mTextView.setVisibility(VISIBLE);
                mActionGroup.addView(mTextView);
                break;
        }
    }

    /**
     *
     * @param options
     */
    public void setOptions(@OptionMode int options){
        this.mOptions = options;
        reset();
    }

    /**
     * Sets a Bitmap as the content of this ImageView.
     *
     * @param bm The bitmap to set
     */
    public void setIconImageBitmap(Bitmap bm) {
        mIconView.setImageBitmap(bm);
    }

    /**
     * Sets a drawable as the content of this ImageView.
     *
     * <p class="note">This does Bitmap reading and decoding on the UI
     * thread, which can cause a latency hiccup.  If that's a concern,
     * consider using {@link #setImageDrawable(android.graphics.drawable.Drawable)} or
     * {@link #setIconImageBitmap(android.graphics.Bitmap)} Bitmap(android.graphics.Bitmap)} and
     * {@link android.graphics.BitmapFactory} instead.</p>
     *
     * @param resId the resource identifier of the drawable
     *
     * @attr ref android.R.styleable#ImageView_src
     */
    public void setIconImageResource(int resId) {
        this.mIconResId = resId;
        mIconView.setImageResource(resId);
    }

    /**
     * Sets the content of this ImageView to the specified Uri.
     *
     * <p class="note">This does Bitmap reading and decoding on the UI
     * thread, which can cause a latency hiccup.  If that's a concern,
     * consider using {@link #setImageDrawable(android.graphics.drawable.Drawable)} or
     * {@link #setIconImageBitmap(android.graphics.Bitmap)} (android.graphics.Bitmap)} and
     * {@link android.graphics.BitmapFactory} instead.</p>
     *
     * @param uri The Uri of an image
     */
    public void setImageURI(Uri uri) {
        mIconView.setImageURI(uri);
    }

    /**
     * Sets a drawable as the content of this ImageView.
     *
     * @param drawable The drawable to set
     */
    public void setImageDrawable(Drawable drawable) {
        mIconView.setImageDrawable(drawable);
    }

    /**
     * Set the default text size to the given value, interpreted as "scaled
     * pixel" units.  This size is adjusted based on the current density and
     * user font size preference.
     *
     * @param size The scaled pixel size.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    /**
     * Sets the text color for all the states (normal, selected,
     * focused) to be this color.
     *
     * @attr ref android.R.styleable#TextView_textColor
     */
    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }

    /**
     * Sets the string value of the TextView. TextView <em>does not</em> accept
     * HTML-like formatting, which you can do with text strings in XML resource files.
     * To style your strings, attach android.text.style.* objects to a
     * {@link android.text.SpannableString SpannableString}, or see the
     * <a href="{@docRoot}guide/topics/resources/available-resources.html#stringresources">
     * Available Resource Types</a> documentation for an example of setting
     * formatted text in the XML resource file.
     *
     * @attr ref android.R.styleable#TextView_text
     */
    public final void setText(CharSequence text) {
        this.mText = text;
        mTextView.setText(text);
    }

    public final void setText(int resid) {
        mTextView.setText(resid);
    }

    public void show(){
        if(getVisibility() != VISIBLE){
            setVisibility(VISIBLE);
        }
    }

    public void hide(){
        if(getVisibility() != GONE){
            setVisibility(GONE);
        }
    }

    public void displayIcon(boolean display){
        if(display){
            mIconView.setVisibility(VISIBLE);
        }else {
            mIconView.setVisibility(GONE);
        }
    }

    public void displayText(boolean display){
        if(display){
            mTextView.setVisibility(VISIBLE);
        }else {
            mTextView.setVisibility(GONE);
        }
    }

    public ImageView getIconView() {
        return mIconView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void setUnread(boolean unread){
        if(unread){
            mUnreadView.setVisibility(VISIBLE);
        }else {
            mUnreadView.setVisibility(GONE);
        }
    }
}
