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
public class ActionBar {


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

       MODE_SUB_MENU,

       MODE_CUSTOM;

       public static Mode format(String name){
           try {
               return Mode.valueOf(name);
           }catch (Exception e){
               return Mode.MODE_DEFAULT;
           }
       }
   }
}
