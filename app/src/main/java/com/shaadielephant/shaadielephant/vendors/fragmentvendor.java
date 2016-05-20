package com.shaadielephant.shaadielephant.vendors;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import com.shaadielephant.shaadielephant.vendorslist.fragmentvendorlist;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;


public class fragmentvendor extends Fragment implements networkresponse1,networkresponse2,networkresponse3 {
    ListView listViewVendorMain;
    ArrayList<customvendorclass> objArraylist;



    // for dialog_vendorlist_filter

    public Dialog dialog;
    Spinner select_vendortype,select_city,select_locality;
    TextView keyword;
    static ArrayList<customvendorclass> objArraylist2;
    static  ArrayList<customclass_group_city> objArraylist3;
    static  ArrayList<customclass_child_locality> objArraylist4;
    ArrayAdapter<String> select_vendortype_adapter,select_city_adapter,select_locaity_adapter;
    static String[] categories_vendortype , categories_city,categories_locality;
    int cityId,catId,localityId;
    Button search;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewVendorListMain= inflater.inflate(R.layout.fragmentvendor,container,false);
        listViewVendorMain= (ListView) viewVendorListMain.findViewById(R.id.listViewVendorMain);
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Vendor Category");
        ((main_drawer) getActivity()).setbartitle(getActivity(),call.TITLE_LIST.get(call.TITLE_LIST.size()-1));
        asyntask1 objasyntask= new asyntask1(getActivity());
        Log.w("oncreate", " ");
        objasyntask.execute("http://webservices.shaadielephant.com/vendors.asmx/categoryList");
        asyntask1.setListner(fragmentvendor.this);
        listViewVendorMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                customvendorclass csm = objArraylist.get(position);

                FragmentManager frgmgr = getActivity().getSupportFragmentManager();

                FragmentTransaction ftgm = frgmgr.beginTransaction();
                fragmentvendorlist frgmvendorlist = new fragmentvendorlist();

                Bundle bundleVendorList = new Bundle();
                bundleVendorList.putInt("num", csm.getNum());
                bundleVendorList.putString("Title", csm.getProductCategoryName());
                bundleVendorList.putString("ImageHeader", "http://shaadielephant.com/imagesProductImages360/" + csm.getImageurl());

                bundleVendorList.putString("keyword", "0");
                bundleVendorList.putInt("cityId", 0);
                bundleVendorList.putInt("localityId", 0);

