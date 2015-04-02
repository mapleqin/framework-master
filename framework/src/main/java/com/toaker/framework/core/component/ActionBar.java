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
package com.toaker.framework.core.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.toaker.framework.core.view.ActionView;
import com.toaker.framework.core.view.SubMenuView;
import com.toaker.framework.core.widget.PressSelector;

import java.util.List;

/**
 * Decorator for framework-master
 *
 * @author Toaker (ToakerQin@gmail.com)
 *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
 *
 * @Description: The function of the title bar base class
 *
 * @Time Create by 2015/3/31 10:59
 */
public abstract class ActionBar {

    protected Activity mContext;

    protected ActionBar(Activity mContext){
        this.mContext = mContext;
    }

    /**
     * @Description: Show title bar
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     */
    public abstract void show();

    /**
     * @Description: Hide title bar
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     */
    public abstract void hide();

    /**
     * @Description: Create a title bar Action Item
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @return Return Created Action Item
     */
    public abstract Action newAction();

    /**
     * @Description: Create a title bar SubMenu Item
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @return Return Created SubMenu Item
     */
    public abstract SubMenu newSubMenu();

    /**
     * @Description: Setup SubMenu Item shadow line height
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param height Want set to SubMenu Item shadow line the drawable height
     */
    public abstract void setSubMenuItemShadowHeight(int height);

    /**
     * @Description: Setup SubMenu Item shadow line
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param drawable Want set to SubMenu Item shadow line the drawable
     */
    public abstract void setSubMenuItemShadow(Drawable drawable);

    /**
     * @Description: Set the Title bar of SubMenu  background color;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param color Want to title bar of SubMenu  the background color!
     */
    public abstract void setSubMenuBackgroundColor(int color);

    /**
     * @Description: Set the Title bar of SubMenu  background drawable;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param drawable Want to title bar of SubMenu the background drawable!
     */
    public abstract void setSubMenuBackgroundDrawable(Drawable drawable);

    /**
     * @Description: Get the Title bar background drawable;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @return title bar the background drawable
     */
    public abstract Drawable getBackgroundDrawable();

    /**
     * @Description: Set the Title bar background color;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param color Want to title bar the background color!
     */
    public abstract void setBackgroundColor(int color);

    /**
     * @Description: Set the Title bar background drawable;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param drawable Want to title bar the background drawable!
     */
    public abstract void setBackgroundDrawable(Drawable drawable);

    /**
     * @Description: Set the Title of the title bar font size;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param size size
     */
    public abstract void setTitleTextSize(float size);

    /**
     * @Description: Set the Title of the title bar font color;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param color color
     */
    public abstract void setTitleTextColor(int color);

    /**
     * @Description: Set the Title to title bar;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param title title
     */
    public abstract void setTitle(CharSequence title);

    /**
     * @Description: Get the Title by title bar;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @return Title!
     */
    public abstract CharSequence getTitle();

    /**
     * @Description: Add a action button to title bar left
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param action Want add to the title bar {@link com.toaker.framework.core.component.ActionBar.Action}
     */
    public abstract void addActionToLeft(Action action);

    /**
     * @Description: Add a action button to title bar right
     *               Most the right side of the title bar can only add two {@link com.toaker.framework.core.component.ActionBar.Action}
     *               if you add more than two {@link com.toaker.framework.core.component.ActionBar.Action} is automatically added
     *               to the SubMenu list;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param action Want add to the title bar {@link com.toaker.framework.core.component.ActionBar.Action}
     */
    public abstract void addActionToRight(Action action);

    /**
     * * @Description: Delete the specified location from SubMenu list SubMenu;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param index    The location of SubMenu list to delete;
     * @return         Returns the deleted SubMenu
     */
    public abstract SubMenu removeSubMenu(int index);

    /**
     * * @Description: Delete the specified from SubMenu list SubMenu;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param subMenu  Want to delete SubMenu;
     */
    public abstract  void removeSubMenu(SubMenu subMenu);

    /**
     * * @Description: Add a SubMenu to title bar of SubMenu list,
     *               Pay attention to ,only in the display mode for
     *               {@link com.toaker.framework.core.component.ActionBar.Mode} MODE_SUB_MENU
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param subMenus {@link com.toaker.framework.core.component.ActionBar.SubMenu}
     *                 Add multiple SubMenu item;
     */
    public abstract void addSubMenu(List<SubMenu> subMenus);

