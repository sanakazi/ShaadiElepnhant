package com.shaadielephant.shaadielephant.checklist_item;

/**
 * Created by Callndata on 2/4/2016.
 */
public class customclass_checklist_item_child {
    int numItem;


    int daysCompleteBeforeEvents;
    String fullNameItem;
    String statusItem;
    String dateofCompletion;

    public String getDateofCompletion() {
        return dateofCompletion;
    }

    public void setDateofCompletion(String dateofCompletion) {
        this.dateofCompletion = dateofCompletion;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public int getDaysCompleteBeforeEvents() {
        return daysCompleteBeforeEvents;
    }

    public void setDaysCompleteBeforeEvents(int daysCompleteBeforeEvents) {
        this.daysCompleteBeforeEvents = daysCompleteBeforeEvents;
    }


    public String getFullNameItem() {
        return fullNameItem;
    }

    public void setFullNameItem(String fullNameItem) {
        this.fullNameItem = fullNameItem;
    }

    public String getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(String statusItem) {
        this.statusItem = statusItem;
    }
}
