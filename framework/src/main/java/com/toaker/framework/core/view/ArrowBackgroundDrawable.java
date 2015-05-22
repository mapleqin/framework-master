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
package com.toaker.framework.core.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Decorator for XiaoDaoW
 * <p/>
 * author Soulwolf
 * <p/>
 * Create by 2015/5/19 16:31
 */
public class ArrowBackgroundDrawable extends Drawable {

    private static final float LEFT_SCALE           = 0.2f;

    private static final float CENTER_SCALE         = 0.5f;

    private static final float RIGHT_SCALE          = 0.8f;

    private int mBackgroundColor = 0xE53C3C3C;

    private Paint mPaint = new Paint();

    private ArrowMode mArrowMode = ArrowMode.CENTER;

    private float mRadius        = 8.0f;

    private float mTriangleSize = 20.0f;

    private Path mPath = new Path();

    public ArrowBackgroundDrawable(int color,float triangleSize){
        if(color != 0){
            this.mBackgroundColor = color;
        }
        if(triangleSize != 0){
            mTriangleSize = triangleSize;
        }
        this.mPaint.setColor(mBackgroundColor);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
    }

    public ArrowBackgroundDrawable(float triangleSize){
        this(0,triangleSize);
    }

    public ArrowBackgroundDrawable(int color){
        this(color,0);
    }

    public ArrowBackgroundDrawable(){
        this(0,0);
    }

    @Override
    public void draw(Canvas canvas) {
        int count = canvas.save();
        float centerX = getIntrinsicWidth() * CENTER_SCALE;
        if(mArrowMode == ArrowMode.LEFT){
            centerX = getIntrinsicWidth() * LEFT_SCALE;
        }else if(mArrowMode == ArrowMode.RIGHT){
            centerX = getIntrinsicWidth() * RIGHT_SCALE;
        }
        canvas.drawPath(getPath(centerX),mPaint);
        canvas.drawRoundRect(new RectF(getBounds().left,getBounds().top + mTriangleSize,
                getBounds().right,getBounds().bottom),mRadius,mRadius,mPaint);
        canvas.restoreToCount(count);
    }

    private Path getPath(float cX){
        mPath.reset();
        mPath.moveTo(cX, mTriangleSize * LEFT_SCALE);
        mPath.lineTo(cX + mTriangleSize * RIGHT_SCALE, mTriangleSize);
        mPath.lineTo(cX - mTriangleSize * RIGHT_SCALE, mTriangleSize);
        mPath.lineTo(cX, mTriangleSize * LEFT_SCALE);
        mPath.close();
        return mPath;
    }

    public void setColor(int color){
        this.mBackgroundColor = color;
        this.mPaint.setColor(mBackgroundColor);
        invalidateSelf();
    }

    public void setArrowMode(ArrowMode mode){
        this.mArrowMode = mode;
        invalidateSelf();
    }

    @Override
    public int getIntrinsicWidth() {
        return getBounds().width();
    }

    @Override
    public int getIntrinsicHeight() {
        return getBounds().height();
    }

    @Override
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public enum ArrowMode{
        LEFT,
        CENTER,
        RIGHT
    }
}
