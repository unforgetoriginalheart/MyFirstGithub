package com.taikang.latter_core.delegates;

/**
 * Time：2018/12/9
 * Author: gaonz
 * Description:
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
