package com.shaadielephant.shaadielephant.fav_vendors;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;


import java.util.ArrayList;

/**
 * Created by Callndata on 1/28/2016.
 */
public class fragmentfav_vendor extends Fragment implements networkresponse1,networkresponse2,networkresponse3 {
    static ExpandableListView exp_list;
   fav_vendoradapter ExpAdapter;
    SharedPreferences shared;
     ArrayList<customclassfav_vendor_group> objArraylist;
    ArrayList<String> objArraylistvendor,ob_del;
    ImageView add;
    private static View v;
    private static Dialog dialog ;
    String productCatID;
    static String userID;
    ArrayList<String> objArraylistadd;
    Spinner spinner;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragmentfav_vendor, container, false);
       ((main_drawer) getActivity()).setbartitle(getActivity(),"Favorite Vendors");

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment vendorlist l2");
            }
        });

        exp_list = (ExpandableListView)v.findViewById(R.id.exp_list);
        add = (ImageView)v.findViewById(R.id.add);
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > Favorite Vendors");
        ((main_drawer) getActivity()).setbartitle(getActivity(),call.TITLE_LIST.get(call.TITLE_LIST.size()-1));

        //to get category List
        asyntask2 objasyntask2= new asyntask2(getActivity());
        asyntask2.setListner(fragmentfav_vendor.this);
        objasyntask2.execute("http://webservices.shaadielephant.com/vendors.asmx/categoryList");


        // to get fav vendors list
        refresh();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        init();


        return v;
    }

    /**
     * initialisation of this fragment
     */
    private void init(){

        Resources res = getResources();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddfav_vendor();
            }
        });
    }




    /**
     * function to show pop up dialog to add fav_vendor
     */
    private void showDialogToAddfav_vendor(){


        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_fav_vendors);
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
        final EditText favvendor_name = (EditText)dialog.findViewById(R.id.subject);
        final  EditText favvendor_brief =  (EditText) dialog.findViewById(R.id.brief);
        final  EditText favvendor_address =  (EditText) dialog.findViewById(R.id.favvendor_address);
        final  EditText favvendor_phone =  (EditText) dialog.findViewById(R.id.favvendor_phone);
            spinner = (Spinner)dialog.findViewById(R.id.spinner);
        fav_vendor();
                ((Button) dialog.findViewById(R.id.dialog_add_fav_vendor)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //write your ws implementation here
                        if (favvendor_name.getText().toString().length() == 0 || favvendor_brief.getText().toString().length() == 0 || favvendor_address.getText().toString().length() == 0 || favvendor_phone.getText().toString().length() == 0) {

                            Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                        } else {
                            asyntask3 objasyntask3 = new asyntask3(getActivity());
                            asyntask3.setListner(fragmentfav_vendor.this);
                        //    Log.w("VALUE OF productCatID ", productCatID);

                            String[] strinputarray = {"http://webservices.shaadielephant.com/usersVendors.asmx/userVendorAdd", "productCatID=" + productCatID, "vendorName=" + favvendor_name.getText().toString()
                                    , "VendorBrief=" + favvendor_brief.getText().toString(), "address=" + favvendor_address.getText().toString(), "phoneNumbers=" + favvendor_phone.getText().toString()
                                    , "userID=" + userID};


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


    public void refresh()
    {
        // to get fav vendors list
        asyntask1 objasyntask1 = new asyntask1(getActivity());
        asyntask1.setListner(fragmentfav_vendor.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorListCategoryGrouped","userID="+userID};
        objasyntask1.execute(strinputarray);
    }

    private void fav_vendor()

    {
        if(objArraylistvendor!=null) {
            String[] categories = new String[objArraylistvendor.size()];
            categories = objArraylistvendor.toArray(categories);


            final String categories_num[] = new String[objArraylistvendor.size() / 2];
            String categories_name[] = new String[objArraylistvendor.size() / 2];

            for (int i = 0; i < objArraylistvendor.size() / 2; i++) {
                categories_num[i] = categories[i + i];      //even numbers for productcatID
                categories_name[i] = categories[i + i + 1]; // odd numbers for name

            }

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_name);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    productCatID = categories_num[position];

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    productCatID = categories_num[0];
                }
            });

        }

       else if(objArraylistvendor==null) {

            String categories_name[] = new String[1];

           categories_name[0] = "Select Vendor Category";

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_name);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        }

    }




    //response for expandable fav vendors list
    @Override
    public void getResponse1(String strinput) {
        if(strinput=="noInternet")
            noInternetPage();
        else {
            Log.w("asyntask1 ", "response 1");
            fav_vendorjsonparser objfav_vendorjsonparser = new fav_vendorjsonparser();
            objArraylist = objfav_vendorjsonparser.parser(strinput);
            if (objArraylist != null) {
                fav_vendoradapter objfav_vendoradapter = new fav_vendoradapter(getActivity(), objArraylist);
                exp_list.setAdapter(objfav_vendoradapter);
            }
        }
    }

    //response for category list of vendors
    @Override
    public void getResponse2(String strinput)

    {
        if(strinput=="noInternet")
            noInternetPage();
        else {
            Log.w("asyntask2 ", "response 2");
            fav_vendor_add_category_jsonparser objvendorjsonparser = new fav_vendor_add_category_jsonparser();
            objArraylistvendor = objvendorjsonparser.parser(strinput);
        }
    }

   //adding new fav vendor
    @Override
    public void getResponse3(String strinput) {
        if(strinput=="noInternet")
            noInternetPage();
        else {
            fav_vendor_add_jsonparser obj_fav_vendor_add_jsonparser = new fav_vendor_add_jsonparser();
            objArraylistadd = obj_fav_vendor_add_jsonparser.parser(strinput);
            dialog.dismiss();
            Toast.makeText(getActivity(), "Vendor added successfully", Toast.LENGTH_SHORT).show();
            asyntask1 objasyntask = new asyntask1(getActivity());
            asyntask1.setListner(fragmentfav_vendor.this);
            String[] strinputarray = {"http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorListCategoryGrouped", "userID=" + userID};
            objasyntask.execute(strinputarray);
        }

    }


         //start adapter class
            public class fav_vendoradapter extends BaseExpandableListAdapter implements networkresponse4{

                private Context context;
                private ArrayList<customclassfav_vendor_group> groups;
                ArrayList<customclassfav_vendor_child> chList;
                ArrayList<String> ob_del;
                 ArrayList<customclassfav_vendor_group> objArraylist;
                SharedPreferences shared;
                String userID;
                 int a;
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
                    TextView phone  = (TextView) convertView.findViewById(R.id.phone);

                    fullName.setText(child.getFullName().toString());

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



                // method to delete vendor
                public void open(int id , int pos){
                    final int delete_id = id;
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    alertDialogBuilder.setMessage("Are you sure you want to delete?");

                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            asyntask4 objasyntask4= new asyntask4(context);
                            asyntask4.setListner(fav_vendoradapter.this);
                            String[] strinputarray={"http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorRemove","num="+delete_id};
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

             //delete vendor
             @Override
             public void getResponse4(String strinput)
             {
                 if(strinput=="noInternet")
                     noInternetPage();
                 else
                 {
                     fav_vendor_del_jsonparser obj_fav_vendor_del_jsonparser = new fav_vendor_del_jsonparser();
                     ob_del = obj_fav_vendor_del_jsonparser.parser(strinput);
                     String status_report = obj_fav_vendor_del_jsonparser.arrayList.get(0);
                     Log.w("status report is", status_report + " ");

                     if (status_report == "1") {
                         refresh();
                         Toast.makeText(getActivity(), "Vendor deleted", Toast.LENGTH_SHORT).show();

                     }
                 }
             }

            }

    //if no internet connection
    public void noInternetPage()
    {
        FragmentManager fm= getActivity().getSupportFragmentManager();

        FragmentTransaction ft=fm.beginTransaction();
        noInternetConnection_fragment frgmvendorlist= new noInternetConnection_fragment();
        ft.replace(R.id.mainContent, frgmvendorlist, "VendorList").addToBackStack("abc");
        ft.commit();

    }
}
