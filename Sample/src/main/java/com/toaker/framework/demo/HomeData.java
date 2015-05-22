package com.toaker.framework.demo;

import com.android.volley.toolbox.ResponseWrapper;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel on 2014/12/17.
 */
public class HomeData implements ResponseWrapper{

    /**
     * 当前的页面数
     */
    @SerializedName("page")
    public int page_num;

    /**
     * 总页数
     */
    @SerializedName("total_page")
    public int total_page;
    /**
     * 总页数
     */
    @SerializedName("page_count")
    public int page_count;

    /**
     * 每页的数量
     */
    @SerializedName("page_size")
    public int pager_size;

    @SerializedName("status")
    public int status;

    public List<ActListItem> data;

    /**
     * 活动列表的条目
     */
    public static class ActListItem {

        @SerializedName("id")
        public String act_id;
        @SerializedName("title")
        public String title;
        @SerializedName("tag1")
        public String label1;
        @SerializedName("tag2")
        public String label2;
        @SerializedName("tag3")
        public String label3;

        /**
         * 是否是内部活动  1 为内部活动  0 为非内部活动
         */
        @SerializedName("is_public")
        public int is_internal;
        @SerializedName("thumb")
        public String pic;
        @SerializedName("hot")
        public int hot;


        /**
         * 活动的状态,1代表未开始 2代表进行中
         */
        @SerializedName("is_status")
        public  int status;


        public ActListItem() {
        }
    }
}