                frgmvendorlist.setArguments(bundleVendorList);
                ftgm.add(R.id.mainContent, frgmvendorlist, "VendorList").addToBackStack("abc");
                ftgm.commit();


            }
        });
        setHasOptionsMenu(true);
        return viewVendorListMain;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.item_vendorlist, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.filter_vendorlist) {
            showDialogToFilter();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //popup dialog to filter
    public void showDialogToFilter()
    {

        //show categorylist
        asyntask2 objasyntask= new asyntask2(getActivity());
        objasyntask.execute("http://webservices.shaadielephant.com/vendors.asmx/categoryList");
        asyntask2.setListner(fragmentvendor.this);

        //show city and localitylist
        asyntask3 objasyntask3= new asyntask3(getActivity());
        objasyntask3.execute("http://webservices.shaadielephant.com/mastersList.asmx/CityListWithLocalityArea");
        asyntask3.setListner(fragmentvendor.this);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_vendorlist_filter);
        dialog.setCancelable(false);

        keyword = (TextView)dialog.findViewById(R.id.keyword);
        select_vendortype=(Spinner)dialog.findViewById(R.id.select_vendortype);
        select_city=(Spinner)dialog.findViewById(R.id.select_city);
        select_locality=(Spinner)dialog.findViewById(R.id.select_locality);
        search= (Button)dialog.findViewById(R.id.search);


        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });

       search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                FragmentManager frgmgr = getActivity().getSupportFragmentManager();

                FragmentTransaction ftgm = frgmgr.beginTransaction();
                fragmentvendorlist frgmvendorlist = new fragmentvendorlist();

                Bundle bundleVendorList = new Bundle();
                bundleVendorList.putInt("num", catId);
                bundleVendorList.putString("Title", " ");
                bundleVendorList.putString("ImageHeader", " ");

                bundleVendorList.putString("keyword", keyword.getText().toString());
                bundleVendorList.putInt("cityId", cityId);
                bundleVendorList.putInt("localityId", localityId);

                frgmvendorlist.setArguments(bundleVendorList);
                ftgm.add(R.id.mainContent, frgmvendorlist, "VendorList").addToBackStack("abc");
                ftgm.commit();


            }
        });




        select_vendortype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                if (pos==0)
                { catId=0;}
                else {
                    customvendorclass cat = objArraylist2.get(pos-1);
                    catId = cat.getNum();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customvendorclass cat = objArraylist2.get(0);
                catId = cat.getNum();
            }
        });

        select_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                  @Override
                                                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                      int pos = position;
                                                      if (pos==0)
                                                      {
                                                          categories_locality = new String[1];
                                                          categories_locality[0] = "Select Locality";
                                                          cityId=0;

                                                          // Creating adapter for spinner
                                                          select_locaity_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_locality);
                                                          select_locaity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          select_locality.setAdapter(select_locaity_adapter);
                                                      }
                                                      else {
                                                          customclass_group_city local = objArraylist3.get(pos - 1);
                                                          cityId = local.getNumCity();


                                                          objArraylist4 = new ArrayList<customclass_child_locality>();

                                                          objArraylist4 = objArraylist3.get(pos - 1).getItems();


                                                          Log.d("Locality size is ", objArraylist4.size() + " ");
                                                          categories_locality = new String[(objArraylist4.size()) + 1];
                                                          categories_locality[0] = "Select Locality";

                                                          for (int j = 1; j <= objArraylist4.size(); j++) {
                                                              customclass_child_locality test_child = objArraylist4.get(j - 1);
                                                              categories_locality[j] = test_child.getFullNameLocality();
                                                              Log.d("vendor locality spinner", categories_locality[j] + " ");

                                                          }

                                                          // Creating adapter for spinner
                                                          select_locaity_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_locality);
                                                          select_locaity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          select_locality.setAdapter(select_locaity_adapter);
                                                      }

                                                  }


                                                  @Override
                                                  public void onNothingSelected(AdapterView<?> parent)
                                                  {
                                                      customclass_group_city local = objArraylist3.get(0);
                                                      cityId = 0;
                                                  }
                                              }
        );

        dialog.show();


        select_locality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                if(pos==0)
                {
                    localityId = 0;
                }
                else {
                    customclass_child_locality local = objArraylist4.get(pos-1);
                    localityId = local.getNumLocality();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customclass_child_locality local = objArraylist4.get(0);
                localityId = local.getNumLocality();
            }
        });

    }

    //spinner for vendor list category
    public void filterList_vendorType() {


        if (objArraylist2 != null) {
            categories_vendortype = new String[(objArraylist2.size())+1];

            Log.d("size is ",(objArraylist2.size())+1 + " ");
            categories_vendortype[0] = "Select Vendor Category";
            for (int i = 1; i <= objArraylist2.size(); i++) {
                customvendorclass test = objArraylist2.get(i-1);
                categories_vendortype[i] = test.getProductCategoryName();
                Log.d("vendor full name is ", categories_vendortype[i] + " ");
            }

            // Creating adapter for spinner
            select_vendortype_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_vendortype);
            select_vendortype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            select_vendortype.setAdapter(select_vendortype_adapter);

        }


    }

    //spinner for city and locality

    public void filterList_city()
    {
        if (objArraylist3 != null) {
            categories_city = new String[(objArraylist3.size())+1];
            Log.d("size for city ", objArraylist3.size()+1 + " ");
            categories_city[0]="Select City";

            //logic for expandable listview arraylist

            for (int i = 1; i <= objArraylist3.size(); i++) {
                customclass_group_city test = objArraylist3.get(i-1);
                categories_city[i] = test.getFullNameCity();
                Log.d("vendor city is ", categories_city[i] + " ");

             /*   objArraylist4 = new ArrayList<customclass_child_locality>();

                objArraylist4 = objArraylist3.get(i).getItems();
                Log.d("Locality size is ", objArraylist4.size() + " ");
                categories_locality  = new String[objArraylist4.size()];


                for (int j = 0; j < objArraylist4.size(); j++)
                {
                    customclass_child_locality test_child = objArraylist4.get(j);
                    categories_locality[j]=test_child.getFullNameLocality();
                    Log.d("vendor locality is ", categories_locality[j] + " ");

                }

                */

            }



            // Creating adapter for spinner
            select_city_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_city);
            select_city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            select_city.setAdapter(select_city_adapter);


        }
    }




 // TO display vendor category
    @Override
    public void getResponse1(String strinput) {



            vendorjsonparser objvendorjsonparser = new vendorjsonparser();
            objArraylist = objvendorjsonparser.parser(strinput);
            vendoradapter objvendoradapter = new vendoradapter(getActivity(), objArraylist);
            listViewVendorMain.setAdapter(objvendoradapter);

    }


    //show vendor category List
    @Override
    public void getResponse2(String strinput) {
        vendorjsonparser objvendorjsonparser = new vendorjsonparser();
        objArraylist2 = objvendorjsonparser.parser(strinput);
        filterList_vendorType();
    }

    // show city and locality list
    @Override
    public void getResponse3(String strinput) {
        city_locality_jsonparser ob = new city_locality_jsonparser();
        objArraylist3 = ob.parser(strinput);
        filterList_city();
    }

}
