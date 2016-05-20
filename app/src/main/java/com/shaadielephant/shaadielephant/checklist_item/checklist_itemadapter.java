/*package com.shaadielephant.shaadielephant.checklist_item;

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


public class checklist_itemadapter extends BaseExpandableListAdapter implements networkresponse4,networkresponse5 {

    private Context context;
    private ArrayList<customclass_checklist_item_group> groups;
    ArrayList<String>ob_del_cat,ob_item_add;
    ArrayList<customclass_checklist_item_child> chList;

    private static Dialog dialog ;
    static int catID,userEventCatID,userEventCatPos;
    static String userID;
    static EditText dialog_event_name ,days;
    static TextView fullName,daysofcompletion;

    SharedPreferences shared;
    customclass_checklist_item_group group;
    customclass_checklist_item_child child;

    public checklist_itemadapter(Context context, ArrayList<customclass_checklist_item_group> groups) {
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
        child = (customclass_checklist_item_child) getChild(groupPosition, childPosition);

        group = (customclass_checklist_item_group) getGroup(groupPosition);


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

             convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_child, null);
             fullName = (TextView) convertView.findViewById(R.id.fullName);
            fullName.setText(child.getFullNameItem());
             daysofcompletion = (TextView) convertView.findViewById(R.id.days);
             daysofcompletion.setText(child.getDateofCompletion());
             Spinner spinner_checklist= (Spinner)convertView.findViewById(R.id.spinner_checklist);
             List<String> categories = new ArrayList<String>();
             categories.add("Pending");
             categories.add("Completed");

             // Creating adapter for spinner
             ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);

             // Drop down layout style - list view with radio button
             dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             // attaching data adapter to spinner
             spinner_checklist.setAdapter(dataAdapter);

         }

        if(childPosition==getChildrenCount(groupPosition)-1)
        {
            convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_staticrow_plus, null);
            ImageView img_add_item = (ImageView)convertView.findViewById(R.id.img_add_item);
           // img_add_item.setTag(child.getNumItem());
            MyTagcat myTagitem=new MyTagcat(group.getCategoryID(),group.getUserEventCatID(),groupPosition);
            img_add_item.setTag(myTagitem);

            img_add_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyTagcat myTagitem=(MyTagcat)v.getTag();
                    catID=myTagitem.catID;
                    userEventCatID=myTagitem.user_catID;
                    userEventCatPos=myTagitem.cat_pos;
                    showDialogToAddChecklist_item();
                }
            });
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
         group = (customclass_checklist_item_group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.singlerowchecklist_item_group, null);
        }
        TextView categoryname = (TextView) convertView.findViewById(R.id.categoryname);
        categoryname.setText(group.getFullNameCategory());
        ImageView img_del_cat = (ImageView)convertView.findViewById(R.id.img_del_cat);

        MyTagcat myTagcat=new MyTagcat(group.getCategoryID(),group.getUserEventCatID(),groupPosition);
        img_del_cat.setTag(myTagcat);


        img_del_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyTagcat myTagcat = (MyTagcat) v.getTag();
                catID = myTagcat.catID;
                userEventCatID = myTagcat.user_catID;
                userEventCatPos = myTagcat.cat_pos;
                Log.w("user event catid is", userEventCatID + " ");
                open(v);
            }
        });

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



    // popup dialog to add items to category
    public void showDialogToAddChecklist_item()
    {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_checklist_item);
        dialog.setCancelable(false);
        dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
        days =  (EditText) dialog.findViewById(R.id.days);
        Button dialog_add_item=(Button)dialog.findViewById(R.id.dialog_add_item);



        dialog_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog_event_name.getText().toString().length() == 0 || days.getText().toString().length() == 0) {
                    Toast.makeText(context, "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask5 objasyntask5 = new asyntask5(context);
                    asyntask5.setListner(checklist_itemadapter.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListEventItemAdd", "userID=" + userID, "eventID=" +
                            group.getNumChecklist(), "catID=" + catID, "userCatID=" + userEventCatID, "fullName=" + dialog_event_name.getText().toString(),
                            "daysCompleteBeforeEvents=" + days.getText().toString()};
                    Log.w("userID=  ", userID + " eventID= " + group.getNumChecklist() + " catID= " + catID + " userCatID= " + userEventCatID + "  fullName= " + dialog_event_name.getText().toString()
                            + " daysCompleteBeforeEvents= " + days.getText().toString());

                    objasyntask5.execute(strinputarray);
                }
            }
        });

        ((ImageView) dialog.findViewById(R.id.dialog_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

  // dialogbox to confirm if you want to delete
    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                asyntask4 objasyntask4 = new asyntask4(context);
                asyntask4.setListner(checklist_itemadapter.this);
                Log.w("userEventCatID is " , userEventCatID + " ");
                String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListEventCategoryRemove", "userEventCatID=" + userEventCatID};
                objasyntask4.execute(strinputarray);
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


    //Delete the checklist category  and Refresh the checklist category
    @Override
    public void getResponse4(String strinput) {
        checklist_del_jsonparser obj_checklist_del_jsonparser = new checklist_del_jsonparser();
        ob_del_cat = obj_checklist_del_jsonparser.parser(strinput);
        String status_report = obj_checklist_del_jsonparser.arrayList.get(0);
        if(status_report=="0")
        {
            Log.w("position is", userEventCatPos + " ");
            groups.remove(userEventCatPos);
          Log.w("category deleted", "true");
            notifyDataSetChanged();

       }
    }


    //Add items to  checklist category
    @Override
    public void getResponse5(String strinput) {
        checklist_add_cat_jsonparser obj_checklist_add_item_jsonparser = new checklist_add_cat_jsonparser();
        ob_item_add = obj_checklist_add_item_jsonparser.parser(strinput);
        dialog.dismiss();
        String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
        if(status_report!="0") {

            //add locally the record in arraylist
            group= new customclass_checklist_item_group();
            child = new customclass_checklist_item_child();
            child.setNumItem(0);
            Log.w("typed", dialog_event_name.getText().toString());
            child.setFullNameItem(dialog_event_name.getText().toString());
            child.setStatusItem("");
            child.setDateofCompletion(days.getText().toString());
            chList.add(child);
            group.setItems(chList);
            groups.add(group);
            notifyDataSetChanged();
            child= chList.get(chList.size()-1);
            Log.w("last value", child.getFullNameItem() + " " );

        }
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

/*



 asyntask1 objasyntask1= new asyntask1(context);
            asyntask1.setListner(checklist_itemadapter.this);
            Log.w("eventID is ", group.getNumChecklist() + " ");
            shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
            userID = (shared.getString("num","0"));
            Log.w("userId is ", userID + " ");
            String[] strinputarray={"http://webservices.shaadielephant.com/usersChecklist.asmx/usersCheckListCategoryGrouped","userID="+userID,"eventID="+ group.getNumChecklist()};
            objasyntask1.execute(strinputarray);






    public void getResponse1(String strinput) {
        checklist_item_jsonparser obj_checklist_item_jsonparser = new checklist_item_jsonparser();
        groups = obj_checklist_item_jsonparser.parser(strinput);
        notifyDataSetChanged();

    }







*/
