package com.shaadielephant.shaadielephant.budget_item;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/26/2016.
 */
public class budget_item_categorywise_vendor_jsonparser {
    ArrayList<customclass_categorywise_vendor> arrayList=null;
    public budget_item_categorywise_vendor_jsonparser(){

    }
    public ArrayList<customclass_categorywise_vendor> parser(String strJson) {
        try

        {
            customclass_categorywise_vendor objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclass_categorywise_vendor>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("details");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_categorywise_vendor();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    int vendorID = jsonObject.getInt("vendorID");
                    objrow.setVendorID(vendorID);

                    int startprice = jsonObject.getInt("startprice");
                    objrow.setStartprice(startprice);

                    String fullName = jsonObject.getString("fullName").toString();
                    Log.w("vendor full name ",fullName );
                    objrow.setFullName(fullName);

                    String address = jsonObject.getString("address").toString();
                    objrow.setAddress(address);

                    String phone = jsonObject.getString("phone").toString();
                    objrow.setPhone(phone);

                    String CategoryName = jsonObject.getString("CategoryName").toString();
                    objrow.setCategoryName(CategoryName);

                    String localityName = jsonObject.getString("localityName").toString();
                    objrow.setLocalityName(localityName);

                    String cityName = jsonObject.getString("cityName").toString();
                    objrow.setCityName(cityName);




                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}
