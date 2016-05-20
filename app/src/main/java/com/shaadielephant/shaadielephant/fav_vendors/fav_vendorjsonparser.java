package com.shaadielephant.shaadielephant.fav_vendors;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class fav_vendorjsonparser {
    ArrayList<customclassfav_vendor_group> arrayListgroup = null;
    ArrayList<customclassfav_vendor_child> arrayListchild = null;

    fav_vendorjsonparser() {}
    public ArrayList<customclassfav_vendor_group> parser(String strJson) {
        try

        {
            Log.w("outside vendor parser", "true");
            customclassfav_vendor_group objrow_group=null;
            customclassfav_vendor_child objrow_child=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayListgroup = new ArrayList<customclassfav_vendor_group>();

                JSONArray jsonArraygroup = jsonRootObject.optJSONArray("Message");
                Log.w("inside vendor parser", "true");
                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArraygroup.length(); i++) {


                    objrow_group= new customclassfav_vendor_group();

                    JSONObject jsonObjectgroup = jsonArraygroup.getJSONObject(i);
                    int categoryID = Integer.parseInt(jsonObjectgroup.getString("categoryID").toString());
                    String categoryName = jsonObjectgroup.getString("fullName").toString();
                    objrow_group.setCategoryName(categoryName);
                    Log.w("cat name is " , categoryName);
                    arrayListchild = new ArrayList<customclassfav_vendor_child>();
                    JSONArray jsonArraychild = jsonObjectgroup.optJSONArray("vendorList");
                    for (int j = 0; j < jsonArraychild.length(); j++) {
                        objrow_child = new customclassfav_vendor_child();
                        JSONObject jsonObjectchild = jsonArraychild.getJSONObject(j);
                       int num = Integer.parseInt(jsonObjectchild.getString("num").toString());
                        objrow_child.setNum(num);
                        String fullName = jsonObjectchild.getString("fullName").toString();
                        objrow_child.setFullName(fullName);
                        Log.w("item  name is ", fullName);

                        String localityName = jsonObjectchild.getString("localityName").toString();
                        objrow_child.setLocalityName(localityName);
                        String cityName = jsonObjectchild.getString("cityName").toString();
                        objrow_child.setCityName(cityName);
                        String phone = jsonObjectchild.getString("phone").toString();
                        objrow_child.setCityName(phone);
                        arrayListchild.add(objrow_child);

                    }
                    objrow_group.setItems(arrayListchild);
                    arrayListgroup.add(objrow_group);

                }
            }
            else{
                arrayListgroup = new ArrayList<customclassfav_vendor_group>();
                objrow_group= new customclassfav_vendor_group();
                String msg = jsonRootObject.optString("Message");
                Log.w("inside vendor parser", "true");
                objrow_group.setCategoryName(msg);
                objrow_child = new customclassfav_vendor_child();
                objrow_child.setFullName("You donot have any favorite vendors");
                arrayListchild.add(objrow_child);
                arrayListgroup.add(objrow_group);

            }

        } catch (Exception ex) {
        }
        return arrayListgroup;
    }
}