package com.shaadielephant.shaadielephant.checklist_item;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/4/2016.
 */
public class customclass_checklist_item_group {
    int numChecklist,categoryID,userEventCatID;
    String fullNameChecklist,fullNameCategory,marriageDate;
    private ArrayList<customclass_checklist_item_child> Items;

    public int getNumChecklist() {
        return numChecklist;
    }

    public void setNumChecklist(int numChecklist) {
        this.numChecklist = numChecklist;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUserEventCatID() {
        return userEventCatID;
    }

    public void setUserEventCatID(int userEventCatID) {
        this.userEventCatID = userEventCatID;
    }

    public String getFullNameChecklist() {
        return fullNameChecklist;
    }

    public void setFullNameChecklist(String fullNameChecklist) {
        this.fullNameChecklist = fullNameChecklist;
    }

    public String getFullNameCategory() {
        return fullNameCategory;
    }

    public void setFullNameCategory(String fullNameCategory) {
        this.fullNameCategory = fullNameCategory;
    }

    public String getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }

    public ArrayList<customclass_checklist_item_child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<customclass_checklist_item_child> items) {
        Items = items;
    }
}
