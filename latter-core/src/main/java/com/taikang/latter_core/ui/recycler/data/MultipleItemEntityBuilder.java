package com.taikang.latter_core.ui.recycler.data;

import java.util.LinkedHashMap;

/**
 * Time：2019/1/4
 * Author: gaonz
 * Description:MultipleItemEntity的建造者类
 */
public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        //先清空之前数据
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
