package com.shaadielephant.shaadielephant.vendorslist;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.vendors.customvendorclass;
import com.shaadielephant.shaadielephant.vendors.vendorjsonparser;
import com.shaadielephant.shaadielephant.vendorsdetails.frgmvendordetailsscroll;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;


public class fragmentvendorlist extends Fragment implements networkresponse1,networkresponse4 {
    ListView objlistViewVenderlist;
    ArrayList<customclassvendorlist> objArraylist1;

    Button btnrecent,btnall;
    String headerImage;
    LinearLayout l2,l1;

    int pass_loc_id,pass_city_id;
    String pass_keyword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentvendorlist,container,false);
        objlistViewVenderlist= (ListView) v.findViewById(R.id.listViewVenderlist);
        btnrecent= (Button) v.findViewById(R.id.imageViewRecent);
        btnall= (Button) v.findViewById(R.id.imageViewAll);
        l1 = (LinearLayout)v.findViewById(R.id.l1);
        l2 = (LinearLayout)v.findViewById(R.id.l2);

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment vendorlist l2");
            }
        });

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment vendorlist l1");
            }
        });
        //For changing actionbar title
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Vendor Lists");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        btnrecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnrecent.setBackgroundResource(R.drawable.button_rounded_popular_pink);
                btnall.setBackgroundResource(R.drawable.button_rounded_all_black);

                Bundle inputargs = getArguments();
                int catid = inputargs.getInt("num");
                headerImage = inputargs.getString("ImageHeader");

                //for filtered results

                asyntask1 objasyntask = new asyntask1(getActivity());
                asyntask1.setListner(fragmentvendorlist.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/vendors.asmx/vendorList", "catID=" + catid, "ListType=" + "Popular"};
                objasyntask.execute(strinputarray);


            }
        });

        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        // Update your UI here.
                        Log.w("Vendor List","backstack");
                    }
                });


        btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnrecent.setBackgroundResource(R.drawable.button_rounded_popular_black);
                btnall.setBackgroundResource(R.drawable.button_rounded_all_pink);

                asyntask1 objasyntask = new asyntask1(getActivity());
                asyntask1.setListner(fragmentvendorlist.this);
                Bundle inputargs = getArguments();
                int catid = inputargs.getInt("num");
                headerImage = inputargs.getString("ImageHeader");
                String[] strinputarray = {"http://webservices.shaadielephant.com/vendors.asmx/vendorList", "catID=" + catid, "ListType=" + "all"};
                objasyntask.execute(strinputarray);


            }
        });


      // show vendor list

        Bundle inputargs= getArguments();
        final int catid=inputargs.getInt("num");
        Log.w("vendor cat id = ", catid + " ");
        String strtitle= inputargs.getString("Title");
        headerImage=inputargs.getString("ImageHeader");
        pass_loc_id=inputargs.getInt("localityId");
        pass_city_id=inputargs.getInt("cityId");
        pass_keyword=inputargs.getString("keyword");


        if(pass_keyword.compareTo("0")==0) {
            //show filtered vendor list without any flter

            asyntask1 objasyntask = new asyntask1(getActivity());
            asyntask1.setListner(fragmentvendorlist.this);
            String[] strinputarray = {"http://webservices.shaadielephant.com/vendors.asmx/vendorList", "catID=" + catid, "ListType=" + "all"};
            objasyntask.execute(strinputarray);
        }
        else
        {
            //show filtered vendor list
            asyntask4 objasyntask4 = new asyntask4(getActivity());
            asyntask4.setListner(fragmentvendorlist.this);
            String[] strinputarray = {"http://webservices.shaadielephant.com/vendors.asmx/vendorFilter", "catID="+catid,"keyword="+pass_keyword,
                    "cityID="+pass_city_id,"localityID="+pass_loc_id};
            objasyntask4.execute(strinputarray);

            Log.w("onsearch click ", "catID=" + catid + "keyword= " + pass_keyword + "cityID=" + pass_city_id + "localityID=" + pass_loc_id);
        }



        objlistViewVenderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                customclassvendorlist csm = objArraylist1.get(position);
                FragmentManager frgmgr = getActivity().getSupportFragmentManager();

                FragmentTransaction ftgm = frgmgr.beginTransaction();
                frgmvendordetailsscroll frgmvendordetails = new frgmvendordetailsscroll();
                Bundle bundleVendorList = new Bundle();
                bundleVendorList.putInt("vendorid", csm.getNum());
                bundleVendorList.putString("ImageHeader", headerImage);
                frgmvendordetails.setArguments(bundleVendorList);
                ftgm.add(R.id.mainContent, frgmvendordetails, "VendorListDetails").addToBackStack("xyz");
                ftgm.commit();


            }
        });



        return v;
    }



    // to show vendor list without any filter
    @Override
    public void getResponse1(String strinput) {

        vendorlistjsonparser objvendorlisVendorlistjsonparser = new vendorlistjsonparser();
        objArraylist1= objvendorlisVendorlistjsonparser.parser(strinput);
        if(objArraylist1!=null) {
            vendorlistadapter objvendoradapter = new vendorlistadapter(getActivity(), objArraylist1);
            objlistViewVenderlist.setAdapter(objvendoradapter);
        }
    }


 // show vendor list with filter
    @Override
    public void getResponse4(String strinput) {
        vendorlistjsonparser objvendorlisVendorlistjsonparser = new vendorlistjsonparser();
        objArraylist1= objvendorlisVendorlistjsonparser.parser(strinput);
       if(objArraylist1!=null)
       {
           vendorlistadapter objvendoradapter = new vendorlistadapter(getActivity(), objArraylist1);
           objlistViewVenderlist.setAdapter(objvendoradapter);
       }
        else
       {
           Toast.makeText(getActivity(),"No search results found ", Toast.LENGTH_SHORT).show();
       }

    }
}
