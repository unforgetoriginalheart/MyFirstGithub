package com.taikang.myfirstgithub.generators;


import com.taikang.latte_annotations.annotations.PayEntryGenerator;
import com.taikang.latter_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.taikang.myfirstgithub",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
