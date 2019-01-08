package com.taikang.latter_core.ui.refresh;

/**
 * Time：2019/1/8
 * Author: gaonz
 * Description:列表分页的bean
 */
public final class PagingEntity {
    //当前是第几页
    private int mPageIndex = 0;
    //总数据条数
    private int mTotal = 0;
    //一页显示几条数据
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingEntity setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingEntity setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingEntity setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingEntity setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingEntity setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PagingEntity addIndex() {
        mPageIndex++;
        return this;
    }
}
