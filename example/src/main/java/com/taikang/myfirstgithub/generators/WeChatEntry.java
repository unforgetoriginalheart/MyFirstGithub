package com.taikang.myfirstgithub.generators;

import com.taikang.latte_annotations.annotations.EntryGenerator;
import com.taikang.latter_core.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.taikang.myfirstgithub",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
