package com.shaadielephant.shaadielephant.fav_vendors;

import com.shaadielephant.shaadielephant.fav_vendors.customclassfav_vendor_child;

import java.util.ArrayList;

/**
 * Created by Callndata on 1/28/2016.
 */
public class customclassfav_vendor_group {
    int categoryID;
    String CategoryName,msg;
    private ArrayList<customclassfav_vendor_child> Items;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public ArrayList<customclassfav_vendor_child> getItems() {
        return Items;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setItems(ArrayList<customclassfav_vendor_child> items) {
        Items = items;
    }
}
