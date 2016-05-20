package com.shaadielephant.shaadielephant.planning_page;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shaadielephant.shaadielephant.budget.fragmentbudget;
import com.shaadielephant.shaadielephant.checklist.fragmentchecklist;
import com.shaadielephant.shaadielephant.fav_vendors.fragmentfav_vendor;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

/**
 * Created by Callndata on 2/19/2016.
 */
public class frg_planning extends Fragment{
    ImageView website,einvites,vendors,checklist,budget;
    SharedPreferences shared;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmentplanning, container, false);
        //For changing actionbar title
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));


        website = (ImageView)v.findViewById(R.id.website);
        einvites = (ImageView)v.findViewById(R.id.einvites);
        vendors = (ImageView)v.findViewById(R.id.vendors);
        checklist= (ImageView)v.findViewById(R.id.checklist);
        budget=(ImageView)v.findViewById(R.id.budget);


        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                frg_webview website= new frg_webview();
                Bundle bundle = new Bundle();
               // bundle.putString("pagename","userWebsite.aspx");
                bundle.putString("pagename","mobileuserWebsite.aspx");
                bundle.putString("pageheader", "Website");
                website.setArguments(bundle);
                ft.add(R.id.mainContent, website, "web").addToBackStack("dfsf");
                ft.commit();

            }
        });

        einvites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                frg_webview einvite= new frg_webview();
                Bundle bundle = new Bundle();
               // bundle.putString("pagename","userEinvite.aspx");
                bundle.putString("pagename","mobileuserEinvite.aspx");
                bundle.putString("pageheader", "Send E-invites");
                einvite.setArguments(bundle);
                ft.add(R.id.mainContent, einvite, "FavVendorDetails").addToBackStack("cd");
                ft.commit();

            }
        });

        vendors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                fragmentfav_vendor favendors= new fragmentfav_vendor();
                ft.add(R.id.mainContent, favendors, "FavVendorDetails").addToBackStack("kj");
                ft.commit();

            }
        });

        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentchecklist fc = new fragmentchecklist();
                ft.add(R.id.mainContent, fc, "dsff").addToBackStack("dss");
                ft.commit();

            }
        });

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("true budget", "true");
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                fragmentbudget fc= new fragmentbudget();
                ft.add(R.id.mainContent, fc, "dsff").addToBackStack("dss");
                ft.commit();

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning ");
        Log.w("log planing resume ", "true");
    }
}