    /**
     * * @Description: Add a SubMenu to title bar of SubMenu list,
     *               Pay attention to ,only in the display mode for
     *               {@link com.toaker.framework.core.component.ActionBar.Mode} MODE_SUB_MENU
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param index   Add SubMenu item in the SubMenu list of index;
     * @param subMenu {@link com.toaker.framework.core.component.ActionBar.SubMenu} SubMenu item;
     */
    public abstract void addSubMenu(SubMenu subMenu,int index);

    /**
     * * @Description: Add a SubMenu to title bar of SubMenu list,
     *               Pay attention to ,only in the display mode for
     *               {@link com.toaker.framework.core.component.ActionBar.Mode} MODE_SUB_MENU
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param subMenu {@link com.toaker.framework.core.component.ActionBar.SubMenu} SubMenu item;
     */
    public abstract void addSubMenu(SubMenu subMenu);

    /**
     * @Description: Set a custom title bar shows view,
     *               Pay attention to ,only in the display mode for
     *               {@link com.toaker.framework.core.component.ActionBar.Mode} MODE_CUSTOM
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param customView A custom title bar view
     */
    public abstract void setCustomView(View customView);

    /**
     * @Description: Set a custom title bar shows view,
     *               Pay attention to ,only in the display mode for
     *               {@link com.toaker.framework.core.component.ActionBar.Mode} MODE_CUSTOM
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param customView A custom title bar view
     * @param params     A custom title bar view of LayoutParams
     */
    public abstract void setCustomView(View customView,ViewGroup.LayoutParams params);

    /**
     * @Description: Set the title bar display mode
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @param mode  Mode Enum value
     */
    public abstract void setMode(Mode mode);


