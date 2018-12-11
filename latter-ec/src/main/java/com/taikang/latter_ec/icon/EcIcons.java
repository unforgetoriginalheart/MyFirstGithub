package com.taikang.latter_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public enum  EcIcons implements Icon {
    chrsmas('\ue610');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("-", "_");
    }

    @Override
    public char character() {
        return this.character;
    }
}
