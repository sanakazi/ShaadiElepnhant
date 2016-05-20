package com.shaadielephant.shaadielephant.budget_item;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.shaadielephant.shaadielephant.budget.fragmentbudget;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/18/2016.
 */
public class fragmentbudget_item extends Fragment implements  networkresponse1,networkresponse2,networkresponse3,networkresponse4{
    SharedPreferences shared;

    int plannedCost,availbleAmount,actualAmount,paidAmount,pendingAmount;
    ExpandableListView listbudget_item;
    ImageView add, img_del_budget, img_edit_budget;;
    TextView getmyBudget,getplannedCost,getpaid,getpending,getavailable,getactual;
    private static Dialog dialog ;
    ArrayList<customclass_budget_item_group> objArraylistadd;
    ArrayList<customclass_budget_item_child> objArraylistadd_child;

    ArrayList<String> objArraylistdetails,ob_del,ob_update,ob_cat_add;
    customclass_budget_item_combine obj_combine;

    RelativeLayout rl1;
    //budget specific paramneters
    static int budgetID,TotalBudget;
    static String userID,marriageDate,BudgetfullName;
     public String myBudgetName;

    //adapter items
    static String[] categories;
    public ArrayList<customclass_categorywise_vendor>objArraylist_categorywise_vendor;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragmentbudget_item,container,false);
        add = (ImageView)v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddBudget_category();
            }
        });

        getmyBudget=(TextView)v.findViewById(R.id.getmyBudget);
        getplannedCost=(TextView)v.findViewById(R.id.getplannedCost);
        getpaid=(TextView)v.findViewById(R.id.getpaid);
        getpending=(TextView)v.findViewById(R.id.getpending);
        getavailable=(TextView)v.findViewById(R.id.getavailable);
        getactual=(TextView)v.findViewById(R.id.getactual);

        listbudget_item=(ExpandableListView)v.findViewById(R.id.listbudget_item);

        rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment budget_item rl1");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddBudget_category();
            }
        });
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        Bundle inputargs = getArguments();
        budgetID = inputargs.getInt("eventID");
        BudgetfullName= inputargs.getString("BudgetfullName");
        marriageDate= inputargs.getString("marriageDate");
        TotalBudget = inputargs.getInt("TotalBudget");


      //  ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Budget> " + BudgetfullName);

        //For changing actionbar title
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > Budget > " + BudgetfullName);
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        RelativeLayout l2 = (RelativeLayout)v.findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Don not reload ", " fragment");
            }
        });


    //refreshing catlist
        asyntask1 objasyntask1= new asyntask1(getActivity());
        asyntask1.setListner(fragmentbudget_item.this);
        Log.w("userid is ", userID);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersBudgetList.asmx/usersBudgetCategoryGrouped","userID="+userID,"budgetID="+budgetID};
        objasyntask1.execute(strinputarray);



        img_del_budget = (ImageView)v.findViewById(R.id.img_del);
        img_edit_budget=(ImageView)v.findViewById(R.id.img_edit);

        img_del_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBudget(v);
            }
        });

        img_edit_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToEditBudget();
            }
        });

        return v;
    }

    //popup dialog to add budget category
    private void showDialogToAddBudget_category(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_budget_category);
        dialog.setCancelable(false);
        // set the custom dialog components - text, image and button
        final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
        final EditText dialog_planned_cost = (EditText)dialog.findViewById(R.id.dialog_planned_cost);


        ((Button) dialog.findViewById(R.id.dialog_add_checklist_cat)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write your ws implementation here
                if (dialog_event_name.getText().toString().length() == 0 || dialog_planned_cost.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert the required fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask4 objasyntask4 = new asyntask4(getActivity());
                    asyntask4.setListner(fragmentbudget_item.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetCategoryAdd", "userID=" + userID,
                            "budgetID=" + budgetID, "fullName=" + dialog_event_name.getText().toString(),
                            "plannedCost=" + dialog_planned_cost.getText().toString()};

                    objasyntask4.execute(strinputarray);

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



    //popup dialog to edit budget
    private void showDialogToEditBudget(){


        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_budget);
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
        final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
        final  EditText dialog_event_date =  (EditText) dialog.findViewById(R.id.dialog_event_date);
        final  EditText dialog_total_budget =  (EditText) dialog.findViewById(R.id.dialog_total_budget);


        dialog_event_name.setText(String.valueOf(BudgetfullName));
        dialog_event_date.setText(String.valueOf(marriageDate));
        dialog_total_budget.setText(String.valueOf(TotalBudget));

        ((Button) dialog.findViewById(R.id.dialog_add_budget)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write your ws implementation here
                if (dialog_event_name.getText().toString().length() == 0 || dialog_event_date.getText().toString().length() == 0 || dialog_total_budget.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask3 objasyntask3 = new asyntask3(getActivity());
                    asyntask3.setListner(fragmentbudget_item.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetUpdate", "userID=" + userID, "budgetID=" + budgetID
                            , "fullName=" +  dialog_event_name.getText().toString(), "marriageDate=" + dialog_event_date.getText().toString(), "totalBudgetAmount=" + dialog_total_budget.getText().toString()};
                    objasyntask3.execute(strinputarray);
                }
            }
        });

        dialog_event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new fragmentbudget.DatePickerFragment();
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

    // refreshing the catlist
    @Override
    public void getResponse1(String strinput) {

            budget_item_jsonparser obj = new budget_item_jsonparser();
            obj_combine = obj.parser(strinput);
            objArraylistadd = obj_combine.getBudget_item_group();
            objArraylistdetails = obj_combine.getBudget_details();
            budget_item_adapter obj_adapter = new budget_item_adapter(getActivity(), objArraylistadd);
            // listchecklist_item.setAdapter((BaseExpandableListAdapter) null);
            listbudget_item.setAdapter(obj_adapter);


            //get values for budget

            myBudgetName = objArraylistdetails.get(1);
            plannedCost = Integer.parseInt(objArraylistdetails.get(4));
            availbleAmount = Integer.parseInt(objArraylistdetails.get(5));
            actualAmount = Integer.parseInt(objArraylistdetails.get(6));
            paidAmount = Integer.parseInt(objArraylistdetails.get(7));
            pendingAmount = Integer.parseInt(objArraylistdetails.get(8));


            ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Budget > " + myBudgetName);

            getmyBudget.setText(String.valueOf(TotalBudget));
            getplannedCost.setText(String.valueOf(plannedCost));
            getpaid.setText(String.valueOf(paidAmount));
            getpending.setText(String.valueOf(pendingAmount));
            getavailable.setText(String.valueOf(availbleAmount));
            getactual.setText(String.valueOf(actualAmount));


    }

    // to delete budget
    @Override
    public void getResponse2(String strinput) {

            budget_del_jsonparser obj_checklist_del_jsonparser = new budget_del_jsonparser();
            ob_del = obj_checklist_del_jsonparser.parser(strinput);
            String status_report = obj_checklist_del_jsonparser.arrayList.get(0);

            if (status_report == "1") {
                Toast.makeText(getActivity(), "Budget deleted", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentbudget fc = new fragmentbudget();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();

        }
    }
    // to edit budget info
    @Override
    public void getResponse3(String strinput) {

            budget_del_jsonparser obj_checklist_del_jsonparser = new budget_del_jsonparser();
            ob_update = obj_checklist_del_jsonparser.parser(strinput);
            dialog.dismiss();
            String status_report = obj_checklist_del_jsonparser.arrayList.get(0);

            if (status_report == "1") {


                asyntask1 objasyntask1 = new asyntask1(getActivity());
                asyntask1.setListner(fragmentbudget_item.this);
                Log.w("userid is ", userID);
                String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/usersBudgetCategoryGrouped", "userID=" + userID, "budgetID=" + budgetID};
                objasyntask1.execute(strinputarray);

                Toast.makeText(getActivity(), "Budget is updated", Toast.LENGTH_SHORT).show();


        }
    }

    // adding budget category and refreshing
    @Override
    public void getResponse4(String strinput) {

            budget_add_cat_jsonparser ob = new budget_add_cat_jsonparser();
            ob_cat_add = ob.parser(strinput);
            dialog.dismiss();
            String status_report = ob.arrayList.get(0);
            if (status_report != "0") {

                refresh();
                Toast.makeText(getActivity(), "Category added", Toast.LENGTH_SHORT).show();


        }
    }

    //dialogbox to confirm delete of budget
    public void deleteBudget(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                asyntask2 objasyntask2 = new asyntask2(getActivity());
                asyntask2.setListner(fragmentbudget_item.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetRemove", "userID=" + userID, "budgetID=" + budgetID};
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


    public void refresh(){
        //refreshing catlist
        asyntask1 objasyntask1= new asyntask1(getActivity());
        asyntask1.setListner(fragmentbudget_item.this);
        Log.w("userid is ", userID);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersBudgetList.asmx/usersBudgetCategoryGrouped","userID="+userID,"budgetID="+budgetID};
        objasyntask1.execute(strinputarray);
    }


    //adapter as inner class

    public class budget_item_adapter extends BaseExpandableListAdapter implements networkresponse5,networkresponse6,networkresponse7,networkresponse8,networkresponse9,networkresponse10,networkresponse11
    {
        private Context context;
        private ArrayList<customclass_budget_item_group> groups;
        ArrayList<String>ob_del_cat,ob_item_add,ob_del_item,ob_vendor_edit;
        ArrayList<customclass_budget_item_child> chList;
        private Dialog dialog ;
        int catID,userEventCatID,userEventCatPos,userEventItemID;
        int itemID, itemPos, itemVendorId,vendor_actual_cost, vendor_paid_amt;
        String userID, itemlist_vendorStatus , vendorName,vendorPhone, vendorRemarks;
        EditText dialog_event_name ,days;
        TextView fullName,item_plannedCost,item_actual,item_diff,item_paid,item_pending,txt_select_vendor,txt_vendor_payment,txt_vendor_name;
        SharedPreferences shared;
        customclass_budget_item_group group;
        customclass_budget_item_child child;
        ArrayAdapter<String> dataAdapter;

        // dialog for finalize vendor;
        Spinner spinner;
        EditText dialog_vendor_phone,dialog_vendor_remarks,dialog_vendor_totalcost,dialog_vendor_advance,dialog_select_vendor_name;
        Button dialog_add_budget,dialog_save;
        int spinner_selected_pos;


        //dialog to make vendor pending payment
        TextView dialog_vendor_pending;
        EditText dialog_paidToVendor;
        Button dialog_add_amount;
        int pendingItemAmount;

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
                    convertView = infalInflater.inflate(R.layout.singlerow_budget_item_staticrow, null);
                }
                if(childPosition>0)
                {
                    convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_child, null);

                }
            }


            if(childPosition==0) {

                convertView = infalInflater.inflate(R.layout.singlerow_budget_item_staticrow, null);
            }

            if(childPosition>0)
            {

                convertView = infalInflater.inflate(R.layout.singlerow_budget_item_child, null);
                fullName = (TextView) convertView.findViewById(R.id.fullName);
                fullName.setText(child.getItemlist_fullName());
                item_plannedCost = (TextView) convertView.findViewById(R.id.item_plannedCost);
                item_plannedCost.setText(String.valueOf(child.getItemlist_plannedCost()));
                item_actual = (TextView) convertView.findViewById(R.id.item_actual);
                item_actual.setText(String.valueOf(child.getItemlist_actualAmount()));
                item_diff = (TextView) convertView.findViewById(R.id.item_diff);
                item_diff.setText(String.valueOf(child.getItemlist_DiffAmount()));
                item_paid = (TextView) convertView.findViewById(R.id.item_paid);
                item_paid.setText(String.valueOf(child.getItemlist_paidAmount()));
                item_pending = (TextView) convertView.findViewById(R.id.item_pending);
                item_pending.setText(String.valueOf(child.getItemlist_pendingAmount()));
                itemlist_vendorStatus=String.valueOf(child.getItemlist_status());

                txt_select_vendor= (TextView) convertView.findViewById(R.id.txt_select_vendor);
                txt_vendor_payment= (TextView) convertView.findViewById(R.id.txt_vendor_payment);
                txt_vendor_payment.setVisibility(View.INVISIBLE);
                txt_vendor_name = (TextView) convertView.findViewById(R.id.txt_vendor_name);
                txt_vendor_name.setText(child.getItemlist_vendorName());
                txt_vendor_name.setVisibility(View.INVISIBLE);


                if(itemlist_vendorStatus.toString().equals("VendorFinalized"))
                {

                    txt_select_vendor.setVisibility(View.INVISIBLE);
                    txt_vendor_name.setVisibility(View.VISIBLE);
                    txt_vendor_payment.setVisibility(View.VISIBLE);
                }

                //finalizing vendor for item
                MyTagItem myTagitem=new MyTagItem(child.getItemlist_num(),childPosition , child.getItemlist_vendorID(), group.getCategoryID(),child.getItemlist_pendingAmount(),
                        child.getItemlist_actualAmount(),child.getItemlist_paidAmount(),child.getItemlist_vendorName(),child.getItemlist_vendorPhone(),child.getItemlist_vendorOtherRemarks());
                txt_select_vendor.setTag(myTagitem);
              //  txt_select_vendor.setTag(child.getItemlist_num());
                txt_select_vendor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTagItem myTagitem = (MyTagItem) v.getTag();
                        itemID = myTagitem.budgetItemNum;
                        itemPos = myTagitem.budgetItem_pos;
                        itemVendorId = myTagitem.vendorID;
                        catID = myTagitem.catID;

                        // to load vendor category list
                       /* asyntask9 objasyntask9 = new asyntask9(context);
                        asyntask9.setListner(budget_item_adapter.this);
                        objasyntask9.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/usersVendorListCategoryWise", "userID=" + userID, "CatID=" + catID);
                        Log.w("1) click on", " select vendor");
                        */
                        selectVendor(itemID, itemVendorId, catID);
                    }
                });



                //editing finalized vendor
                ImageView img_edit_item = (ImageView)convertView.findViewById(R.id.img_edit);
                img_edit_item.setTag(myTagitem);
                //deleting item from category list
                img_edit_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTagItem myTagitem = (MyTagItem) v.getTag();
                        itemID = myTagitem.budgetItemNum;
                        itemPos = myTagitem.budgetItem_pos;
                        itemVendorId = myTagitem.vendorID;
                        catID = myTagitem.catID;
                        vendor_actual_cost=myTagitem.tot_cost;
                        vendor_paid_amt = myTagitem.amt_paid;
                        vendorName= myTagitem.vendorName;
                        vendorPhone = myTagitem.vendorPhone;
                        vendorRemarks = myTagitem.vendorRemarks;

                        Log.w("ADAPTER vendor remarks ", vendorRemarks);
                        editFinalizedVendor(itemID, itemVendorId, catID,itemPos,vendor_actual_cost,vendor_paid_amt,vendorName,vendorPhone,vendorRemarks);

                    }
                });



                txt_vendor_payment.setTag(myTagitem);
                txt_vendor_payment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTagItem myTagitem = (MyTagItem) v.getTag();
                        pendingItemAmount = myTagitem.itemPending;
                        itemID = myTagitem.budgetItemNum;
                        makeVendorpayment(itemID, pendingItemAmount);

                    }
                });

                ImageView img_del_item = (ImageView)convertView.findViewById(R.id.img_del);
                img_del_item.setTag(child.getItemlist_num());
                //deleting item from category list
                img_del_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userEventItemID = (Integer) v.getTag();
                        delete_item(userEventItemID);
                    }
                });




            }

            if(childPosition==getChildrenCount(groupPosition)-1)
            {
                convertView = infalInflater.inflate(R.layout.singlerowchecklist_item_staticrow_plus, null);
                ImageView img_add_item = (ImageView)convertView.findViewById(R.id.img_add_item);
                // img_add_item.setTag(child.getNumItem());
                MyTagcat myTagitem=new MyTagcat(group.getCategoryID(),group.getUserCatID(),groupPosition);
                img_add_item.setTag(myTagitem);
                img_add_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTagcat myTagitem=(MyTagcat)v.getTag();
                        catID=myTagitem.catID;
                        userEventCatID=myTagitem.user_catID;
                        userEventCatPos=myTagitem.cat_pos;
                        showDialogToAddBudget_item();
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

            img_del_cat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MyTagcat myTagcat = (MyTagcat) v.getTag();
                    catID = myTagcat.catID;
                    userEventCatID = myTagcat.user_catID;
                    userEventCatPos = myTagcat.cat_pos;
                    Log.w("user event catid is", catID + " ");
                    deleteCategory(catID);
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


        // dialogbox to confirm if you want to delete category
        public void deleteCategory(int d){
            final int id = d;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setMessage("Are you sure you want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    asyntask5 objasyntask5 = new asyntask5(context);
                    asyntask5.setListner(budget_item_adapter.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetCategoryRemove", "userID=" + userID, "userBudgetCatID=" + id};
                    objasyntask5.execute(strinputarray);

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



        // popup dialog to add items to category
        public void showDialogToAddBudget_item()
        {

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_add_budget_item);
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
                        asyntask6 objasyntask6 = new asyntask6(context);
                        asyntask6.setListner(budget_item_adapter.this);
                        String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetItemAdd", "userID=" + userID, "budgetID=" +
                                budgetID, "catID=" + catID, "userCatID=" + userEventCatID, "fullName=" + dialog_event_name.getText().toString(),
                                "plannedCost=" + days.getText().toString()};
                        Log.w("userID=  ", userID + " budgetID= " + budgetID + " catID= " + catID + " userCatID= " + userEventCatID + "  fullName= " + dialog_event_name.getText().toString()
                                + " plannedCost= " + days.getText().toString());

                        objasyntask6.execute(strinputarray);

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

        // dialogbox to confirm if you want to delete item
        public void delete_item(int a){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setMessage("Are you sure you want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    asyntask7 objasyntask7 = new asyntask7(context);
                    asyntask7.setListner(budget_item_adapter.this);
                    Log.w("userEventItemID is ", userEventItemID + " ");
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetItemRemove", "userID=" + userID, "BudgetItemID=" + userEventItemID};
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


        //popup dialog to finalize vendor
        public void selectVendor(int budget_itemID, int budget_itemVendorId , int budget_catID)
        {
            // to load vendor category list
            asyntask9 objasyntask9= new asyntask9(context);
            asyntask9.setListner(budget_item_adapter.this);
            objasyntask9.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/usersVendorListCategoryWise", "userID=" + userID, "CatID=" + budget_catID);

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_budgetitem_finalize_vendor);
            dialog.setCancelable(false);
            ((ImageView) dialog.findViewById(R.id.dialog_cancel)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            spinner = (Spinner)dialog.findViewById(R.id.dialog_select_vendor);
            spinner.setPrompt("Select Vendor");
            dialog_select_vendor_name = (EditText)dialog.findViewById(R.id.dialog_select_vendor_name);
            dialog_vendor_phone = (EditText)dialog.findViewById(R.id.dialog_vendor_phone);
            dialog_vendor_remarks = (EditText)dialog.findViewById(R.id.dialog_vendor_remarks);
            dialog_vendor_totalcost = (EditText)dialog.findViewById(R.id.dialog_vendor_totalcost);
            dialog_vendor_advance = (EditText)dialog.findViewById(R.id.dialog_vendor_advance);
            dialog_add_budget = (Button)dialog.findViewById(R.id.dialog_add_budget);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position;
                    if(pos==0)
                    {
                        dialog_vendor_phone.setHint(R.string.budget_item_vendor_phone);
                        dialog_select_vendor_name.setHint(R.string.budget_item_vendor_name);
                        dialog_vendor_remarks.setHint(R.string.budget_item_vendor_other_remarks);
                    }
                    else {
                        customclass_categorywise_vendor local = objArraylist_categorywise_vendor.get(pos-1);
                        dialog_vendor_phone.setText(local.getPhone());
                        dialog_select_vendor_name.setText(local.getFullName());
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            // to finalize vendor
            dialog_add_budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asyntask8 objasyntask8= new asyntask8(context);
                    asyntask8.setListner(budget_item_adapter.this);
                    // itemID,itemVendorId,catID
                    objasyntask8.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/userItemFinalizeVendor", "budgetItemNum=" + itemID,
                            "vendorID=" + itemVendorId,"vendorName="+dialog_select_vendor_name.getText().toString(),"vendorPhone="+dialog_vendor_phone.getText().toString(),
                            "vendorOtherRemarks="+dialog_vendor_remarks.getText().toString(),"TotalBudget="+dialog_vendor_totalcost.getText().toString(),
                            "advancedPaid="+dialog_vendor_advance.getText().toString());


                }
            });


            dialog.show();
        }


        ////popup dialog to edit finalized vendor
        public void editFinalizedVendor(int budget_itemID, int budget_itemVendorId , int budget_catID, int pos , int vendor_actual_cost , int vendor_paid_amt , String vendorName, String vendorPhone, String vendorRemarks)
        {
            final int budgettemid=budget_itemID;
            final String vendorname=vendorName;
            final int vendorcost =vendor_actual_cost;
            final int vendorId=budget_itemVendorId;
            // to load vendor category list
            asyntask9 objasyntask9= new asyntask9(context);
            asyntask9.setListner(budget_item_adapter.this);
            objasyntask9.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/usersVendorListCategoryWise", "userID=" + userID, "CatID=" + budget_catID);

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_budgetitem_edit_finalized_vendor);
            dialog.setCancelable(false);
            ((ImageView) dialog.findViewById(R.id.dialog_cancel)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            spinner = (Spinner)dialog.findViewById(R.id.dialog_select_vendor);
            spinner.setPrompt("Select Vendor");
            dialog_select_vendor_name = (EditText)dialog.findViewById(R.id.dialog_select_vendor_name);
            dialog_vendor_phone = (EditText)dialog.findViewById(R.id.dialog_vendor_phone);
            dialog_vendor_remarks = (EditText)dialog.findViewById(R.id.dialog_vendor_remarks);
            dialog_vendor_totalcost = (EditText)dialog.findViewById(R.id.dialog_vendor_totalcost);
            dialog_vendor_advance = (EditText)dialog.findViewById(R.id.dialog_vendor_advance);
            dialog_save = (Button)dialog.findViewById(R.id.dialog_save);

            Log.w("Vendor pos= ", pos + " ");
            dialog_select_vendor_name.setText(vendorName);
            dialog_vendor_phone.setText(vendorPhone);
            dialog_vendor_remarks.setText(vendorRemarks);
            dialog_vendor_totalcost.setText(String.valueOf(vendor_actual_cost));
            dialog_vendor_advance.setText(String.valueOf(vendor_paid_amt));

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    int pos = position;
                    if(pos==0)
                    {

                    }
                    else {
                        customclass_categorywise_vendor local = objArraylist_categorywise_vendor.get(pos-1);
                        dialog_vendor_phone.setText(local.getPhone());
                        dialog_select_vendor_name.setText(local.getFullName());
                        dialog_vendor_remarks.setHint(R.string.budget_item_vendor_other_remarks);
                    }




                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            dialog_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //show categorylist
                    asyntask11 objasyntask11= new asyntask11(context);
                    asyntask11.setListner(budget_item_adapter.this);
                    // itemID,itemVendorId,catID
                    objasyntask11.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/userItemFinalizeVendor", "budgetItemNum=" + budgettemid,
                            "vendorID=" + vendorId, "vendorName=" + dialog_select_vendor_name.getText().toString(), "vendorPhone=" + dialog_vendor_phone.getText().toString(),
                            "vendorOtherRemarks=" + dialog_vendor_remarks.getText().toString(), "TotalBudget=" + dialog_vendor_totalcost.getText().toString(),
                            "advancedPaid=" + dialog_vendor_advance.getText().toString());
                }
            });

            dialog.show();
        }



        //list of vendors categorywise
        public void vendorList()
        {

            Log.w("Prompt", " " + spinner.getPrompt());
            Log.w("3) vendor list ", "category executed ");
            if(objArraylist_categorywise_vendor!=null) {
                categories = new String[(objArraylist_categorywise_vendor.size())+1];
                Log.d("size is ", (objArraylist_categorywise_vendor.size())+1 + " ");

                categories[0]="Select Vendor";
                for (int i = 1; i <= objArraylist_categorywise_vendor.size(); i++)
                {
                    customclass_categorywise_vendor test = objArraylist_categorywise_vendor.get(i-1);
                    categories[i] = test.getFullName();
                    Log.d("vendor full name is ", categories[i] + " ");
                }

                // Creating adapter for spinner
                dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);

            }

        }

        //popup dialog to make vendor pending payment
        public void makeVendorpayment(int id, int budgetItempendingAmount)
        {   final int budget_itemID=id;
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_budgetitem_makepending_payment);
            dialog.setCancelable(false);

            ((ImageView) dialog.findViewById(R.id.dialog_cancel)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog_vendor_pending = (TextView)dialog.findViewById(R.id.dialog_vendor_pending);
            dialog_paidToVendor = (EditText)dialog.findViewById(R.id.dialog_paidToVendor);
            dialog_add_amount = (Button)dialog.findViewById(R.id.dialog_add_amount);

            dialog_vendor_pending.setText(String.valueOf(budgetItempendingAmount));
            dialog_add_amount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asyntask10 objasyntask10= new asyntask10(context);
                    asyntask10.setListner(budget_item_adapter.this);
                    // itemID,itemVendorId,catID
                    objasyntask10.execute("http://webservices.shaadielephant.com/usersBudgetList.asmx/userItemPayBalanceToVendor", "budgetItemNum=" + budget_itemID,
                            "paymentAmount=" +dialog_paidToVendor.getText().toString());
                }
            });
            dialog.show();
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


        class MyTagItem
        {   int budgetItemNum, budgetItem_pos, vendorID, catID,itemPending, tot_cost, amt_paid;
            String vendorName,vendorPhone,vendorRemarks;



            public MyTagItem()
            {
                budgetItemNum=0;
                catID=0;
                vendorID=0;
                budgetItem_pos=0;
                itemPending=0;
                tot_cost=0;
            }

            public MyTagItem(int budgetItemNum,int budgetItem_pos,int vendorID,int catID,int itemPending)
            {
                this.budgetItemNum=budgetItemNum;
                this.budgetItem_pos=budgetItem_pos;
                this.vendorID=vendorID;
                this.catID=catID;
                this.itemPending=itemPending;
            }

            public MyTagItem(int budgetItemNum,int budgetItem_pos,int vendorID,int catID,int itemPending,int tot_cost,int amt_paid,String vendorName,String vendorPhone, String vendorRemarks)
            {
                this.budgetItemNum=budgetItemNum;
                this.budgetItem_pos=budgetItem_pos;
                this.vendorID=vendorID;
                this.catID=catID;
                this.itemPending=itemPending;
                this.tot_cost=tot_cost; this.amt_paid=amt_paid; this.vendorName =vendorName; this.vendorPhone=vendorPhone; this.vendorRemarks=vendorRemarks;
            }

        }
        // to remove budget category
        @Override
        public void getResponse5(String strinput) {

                budget_del_jsonparser obj_checklist_del_jsonparser = new budget_del_jsonparser();
                ob_del_cat = obj_checklist_del_jsonparser.parser(strinput);
                String status_report = obj_checklist_del_jsonparser.arrayList.get(0);
                if (status_report == "1") {
                    refresh();
                    Toast.makeText(context, "Category deleted", Toast.LENGTH_SHORT).show();

            }
        }
        //Add items to  checklist category
        @Override
        public void getResponse6(String strinput) {

                budget_add_cat_jsonparser obj_checklist_add_item_jsonparser = new budget_add_cat_jsonparser();
                ob_item_add = obj_checklist_add_item_jsonparser.parser(strinput);
                dialog.dismiss();
                String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
                if (status_report != "0") {
                    refresh();
                    Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show();

            }
        }
        // Deleting items from checklist category
        @Override
        public void getResponse7(String strinput) {

                budget_del_jsonparser obj_budget_del_jsonparser = new budget_del_jsonparser();
                ob_del_item = obj_budget_del_jsonparser.parser(strinput);
                String status_report = obj_budget_del_jsonparser.arrayList.get(0);
                if (status_report == "1") {
                    refresh();
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();

            }
        }
        //finalize vendor

        @Override
        public void getResponse8(String strinput) {

                budget_add_cat_jsonparser obj_checklist_add_item_jsonparser = new budget_add_cat_jsonparser();
                ob_item_add = obj_checklist_add_item_jsonparser.parser(strinput);
                dialog.dismiss();
                String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
                if (status_report != "0") {
                    refresh();
                    Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show();

            }
        }

        // get category wise vendors
        @Override
        public void getResponse9(String strinput) {

                //ob_categorywise_vendor
                Log.w("2) response 9", "for category executed 2");
                budget_item_categorywise_vendor_jsonparser ob = new budget_item_categorywise_vendor_jsonparser();
                objArraylist_categorywise_vendor = ob.parser(strinput);
                vendorList();


        }
        // to make pending payment of vendor

        @Override
        public void getResponse10(String strinput) {

                budget_add_cat_jsonparser obj_checklist_add_item_jsonparser = new budget_add_cat_jsonparser();
                ob_item_add = obj_checklist_add_item_jsonparser.parser(strinput);
                dialog.dismiss();
                String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
                if (status_report != "0") {
                    refresh();
                    Toast.makeText(context, "Payment updated", Toast.LENGTH_SHORT).show();

            }
        }

        // submit of editing finalized vendor

        @Override
        public void getResponse11(String strinput) {

                budget_add_cat_jsonparser obj_checklist_add_item_jsonparser = new budget_add_cat_jsonparser();
                ob_vendor_edit = obj_checklist_add_item_jsonparser.parser(strinput);
                dialog.dismiss();
                String status_report = obj_checklist_add_item_jsonparser.arrayList.get(0);
                if (status_report != "0") {
                    refresh();
                    Toast.makeText(context, "Item edited successfully", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //adapter class closes


    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Budget > " +BudgetfullName);
    }

}
