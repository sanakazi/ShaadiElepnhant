package com.shaadielephant.shaadielephant.budget_item;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/18/2016.
 */
public class customclass_budget_item_group {

    int categoryID,userCatID,CatplannedCost,CatactualAmount,CatDiffAmount,CatpaidAmount,CatpendingAmount;
    String CatfullName;
    private ArrayList<customclass_budget_item_child> Items;


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUserCatID() {
        return userCatID;
    }

    public void setUserCatID(int userCatID) {
        this.userCatID = userCatID;
    }

    public int getCatplannedCost() {
        return CatplannedCost;
    }

    public void setCatplannedCost(int catplannedCost) {
        CatplannedCost = catplannedCost;
    }

    public int getCatactualAmount() {
        return CatactualAmount;
    }

    public void setCatactualAmount(int catactualAmount) {
        CatactualAmount = catactualAmount;
    }

    public int getCatDiffAmount() {
        return CatDiffAmount;
    }

    public void setCatDiffAmount(int catDiffAmount) {
        CatDiffAmount = catDiffAmount;
    }

    public int getCatpaidAmount() {
        return CatpaidAmount;
    }

    public void setCatpaidAmount(int catpaidAmount) {
        CatpaidAmount = catpaidAmount;
    }

    public int getCatpendingAmount() {
        return CatpendingAmount;
    }

    public void setCatpendingAmount(int catpendingAmount) {
        CatpendingAmount = catpendingAmount;
    }


    public String getCatfullName() {
        return CatfullName;
    }

    public void setCatfullName(String catfullName) {
        CatfullName = catfullName;
    }


    public ArrayList<customclass_budget_item_child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<customclass_budget_item_child> items) {
        Items = items;
    }
}
