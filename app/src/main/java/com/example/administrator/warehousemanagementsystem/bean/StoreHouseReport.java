package com.example.administrator.warehousemanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:
 **/
@SmartTable(name = "宁通仓库管理系统")
public class StoreHouseReport {
    @SmartColumn(id = 1, name = "仓库名")
    private String ckName;
    @SmartColumn(id = 2, name = "商品名")
    private String productName;
    @SmartColumn(id = 3, name = "数量")
    private int num;
    @SmartColumn(id = 4, name = "单位")
    private String goodsUnit;

    public StoreHouseReport(String ckName, String productName, int num, String goodsUnit) {
        this.ckName = ckName;
        this.productName = productName;
        this.num = num;
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getCkName() {
        return ckName;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
