package com.toaker.framework.core.inter;

import com.toaker.framework.core.view.AbsFrameworkListView;

public interface LoadMoreHandler {
        /**
         * 加载更多
         * @param view
         */
        void onLoadMore(AbsFrameworkListView view);
    }