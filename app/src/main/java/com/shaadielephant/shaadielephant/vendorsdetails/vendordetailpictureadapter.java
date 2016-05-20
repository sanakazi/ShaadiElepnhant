package com.shaadielephant.shaadielephant.vendorsdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shaadielephant.shaadielephant.vendorsdetails.customclassvendordetailpictures;

import java.util.ArrayList;


public class vendordetailpictureadapter extends BaseAdapter {
    Context context;
    ArrayList<customclassvendordetailpictures> vendorarray;
    vendordetailpictureadapter(Context c, ArrayList<customclassvendordetailpictures> temparray)
    {
        this.context=c;
        this.vendorarray=temparray;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
