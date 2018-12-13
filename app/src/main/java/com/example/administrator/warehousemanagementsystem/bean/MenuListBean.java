package com.example.administrator.warehousemanagementsystem.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description: menuList bean
 **/
public class MenuListBean implements Serializable {

    private Map<String, Object> map;
    private boolean isSelect;

    public MenuListBean(Map<String, Object> map, boolean isSelect) {
        this.map = map;
        this.isSelect = isSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