    /**
     * @Description: Get the title bar display mode
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/3/31 10:59
     *
     * @return Return current title bar display mode
     */
    public abstract Mode getMode();

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *                [Toaker](http://www.toaker.com)
     *
     * @Description: This is the sub menu item in the title bar,you call {@link com.toaker.framework.core.component.ActionBar}
     *               addSubMenu to the title bar of sub menu list
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static class SubMenu{

        private SubMenuView mSubMenuView;

        private SubMenu(Context context){
            this.mSubMenuView = new SubMenuView(context);
        }

        protected SubMenu create(Context context){
            return new SubMenu(context);
        }

        public SubMenu setSort(int desc){
            this.mSubMenuView.setSort(desc);
            return this;
        }

        public SubMenu setTextSize(float size){
            this.mSubMenuView.setTextSize(size);
            return this;
        }

        public SubMenu setTextColor(int color){
            this.mSubMenuView.setTextColor(color);
            return this;
        }

        public SubMenu setText(CharSequence text){
            this.mSubMenuView.setText(text);
            return this;
        }

        public SubMenu setText(int resId){
            this.mSubMenuView.setText(resId);
            return this;
        }

        public SubMenu setIcon(int resId){
            this.mSubMenuView.setIcon(resId);
            return this;
        }

        public SubMenu setIcon(Drawable drawable){
            this.mSubMenuView.setIcon(drawable);
            return this;
        }

        public PressSelector getPressSelector() {
            return mSubMenuView.getPressSelector();
        }

        public SubMenu setPressSelector(PressSelector mPressSelector) {
            this.mSubMenuView.setPressSelector(mPressSelector);
            return this;
        }

        public SubMenu setSubMenuSize(int width,int height){
            this.mSubMenuView.setSubMenuSize(width,height);
            return this;
        }

        public SubMenu setSubMenuClickListener(ActionBar.SubMenuClickListener listener){
            this.mSubMenuView.setSubMenuClickListener(listener);
            return this;
        }

        public SubMenu setSubMenuLongClickListener(ActionBar.SubMenuLongClickListener listener){
            this.mSubMenuView.setSubMenuLongClickListener(listener);
            return this;
        }

        public SubMenu setPadding(int left, int top, int right, int bottom) {
            this.mSubMenuView.setPadding(left,top,right,bottom);
            return this;
        }

        public SubMenu setSpacing(float spacing){
            this.mSubMenuView.setSpacing(spacing);
            return this;
        }

        public SubMenuView getSubMenuView(){
            return this.mSubMenuView;
        }
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker (ToakerQin@gmail.com)
     *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
     *
     * @Description: The Title bar Action click listener
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static interface SubMenuClickListener extends View.OnClickListener{

    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker (ToakerQin@gmail.com)
     *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
     *
     * @Description: The Title bar Action long click listener
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static interface SubMenuLongClickListener extends View.OnLongClickListener{

    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *                [Toaker](http://www.toaker.com)
     *
     * @Description: This is a can add to the title bar of the button
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static class Action{

        private ActionView mActionView;

        private Action(Context context){
            this.mActionView = new ActionView(context);
        }

        public Action create(Context context){
            return new Action(context);
        }

        public Action setTextSize(float size){
            this.mActionView.setTextSize(size);
            return this;
        }

        public Action setId(int id){
            this.mActionView.setId(id);
            return this;
        }

        public int getId(){
            return this.mActionView.getId();
        }

        public Action setTextColor(int color){
            this.mActionView.setTextColor(color);
            return this;
        }

        public Action setText(CharSequence text){
            this.mActionView.setText(text);
            return this;
        }

        public Action setText(int resId){
            this.mActionView.setText(resId);
            return this;
        }

        public Action setIcon(int resId){
            this.mActionView.setIcon(resId);
            return this;
        }

        public Action setIcon(Drawable drawable){
            this.mActionView.setIcon(drawable);
            return this;
        }

        public PressSelector getPressSelector() {
            return mActionView.getPressSelector();
        }

        public Action setPressSelector(PressSelector mPressSelector) {
            this.mActionView.setPressSelector(mPressSelector);
            return this;
        }

        public Action setActionSize(int width,int height){
            this.mActionView.setActionSize(width,height);
            return this;
        }

        public Action setActionClickListener(ActionBar.ActionClickListener listener){
            this.mActionView.setActionClickListener(listener);
            return this;
        }

        public Action setActionLongClickListener(ActionBar.ActionLongClickListener listener){
            this.mActionView.setActionLongClickListener(listener);
            return this;
        }

        public ActionView getActionView(){
            return this.mActionView;
        }

        public Action setPadding(int left, int top, int right, int bottom) {
            this.mActionView.setPadding(left,top,right,bottom);
            return this;
        }

        public Action show(){
            this.mActionView.setVisibility(View.VISIBLE);
            return this;
        }

        public Action hide(){
            this.mActionView.setVisibility(View.GONE);
            return this;
        }
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker (ToakerQin@gmail.com)
     *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
     *
     * @Description: The Title bar Action click listener
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static interface ActionClickListener extends View.OnClickListener{

    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker (ToakerQin@gmail.com)
     *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
     *
     * @Description: The Title bar Action long click listener
     *
     * @Time Create by 2015/3/31 10:59
     */
    public static interface ActionLongClickListener extends View.OnLongClickListener{

    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker (ToakerQin@gmail.com)
     *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
     *
     * @Description: The Title bar of display mode
     *
     * @Time Create by 2015/3/31 10:59
     */
   public enum Mode{

        /**
         * The default display mode
         * contains <tt>Center Title</tt><br/><tt>Left go back<tt/>
         *
         * @author Toaker (ToakerQin@gmail.com)
         *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
         */
       MODE_DEFAULT,

        /**
         * The PopupWindow display mode
         * contains <tt>Center Title</tt><br/><tt>Left go back<tt/><tt>Sub Menu List</tt>
         *
         * @author Toaker (ToakerQin@gmail.com)
         *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
         */
       MODE_SUB_MENU,

        /**
         * The Custom display mode
         * contains <tt>Custom Views</tt>
         *
         * @author Toaker (ToakerQin@gmail.com)
         *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
         */
       MODE_CUSTOM;

        /**
         * The String format for Enum Mode
         *
         * @author Toaker (ToakerQin@gmail.com)
         *         (<a href='http://www.toaker.com'>http://www.toaker.com</a>)
         */
       public static Mode format(String name){
           try {
               return Mode.valueOf(name);
           }catch (Exception e){
               return Mode.MODE_DEFAULT;
           }
       }
   }
}
