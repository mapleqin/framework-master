package com.toaker.framework.demo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel on 2014/12/17.
 */
public class HomeData {

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

        @Override
        public String toString() {
            return "ActListItem{" +
                    "act_id='" + act_id + '\'' +
                    ", title='" + title + '\'' +
                    ", label1='" + label1 + '\'' +
                    ", label2='" + label2 + '\'' +
                    ", label3='" + label3 + '\'' +
                    ", is_internal=" + is_internal +
                    ", pic='" + pic + '\'' +
                    ", hot=" + hot +
                    ", status=" + status +
                    '}';
        }
    }
}
