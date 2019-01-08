package com.taikang.latter_ec.main.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taikang.latter_core.ui.recycler.data.DataConverter;
import com.taikang.latter_core.ui.recycler.data.ItemType;
import com.taikang.latter_core.ui.recycler.data.MultipleFields;
import com.taikang.latter_core.ui.recycler.data.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Time：2019/1/4
 * Author: gaonz
 * Description:
 */
public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSONObject.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject index = dataArray.getJSONObject(i);
            final String imageUrl = index.getString("imageUrl");
            final String text = index.getString("text");
            final int spanSize = index.getInteger("spanSize");
            final int id = index.getInteger("goodsId");
            final JSONArray banners = index.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.IMAGE_TEXT;
            } else if (banners != null){
                type = ItemType.BANNER;
                //banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();
            ENTITY.add(entity);
        }
        return ENTITY;
    }
}
