/*
package com.shaadielephant.shaadielephant.budget_item;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;
import java.util.List;



public class budget_item_adapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<customclass_budget_item_group> groups;
    ArrayList<String>ob_del_cat,ob_item_add,ob_del_item,ob_complete_status;
    ArrayList<customclass_budget_item_child> chList;

    private Dialog dialog ;
    int catID,userEventCatID,userEventCatPos;
    String userID;
    int statusItempos;
    EditText dialog_event_name ,days;
    TextView fullName,daysofcompletion,text_checklist;
    SharedPreferences shared;
    customclass_budget_item_group group;
    customclass_budget_item_child child;

    public budget_item_adapter(Context context, ArrayList<customclass_budget_item_group> groups) {
        this.context = context;
        this.groups = groups;
        shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final  int uei;
        final String statusItem;
        child = (customclass_budget_item_child) getChild(groupPosition, childPosition);

        group = (customclass_budget_item_group) getGroup(groupPosition);


        LayoutInflater infalInflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            if(childPosition==0) {
                convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_staticrow, null);
            }
            if(childPosition>0)
            {
                convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_child, null);

            }
        }


        if(childPosition==0) {

            convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_staticrow, null);
        }

        if(childPosition>0)
        {

            convertView = infalInflater.inflate(R.layout.singlerow_budget_item_child, null);
            fullName = (TextView) convertView.findViewById(R.id.fullName);
            fullName.setText(child.getItemlist_fullName());
            ImageView img_del = (ImageView)convertView.findViewById(R.id.img_del);


        }

        if(childPosition==getChildrenCount(groupPosition)-1)
        {
            convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_staticrow_plus, null);
            ImageView img_add_item = (ImageView)convertView.findViewById(R.id.img_add_item);
            // img_add_item.setTag(child.getNumItem());
            MyTagcat myTagitem=new MyTagcat(group.getCategoryID(),group.getUserCatID(),groupPosition);
            img_add_item.setTag(myTagitem);

        }



        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        chList = groups.get(groupPosition).getItems();
        return chList.size();


    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        group = (customclass_budget_item_group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.singlerowchecklist_item_group, null);
        }
        TextView categoryname = (TextView) convertView.findViewById(R.id.categoryname);
        categoryname.setText(group.getCatfullName());
        ImageView img_del_cat = (ImageView)convertView.findViewById(R.id.img_del_cat);

        MyTagcat myTagcat=new MyTagcat(group.getCategoryID(),group.getUserCatID(),groupPosition);
        img_del_cat.setTag(myTagcat);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    class MyTagcat
    {   int catID;
        int user_catID;
        int  cat_pos;


        public MyTagcat()
        {
            catID=0;
            user_catID=0;
            cat_pos=0;
        }

        public MyTagcat(int catID,int user_catID,int cat_pos)
        {
            this.catID=catID;
            this.user_catID=user_catID;
            this.cat_pos=cat_pos;
        }

    }
}
*/
