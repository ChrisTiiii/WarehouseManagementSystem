package com.example.administrator.warehousemanagementsystem.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:
 **/
@SmartTable(name = "宁通仓库管理系统")
public class Report {
    @SmartColumn(id = 1, name = "仓库名")
    private String ckName;
    @SmartColumn(id = 2, name = "商品名")
    private String productName;
    @SmartColumn(id = 3, name = "数量")
    private int num;

    public Report(String ckName, String productName, int num) {
        this.ckName = ckName;
        this.productName = productName;
        this.num = num;
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
