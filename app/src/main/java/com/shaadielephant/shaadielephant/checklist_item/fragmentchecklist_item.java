package com.shaadielephant.shaadielephant.checklist_item;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.checklist.fragmentchecklist;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callndata on 2/4/2016.
 */
public class fragmentchecklist_item extends Fragment implements networkresponse1,networkresponse2,networkresponse3,networkresponse6 {
    ExpandableListView listchecklist_item;
    ImageView add,img_del,img_edit;
    private static View v;
    SharedPreferences shared;
    String userID;
    int eventID,userEventItemID;
    String checklistName,checklistDate;
    private static Dialog dialog ;
    ArrayList<String> ob_del,ob_cat_add,ob_update;
    public Boolean isUserAction = false;
    static public String mychecklistName;

    ArrayList<customclass_checklist_item_group> objArraylistadd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragmentchecklist_item, container, false);
        Log.w("inside create " , " checklist_item frag");
        listchecklist_item=(ExpandableListView)v.findViewById(R.id.listchecklist_item);
        add= (ImageView)v.findViewById(R.id.add);
        img_del= (ImageView)v.findViewById(R.id.img_del);
        img_edit=(ImageView)v.findViewById(R.id.img_edit);
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        Bundle inputargs = getArguments();
        eventID = inputargs.getInt("eventID");
        checklistName=inputargs.getString("checklistName");
        checklistDate=inputargs.getString("checklistDate");

        //For changing actionbar title
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > Checklist >"+checklistName);
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));


        RelativeLayout l2 = (RelativeLayout)v.findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Don not reload ", " fragment");
            }
        });

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment checklist_item rl1");
            }
        });

        Log.w("checklist event id is ", eventID + " ");
        init();
        asyntask1 objasyntask1= new asyntask1(getActivity());
        asyntask1.setListner(fragmentchecklist_item.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersChecklist.asmx/usersCheckListCategoryGrouped","userID="+userID,"eventID="+eventID};
        objasyntask1.execute(strinputarray);






        img_del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteChecklist(v);
            }
        });

        img_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialogToEditChecklist();
            }
        });
        return v;


    }



    private void init(){

        ((ImageView) v.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddChecklist_category();
            }
        });
    }

    //popup dialog to add checklist category
    private void showDialogToAddChecklist_category(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_checklist_category);
        dialog.setCancelable(false);
        // set the custom dialog components - text, image and button
        final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);


        ((Button) dialog.findViewById(R.id.dialog_add_checklist_cat)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write your ws implementation here
                if(dialog_event_name.getText().toString().length() == 0 )
                {
                    Toast.makeText(getActivity(), "Please insert Category Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    asyntask3 objasyntask3 = new asyntask3(getActivity());
                    asyntask3.setListner(fragmentchecklist_item.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListEventCategoryAdd", "userID=" + userID, "eventID=" +
                            eventID, "fullName=" + dialog_event_name.getText().toString()};

                    objasyntask3.execute(strinputarray);
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



    //popup dialog to edit checklist
    private void showDialogToEditChecklist(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_checklist);
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
        final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
        final  EditText dialog_event_date =  (EditText) dialog.findViewById(R.id.dialog_event_date);
        dialog_event_name.setText(checklistName);
        dialog_event_date.setText(checklistDate);

        ((Button) dialog.findViewById(R.id.dialog_add_checklist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write your ws implementation here
                if (dialog_event_name.getText().toString().length() == 0 || dialog_event_date.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask6 objasyntask6 = new asyntask6(getActivity());
                    asyntask6.setListner(fragmentchecklist_item.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListUpdate", "userID=" + userID, "eventID=" + eventID
                            , "fullName=" + dialog_event_name.getText().toString(), "marriageDate=" + dialog_event_date.getText().toString()};
                    objasyntask6.execute(strinputarray);

                }

            }
        });

        dialog_event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new fragmentchecklist.DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
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


    @Override
    public void getResponse1(String strinput) {

            checklist_item_jsonparser obj_checklist_item_jsonparser = new checklist_item_jsonparser();
            objArraylistadd = obj_checklist_item_jsonparser.parser(strinput);
            checklist_itemadapter objfav_vendoradapter = new checklist_itemadapter(getActivity(), objArraylistadd);
            // listchecklist_item.setAdapter((BaseExpandableListAdapter) null);
            listchecklist_item.setAdapter(objfav_vendoradapter);

            customclass_checklist_item_group test = objArraylistadd.get(1);
            mychecklistName = test.getFullNameChecklist();

            ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Checklist > " + mychecklistName);


    }

    //deleting the entire checklist
    @Override
    public void getResponse2(String strinput) {

            checklist_del_jsonparser obj_checklist_del_jsonparser = new checklist_del_jsonparser();
            ob_del = obj_checklist_del_jsonparser.parser(strinput);
            String status_report = obj_checklist_del_jsonparser.arrayList.get(0);

            if (status_report == "1") {

                main_drawer call = new main_drawer();
                Log.w("last elemet is ", call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

                call.TITLE_LIST.remove(call.TITLE_LIST.size() - 1);

                for (int i = 0; i < call.TITLE_LIST.size(); i++) {
                    Log.w(" Updated Called  ", " Element: " + call.TITLE_LIST.get(i));
                }


                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentchecklist fc = new fragmentchecklist();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
                Toast.makeText(getActivity(), "Checklist deleted", Toast.LENGTH_SHORT).show();

        }
    }

    //adding category and refreshing
    @Override
    public void getResponse3(String strinput) {

            checklist_add_cat_jsonparser obj_checklist_add_cat_jsonparser = new checklist_add_cat_jsonparser();
            ob_cat_add = obj_checklist_add_cat_jsonparser.parser(strinput);
            dialog.dismiss();
            String status_report = obj_checklist_add_cat_jsonparser.arrayList.get(0);
            if (status_report != "0") {
                asyntask1 objasyntask1 = new asyntask1(getActivity());
                asyntask1.setListner(fragmentchecklist_item.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/usersCheckListCategoryGrouped", "userID=" + userID, "eventID=" + eventID};
                objasyntask1.execute(strinputarray);
                Toast.makeText(getActivity(), "Category Added", Toast.LENGTH_SHORT).show();


        }
    }
    // updating checklist name and date
    @Override
    public void getResponse6(String strinput) {

            checklist_del_jsonparser obj_checklist_del_jsonparser = new checklist_del_jsonparser();
            ob_update = obj_checklist_del_jsonparser.parser(strinput);
            dialog.dismiss();
            String status_report = obj_checklist_del_jsonparser.arrayList.get(0);

            if (status_report == "1") {
                refresh();
                Toast.makeText(getActivity(), "Checklist updated", Toast.LENGTH_SHORT).show();
            }

    }

    //dialogbox to confirm delete
    public void deleteChecklist(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                asyntask2 objasyntask2 = new asyntask2(getActivity());
                asyntask2.setListner(fragmentchecklist_item.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/usersCheckList.asmx/userCheckListRemove", "userID=" + userID, "eventID=" + eventID};
                objasyntask2.execute(strinputarray);
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



     public void refresh()
    {
        asyntask1 objasyntask1= new asyntask1(getActivity());
        asyntask1.setListner(fragmentchecklist_item.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersChecklist.asmx/usersCheckListCategoryGrouped","userID="+userID,"eventID="+eventID};
        objasyntask1.execute(strinputarray);
    }




    class checklist_itemadapter extends BaseExpandableListAdapter implements networkresponse4,networkresponse5,networkresponse7,networkresponse8
    {

        private Context context;
        private ArrayList<customclass_checklist_item_group> groups;
        ArrayList<String>ob_del_cat,ob_item_add,ob_del_item,ob_complete_status;
        ArrayList<customclass_checklist_item_child> chList;

        private  Dialog dialog ;
         int catID,userEventCatID,userEventCatPos;
         String userID;
         int statusItempos;
         EditText dialog_event_name ,days;
         TextView fullName,daysofcompletion,text_checklist;
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
            final  int uei;
            final String statusItem;
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
                text_checklist=(TextView)convertView.findViewById(R.id.text_checklist);
                fullName = (TextView) convertView.findViewById(R.id.fullName);
                fullName.setText(child.getFullNameItem());
                daysofcompletion = (TextView) convertView.findViewById(R.id.days);
                daysofcompletion.setText(child.getDateofCompletion());
                ImageView img_del = (ImageView)convertView.findViewById(R.id.img_del);
                final Spinner spinner_checklist= (Spinner)convertView.findViewById(R.id.spinner_checklist);

                img_del.setTag(child.getNumItem());
                 statusItem = child.getStatusItem();
                if(statusItem.equals("Completed"))
                {

                   // spinner_checklist.setEnabled(false);
                  //  spinner_checklist.setVisibility(View.INVISIBLE);
                   // text_checklist.setVisibility(View.VISIBLE);
                    statusItempos=1;
                    isUserAction= false;
                }
                else if(statusItem.equals("Pending")) {

                    statusItempos = 0;
                    isUserAction = true;
                }


      //deleting item from category list
                img_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userEventItemID= (Integer)v.getTag();
                        delete_item(userEventItemID);
                    }
                });


                List<String> categories = new ArrayList<String>();
                categories.add("Pending");
                categories.add("Completed");

                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner_checklist.setAdapter(dataAdapter);
                spinner_checklist.setSelection(statusItempos);
                spinner_checklist.setTag(child.getNumItem());
                uei = child.getNumItem();



                //marking item as completed
                spinner_checklist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if( statusItempos==0)
                        {
                            Log.w("  status Item ", statusItem + "  ");
                            // code for user initiated selection
                            if (parent.getSelectedItem().toString() == "Completed")
                            {
                                Log.w("Pending to complte ", uei + "  ");
                                 spinner_checklist.setEnabled(false);
                                asyntask8 objasyntask8 = new asyntask8(context);
                                asyntask8.setListner(checklist_itemadapter.this);
                                String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListEventItemMarkCompleted", "userEventItemID=" + uei};
                                objasyntask8.execute(strinputarray);


                                //  statusItempos=1;
                            }
                        }
                        else {
                            // code for programmatic selection
                            // also triggers on init (hence the default false)
                        }

                        // reset variable, so that it will always be true unless tampered with
                        isUserAction = true;
                       }



                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
                );


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
                    deleteCategory(v);
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

        // dialogbox to confirm if you want to delete category
        public void deleteCategory(View view){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setMessage("Are you sure you want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    asyntask4 objasyntask4 = new asyntask4(context);
                    asyntask4.setListner(checklist_itemadapter.this);
                    Log.w("userEventCatID is ", userEventCatID + " ");
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


        // dialogbox to confirm if you want to delete item
        public void delete_item(int a){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setMessage("Are you sure you want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    asyntask7 objasyntask7 = new asyntask7(context);
                    asyntask7.setListner(checklist_itemadapter.this);
                    Log.w("userEventItemID is ", userEventItemID + " ");
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListEventItemRemove", "userEventItemID=" + userEventItemID};
                    objasyntask7.execute(strinputarray);
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
                if (status_report == "1") {
                    refresh();
                    Toast.makeText(context, "Category Deleted", Toast.LENGTH_SHORT).show();



            }
        }


        //Add items to  checklist category
        @Override
        public void getResponse5(String strinput) {

                checklist_add_cat_jsonparser obj_checklist_add_item_jsonparser = new checklist_add_cat_jsonparser();
                ob_item_add = obj_checklist_add_item_jsonparser.parser(strinput);
                dialog.dismiss();
                String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
                if (status_report != "0") {
                    refresh();
                    Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show();


                }

        }

        //Deleting items from category
        @Override
        public void getResponse7(String strinput) {


                checklist_del_jsonparser obj_checklist_del_jsonparser = new checklist_del_jsonparser();
                ob_del_item = obj_checklist_del_jsonparser.parser(strinput);
                String status_report = obj_checklist_del_jsonparser.arrayList.get(0);
                if (status_report == "1") {

                    refresh();
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();


            }
        }

        //Marking item as complete
        @Override
        public void getResponse8(String strinput) {

                checklist_del_jsonparser obj_checklist_del_jsonparser = new checklist_del_jsonparser();
                ob_complete_status = obj_checklist_del_jsonparser.parser(strinput);
                String status_report = obj_checklist_del_jsonparser.arrayList.get(0);
                if (status_report == "1") {
                    //  refresh();


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

    @Override
    public void onResume() {
        super.onResume();

    }

}

