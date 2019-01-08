package com.taikang.latter_core.ui.recycler.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Time：2019/1/3
 * Author: gaonz
 * Description:
 */
public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    //使用泛型可以将错误暴露在编译期，也能提高效率
    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }
}
