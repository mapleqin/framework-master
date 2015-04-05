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

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toaker.framework.R;
import com.toaker.framework.core.component.ActionBar;
import com.toaker.framework.core.utils.ScaleController;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/2 18:45
 */
public class ActionBarView extends CoreViewGroup {

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private int                             DEFAULT_TITLEBAR_ID    = 0x80251496;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private ActionBar.Mode                  mCurrentMode = ActionBar.Mode.MODE_DEFAULT;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private Activity                        mActivity;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private FrameLayout                     mGroupLayout;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private float                           defaultHeight;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private View                            mCustomView;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private LayoutParams                    mCustomLayoutParams;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private ArrayList<ActionBar.SubMenu>    mSubMenus = new ArrayList<ActionBar.SubMenu>();

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private ActionBar.Action                mLeftAction;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private ArrayList<ActionBar.Action>     mRightActions = new ArrayList<ActionBar.Action>();

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private View                            mTitleView;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private TextView                        mTextTitle;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private int                             mSubMenuItemShadowHeight = 10;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private Drawable                        mSubMenuItemShadow;

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private ActionBarView(Activity activity) {
        super(activity);
        if(activity == null){
            throw new IllegalArgumentException("The parameters of the illegal Activity is NULL");
        }
        this.mActivity = activity;
        this.defaultHeight = mActivity.getResources().getDimension(R.dimen.abc_action_bar_default_height);
        attach();
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    private void attach() {
        mGroupLayout = new FrameLayout(mActivity);
        mTextTitle   = new TextView(mActivity);
        addView(mGroupLayout);
        ViewGroup mDecorView = (ViewGroup) mActivity.getWindow().getDecorView();
        View      mContentView = null;
        if(mDecorView.getChildCount() > 0){
            mContentView = mDecorView.getChildAt(0);
            mDecorView.removeView(mContentView);
        }
        LinearLayout mLinearLayout = new LinearLayout(mActivity);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(this,LayoutParams.MATCH_PARENT, (int) defaultHeight);
        if(mContentView != null){
            mLinearLayout.addView(mContentView);
        }
        mDecorView.addView(mLinearLayout);
    }


    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            mGroupLayout.layout(l,t,r,b);
        }
    }

    /**
     * Decorator for framework-master
     *
     * @author Toaker [Toaker](ToakerQin@gmail.com)
     *         [Toaker](http://www.toaker.com)
     * @Description:
     * @Time Create by 2015/4/2 18:45
     */
    @Override
    public void requestLayout() {
        super.requestLayout();

    }

    /**
     * @Description: Set the title bar of TitleView;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     */
    public void setTitleView(View titleView){
        if(titleView != null){
            this.mTitleView = titleView;
            requestLayout();
        }
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
    public void show(){
        if(getVisibility() != VISIBLE){
            setVisibility(VISIBLE);
        }
    }

    /**
     * @Description: Hide title bar
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     */
    public void hide(){
        if(getVisibility() != GONE){
            setVisibility(GONE);
        }
    }


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
    public void setSubMenuItemShadowHeight(int height){
        if(this.mSubMenuItemShadowHeight != height){
            this.mSubMenuItemShadowHeight = height;
            // TODO Set the SubMenu list shadow;
        }
    }

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
    public void setSubMenuItemShadow(Drawable drawable){
        this.mSubMenuItemShadow = drawable;
        // TODO Set the SubMenu list shadow;
    }

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
    public void setSubMenuBackgroundColor(int color){
        // TODO Set the SubMenu list shadow;
    }

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
    public void setSubMenuBackgroundDrawable(Drawable drawable){
        // TODO Set the SubMenu list shadow;
    }

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
    public Drawable getBackgroundDrawable(){
        return this.getBackground();
    }

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
    public void setBackgroundColor(int color){
        this.setSubMenuBackgroundColor(color);
    }

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
    public void setBackgroundDrawable(Drawable drawable){
        this.setBackgroundDrawable(drawable);
    }

    /**
     * @Description: Set the Title bar background drawable;
     *
     * @author Toaker  [Toaker](ToakerQin@gmail.com)
     *                 [Toaker](http://www.toaker.com)
     *
     * @Time Create by 2015/4/1 16:40
     *
     * @param resId Want to title bar the background drawable resId!
     */
    public void setBackgroundResource(int resId){
        this.setBackgroundResource(resId);
    }

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
    public void setTitleTextSize(float size){
        if(ScaleController.getInstance() != null){
            size = ScaleController.getInstance().scaleTextSize(size);
        }
        if(this.mTextTitle != null){
            this.mTextTitle.setTextSize(size);
        }
    }

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
    public void setTitleTextColor(int color){
        if(this.mTextTitle != null){
            this.mTextTitle.setTextColor(color);
        }
    }

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
    public void setTitle(CharSequence title){
        if(this.mTextTitle != null){
            this.mTextTitle.setText(title);
        }
    }

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
    public CharSequence getTitle(){
        if(this.mTextTitle != null){
            return this.mTextTitle.getText();
        }
        return null;
    }

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
    public void addActionToLeft(ActionBar.Action action){
        if(action != null){
            if(this.mLeftAction != null){
                this.mRightActions.add(this.mLeftAction);
            }
            this.mLeftAction = action;
        }
    }

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
    public void addActionToRight(ActionBar.Action action){
        if(action != null){
            this.mRightActions.add(action);
        }
    }

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
    public ActionBar.SubMenu removeSubMenu(int index){
        return this.mSubMenus.remove(index);
    }

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
    public  void removeSubMenu(ActionBar.SubMenu subMenu){
        if(subMenu != null){
            this.mSubMenus.remove(subMenu);
        }
    }

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
    public void addSubMenu(List<ActionBar.SubMenu> subMenus){
        if(subMenus != null && subMenus.size() > 0){
            this.mSubMenus.addAll(subMenus);
        }
    }

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
    public void addSubMenu(ActionBar.SubMenu subMenu,int index){
        if(subMenu != null){
            this.mSubMenus.add(index,subMenu);
        }
    }

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
    public void addSubMenu(ActionBar.SubMenu subMenu){
        if(subMenu != null){
            this.mSubMenus.add(subMenu);
        }
    }

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
    public void setCustomView(View customView){
        this.setCustomView(customView,null);
    }

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
    public void setCustomView(View customView,ViewGroup.LayoutParams params){
        this.mCustomView = customView;
        this.mCustomLayoutParams = params;
        if(this.mCurrentMode == ActionBar.Mode.MODE_DEFAULT){
            requestLayout();
        }
    }

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
    public void setMode(ActionBar.Mode mode){
        this.mCurrentMode = mode;
        requestLayout();
    }


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
    public ActionBar.Mode getMode(){
        return this.mCurrentMode;
    }
}
