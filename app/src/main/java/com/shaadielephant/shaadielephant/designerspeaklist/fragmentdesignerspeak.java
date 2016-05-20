package com.shaadielephant.shaadielephant.designerspeaklist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielephant.noconnection_Activity;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.designerspeakdetails.fragmentdesignerspeakdetails;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;


public class fragmentdesignerspeak extends Fragment implements networkresponse1 {
    ListView listViewdesignerspeak;
    ArrayList<customclassdesignerspeaklist> objArraylist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentdesignerspeaklist,container,false);
        listViewdesignerspeak= (ListView) v.findViewById(R.id.listviewDesignerSpeack);
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Designer Speak");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        LinearLayout l1 = (LinearLayout)v.findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment fragmentdesignerspeaklist l1");
            }
        });

        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentdesignerspeak.this);
        objasyntask.execute("http://webservices.shaadielephant.com/DesignerSpeak.asmx/latest50askADesignerList");

        listViewdesignerspeak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customclassdesignerspeaklist objclass= objArraylist.get(position);
               int index= objclass.getNum();
                 FragmentManager FM= getActivity().getSupportFragmentManager();
                FragmentTransaction FT= FM.beginTransaction();
                fragmentdesignerspeakdetails fragdesignerspeakdetails= new fragmentdesignerspeakdetails();
                Bundle bundledesignerspeak= new Bundle();
                bundledesignerspeak.putInt("num",index);
                fragdesignerspeakdetails.setArguments(bundledesignerspeak);
                FT.add(R.id.mainContent,fragdesignerspeakdetails,"DesignerSpeakDetails").addToBackStack("efg");
                FT.commit();

            }
        });
        return v;
    }

    @Override
    public void getResponse1(String strinput) {

            designerspeaklistparser objdesignerjsonparser = new designerspeaklistparser();
            objArraylist = objdesignerjsonparser.parser(strinput);
            designerspeaklistadapter objvendoradapter = new designerspeaklistadapter(getActivity(), objArraylist);
            listViewdesignerspeak.setAdapter(objvendoradapter);


    }
}
