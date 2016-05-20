/*package com.shaadielephant.shaadielephant.fav_vendors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;


public class fav_vendoradapter extends BaseExpandableListAdapter implements networkresponse1,networkresponse4{

    private Context context;
    private ArrayList<customclassfav_vendor_group> groups;
    ArrayList<customclassfav_vendor_child> chList;
    ArrayList<String> ob_del;
    static ArrayList<customclassfav_vendor_group> objArraylist;
    SharedPreferences shared;
    String userID;
    static int a;
    int b;
    public fav_vendoradapter(Context context, ArrayList<customclassfav_vendor_group> groups) {
        this.context = context;
        this.groups = groups;
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


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.singlerowfav_vendor_child, null);
        }


        customclassfav_vendor_child child = (customclassfav_vendor_child) getChild(groupPosition, childPosition);
        TextView fullName  = (TextView) convertView.findViewById(R.id.fullName);
        TextView localityName  = (TextView) convertView.findViewById(R.id.localityName);
        TextView cityName  = (TextView) convertView.findViewById(R.id.cityName);
        TextView phone  = (TextView) convertView.findViewById(R.id.phone);

        fullName.setText(child.getFullName().toString());
        localityName.setText(child.getLocalityName().toString());
        cityName.setText(child.getCityName().toString());
        shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        ImageView imgdel = (ImageView)convertView.findViewById(R.id.img_del);
     //   imgdel.setTag(child.getNum());

        class MyTag
        {
            int item_num;
            int  item_pos;


            public MyTag()
            {
                item_num=0;
                item_pos=0;
            }

            public MyTag(int item_num,int item_pos)
            {
                this.item_num=item_num;
                this.item_pos=item_pos;
            }

        }
        MyTag myTag=new MyTag(child.getNum(),childPosition);
        imgdel.setTag(myTag);

        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  int a= (Integer)v.getTag();
                MyTag myTag=(MyTag)v.getTag();
                a=myTag.item_num;
                b=myTag.item_pos;
                open(a,b);


            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<customclassfav_vendor_child> chList = groups.get(groupPosition).getItems();
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
        customclassfav_vendor_group group = (customclassfav_vendor_group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.singlerowfav_vendor_group, null);
        }
        TextView categoryname = (TextView) convertView.findViewById(R.id.categoryname);
        categoryname.setText(group.getCategoryName());
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

    @Override
    public void getResponse1(String strinput) {
        fav_vendorjsonparser objfav_vendorjsonparser = new fav_vendorjsonparser();
        objArraylist =objfav_vendorjsonparser.parser(strinput);
        notifyDataSetChanged();

    }


    //delete vendor
    @Override
    public void getResponse4(String strinput) {
        fav_vendor_del_jsonparser obj_fav_vendor_del_jsonparser = new fav_vendor_del_jsonparser();
        ob_del = obj_fav_vendor_del_jsonparser.parser(strinput);
        String status_report = obj_fav_vendor_del_jsonparser.arrayList.get(0);
        Log.w("Value of b is ", b + " ");


        if(status_report=="1") {


        }

        asyntask1 objasyntask= new asyntask1(context);
        asyntask1.setListner(fav_vendoradapter.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorListCategoryGrouped","userID=" + userID};
        objasyntask.execute(strinputarray);
        Toast.makeText(context, "Vendor deleted" , Toast.LENGTH_SHORT).show();
    }

    // method to delete vendor
    public void open(int id , int pos){
        final int delete_id = id;
        final int position = pos;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                asyntask4 objasyntask4= new asyntask4(context);
                asyntask4.setListner(fav_vendoradapter.this);
                String[] strinputarray={"http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorRemove","num="+delete_id};
                objasyntask4.execute(strinputarray);
                chList.remove(position);

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}
*/