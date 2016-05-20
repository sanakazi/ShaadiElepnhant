package com.shaadielephant.shaadielephant.vendors;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/10/2016.
 */
public class customclass_group_city {
    int numCity;
    String fullNameCity;
    private ArrayList<customclass_child_locality> Items;


    public int getNumCity() {
        return numCity;
    }

    public void setNumCity(int numCity) {
        this.numCity = numCity;
    }

    public String getFullNameCity() {
        return fullNameCity;
    }

    public void setFullNameCity(String fullNameCity) {
        this.fullNameCity = fullNameCity;
    }

    public ArrayList<customclass_child_locality> getItems() {
        return Items;
    }

    public void setItems(ArrayList<customclass_child_locality> items) {
        Items = items;
    }
}
