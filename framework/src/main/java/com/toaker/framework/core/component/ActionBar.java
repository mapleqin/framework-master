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

import android.view.View;
import android.view.ViewGroup;

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
