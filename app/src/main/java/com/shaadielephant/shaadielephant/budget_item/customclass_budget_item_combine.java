package com.shaadielephant.shaadielephant.budget_item;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/19/2016.
 */
public class customclass_budget_item_combine {
    ArrayList<customclass_budget_item_group> budget_item_group;
    ArrayList<String> budget_details;

    public ArrayList<customclass_budget_item_group> getBudget_item_group() {
        return budget_item_group;
    }

    public void setBudget_item_group(ArrayList<customclass_budget_item_group> budget_item_group) {
        this.budget_item_group = budget_item_group;
    }

    public ArrayList<String> getBudget_details() {
        return budget_details;
    }

    public void setBudget_details(ArrayList<String> budget_details) {
        this.budget_details = budget_details;
    }
}
