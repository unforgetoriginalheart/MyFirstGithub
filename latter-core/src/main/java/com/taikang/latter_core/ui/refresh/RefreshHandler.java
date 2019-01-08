package com.taikang.latter_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.taikang.latter_core.app.Latte;
import com.taikang.latter_core.net.RestClient;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.ui.recycler.adapter.MultipleRecyclerAdapter;
import com.taikang.latter_core.ui.recycler.data.DataConverter;

/**
 * Time：2019/1/3
 * Author: gaonz
 * Description: 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingEntity ENTITY;
    private final RecyclerView RECYCLER;
    private final DataConverter CONVERTER;
    private MultipleRecyclerAdapter mAdapter = null;

    private RefreshHandler(SwipeRefreshLayout refresh_layout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingEntity entity) {

        this.REFRESH_LAYOUT = refresh_layout;
        this.RECYCLER = recyclerView;
        this.CONVERTER = converter;
        this.ENTITY = entity;

        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refresh_layout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(refresh_layout, recyclerView, converter, new PagingEntity());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 3000);
    }

    public void firstPage(String url) {
        ENTITY.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSONObject.parseObject(response);
                        ENTITY.setTotal(object.getInteger("total"))
                                .setPageIndex(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLER);
                        RECYCLER.setAdapter(mAdapter);
                        ENTITY.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        //refreshLayout的加载更多方法
    }
}
