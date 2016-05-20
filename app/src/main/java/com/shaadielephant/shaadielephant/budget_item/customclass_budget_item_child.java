package com.shaadielephant.shaadielephant.budget_item;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/18/2016.
 */
public class customclass_budget_item_child {
  //ItemList
    String itemlist_fullName,itemlist_vendorName,itemlist_vendorPhone,itemlist_vendorOtherRemarks,itemlist_status;
    int itemlist_num,itemlist_plannedCost,itemlist_actualAmount,itemlist_DiffAmount,itemlist_paidAmount,itemlist_pendingAmount,itemlist_vendorID;

    // VendorList
    String vendorlist_fullName;
    int vendorlist_num,vendorlist_vendorID;


    public String getItemlist_fullName() {
        return itemlist_fullName;
    }

    public void setItemlist_fullName(String itemlist_fullName) {
        this.itemlist_fullName = itemlist_fullName;
    }

    public String getItemlist_vendorName() {
        return itemlist_vendorName;
    }

    public void setItemlist_vendorName(String itemlist_vendorName) {
        this.itemlist_vendorName = itemlist_vendorName;
    }

    public String getItemlist_vendorPhone() {
        return itemlist_vendorPhone;
    }

    public void setItemlist_vendorPhone(String itemlist_vendorPhone) {
        this.itemlist_vendorPhone = itemlist_vendorPhone;
    }

    public String getItemlist_vendorOtherRemarks() {
        return itemlist_vendorOtherRemarks;
    }

    public void setItemlist_vendorOtherRemarks(String itemlist_vendorOtherRemarks) {
        this.itemlist_vendorOtherRemarks = itemlist_vendorOtherRemarks;
    }

    public String getItemlist_status() {
        return itemlist_status;
    }

    public void setItemlist_status(String itemlist_status) {
        this.itemlist_status = itemlist_status;
    }

    public int getItemlist_num() {
        return itemlist_num;
    }

    public void setItemlist_num(int itemlist_num) {
        this.itemlist_num = itemlist_num;
    }

    public int getItemlist_plannedCost() {
        return itemlist_plannedCost;
    }

    public void setItemlist_plannedCost(int itemlist_plannedCost) {
        this.itemlist_plannedCost = itemlist_plannedCost;
    }

    public int getItemlist_actualAmount() {
        return itemlist_actualAmount;
    }

    public void setItemlist_actualAmount(int itemlist_actualAmount) {
        this.itemlist_actualAmount = itemlist_actualAmount;
    }

    public int getItemlist_DiffAmount() {
        return itemlist_DiffAmount;
    }

    public void setItemlist_DiffAmount(int itemlist_DiffAmount) {
        this.itemlist_DiffAmount = itemlist_DiffAmount;
    }

    public int getItemlist_paidAmount() {
        return itemlist_paidAmount;
    }

    public void setItemlist_paidAmount(int itemlist_paidAmount) {
        this.itemlist_paidAmount = itemlist_paidAmount;
    }

    public int getItemlist_pendingAmount() {
        return itemlist_pendingAmount;
    }

    public void setItemlist_pendingAmount(int itemlist_pendingAmount) {
        this.itemlist_pendingAmount = itemlist_pendingAmount;
    }

    public int getItemlist_vendorID() {
        return itemlist_vendorID;
    }

    public void setItemlist_vendorID(int itemlist_vendorID) {
        this.itemlist_vendorID = itemlist_vendorID;
    }

    public String getVendorlist_fullName() {
        return vendorlist_fullName;
    }

    public void setVendorlist_fullName(String vendorlist_fullName) {
        this.vendorlist_fullName = vendorlist_fullName;
    }

    public int getVendorlist_num() {
        return vendorlist_num;
    }

    public void setVendorlist_num(int vendorlist_num) {
        this.vendorlist_num = vendorlist_num;
    }

    public int getVendorlist_vendorID() {
        return vendorlist_vendorID;
    }

    public void setVendorlist_vendorID(int vendorlist_vendorID) {
        this.vendorlist_vendorID = vendorlist_vendorID;
    }
}
