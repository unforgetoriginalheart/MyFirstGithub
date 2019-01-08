package com.taikang.latter_core.ui.recycler.data;

import java.util.ArrayList;

/**
 * Time：2019/1/3
 * Author: gaonz
 * Description:数据转换约束
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITY = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
