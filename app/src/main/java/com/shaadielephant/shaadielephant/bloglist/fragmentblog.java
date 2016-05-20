package com.shaadielephant.shaadielephant.bloglist;

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
import android.widget.ListView;

import com.shaadielephant.shaadielephant.noconnection_Activity;
import com.shaadielephant.shaadielepnhant.R;

import com.shaadielephant.shaadielephant.blogdetails.fragmentblogdetails;
import com.shaadielephant.shaadielephant.main_drawer;


import java.util.ArrayList;


public class fragmentblog extends Fragment implements networkresponse1 {
    ListView listViewBlogMain;
    ArrayList<customblogclass> objArraylist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewBlogListMain= inflater.inflate(R.layout.fragmentblog,container,false);
        listViewBlogMain= (ListView) viewBlogListMain.findViewById(R.id.listViewBlogMain);
        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentblog.this);
        objasyntask.execute("http://webservices.shaadielephant.com/blog.asmx/latest50BlogList");

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Blog");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));


        listViewBlogMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

             customblogclass csmb= objArraylist.get(position);
              int index = csmb.getNum();
                FragmentManager frgmgr= getActivity().getSupportFragmentManager();
                FragmentTransaction ftgm=frgmgr.beginTransaction();
                fragmentblogdetails fragmentblogdetails= new fragmentblogdetails();
                Bundle bundleBlogList= new Bundle();
                bundleBlogList.putInt("num",index);
                fragmentblogdetails.setArguments(bundleBlogList);
                ftgm.add(R.id.mainContent, fragmentblogdetails, "BlogListDetails").addToBackStack("lmn");
                ftgm.commit();



            }
        });

        return viewBlogListMain;
    }

    @Override
    public void getResponse1(String strinput) {
        if(strinput=="no")
        {
            Log.w("strinput true no", " ");

            Intent i =  new Intent(getActivity(),noconnection_Activity.class);
            startActivity(i);

        }
        else {

            blogjsonparser objblogjsonparser = new blogjsonparser();
            objArraylist = objblogjsonparser.parser(strinput);
            blogadapter objblogadapter = new blogadapter(getActivity(), objArraylist);
            listViewBlogMain.setAdapter(objblogadapter);
        }
    }
}
