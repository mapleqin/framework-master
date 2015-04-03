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
package com.toaker.framework.core.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.toaker.framework.core.component.ActionBar;

import java.util.List;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/2 10:59
 */
public class ActionBarImpl extends ActionBar {

    protected ActionBarImpl(Activity mContext) {
        super(mContext);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public Action newAction() {
        return null;
    }

    @Override
    public SubMenu newSubMenu() {
        return null;
    }

    @Override
    public void setSubMenuItemShadowHeight(int height) {

    }

    @Override
    public void setSubMenuItemShadow(Drawable drawable) {

    }

    @Override
    public void setSubMenuBackgroundColor(int color) {

    }

    @Override
    public void setSubMenuBackgroundDrawable(Drawable drawable) {

    }

    @Override
    public Drawable getBackgroundDrawable() {
        return null;
    }

    @Override
    public void setBackgroundColor(int color) {

    }

    @Override
    public void setBackgroundDrawable(Drawable drawable) {

    }

    @Override
    public void setTitleTextSize(float size) {

    }

    @Override
    public void setTitleTextColor(int color) {

    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public CharSequence getTitle() {
        return null;
    }

    @Override
    public void addActionToLeft(Action action) {

    }

    @Override
    public void addActionToRight(Action action) {

    }

    @Override
    public SubMenu removeSubMenu(int index) {
        return null;
    }

    @Override
    public void removeSubMenu(SubMenu subMenu) {

    }

    @Override
    public void addSubMenu(List<SubMenu> subMenus) {

    }

    @Override
    public void addSubMenu(SubMenu subMenu, int index) {

    }

    @Override
    public void addSubMenu(SubMenu subMenu) {

    }

    @Override
    public void setCustomView(View customView) {

    }

    @Override
    public void setCustomView(View customView, ViewGroup.LayoutParams params) {

    }

    @Override
    public void setMode(Mode mode) {

    }

    @Override
    public Mode getMode() {
        return null;
    }
}
