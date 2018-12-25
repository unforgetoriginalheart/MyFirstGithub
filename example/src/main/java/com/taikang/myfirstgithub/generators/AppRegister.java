package com.taikang.myfirstgithub.generators;

import com.taikang.latte_annotations.annotations.AppRegisterGenerator;
import com.taikang.latter_core.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.taikang.myfirstgithub",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
