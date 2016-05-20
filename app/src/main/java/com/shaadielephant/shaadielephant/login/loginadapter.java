package com.shaadielephant.shaadielephant.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;


public class loginadapter extends BaseAdapter {
    Context context;
ArrayList<customclasslogin> loginarray;
    loginadapter(Context context,ArrayList<customclasslogin> loginarray)
    {
        this.context=context;
        this.loginarray= loginarray;
    }
    @Override
    public int getCount() {
        return loginarray.size();
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
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.singlerowblog, null);
        return null;
    }
}
