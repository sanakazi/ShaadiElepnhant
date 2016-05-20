package com.shaadielephant.shaadielephant.fav_vendors;

import android.util.Log;

import com.shaadielephant.shaadielephant.vendors.customvendorclass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/2/2016.
 */
public class fav_vendor_add_category_jsonparser {
    ArrayList<String> arrayList = null;
    public fav_vendor_add_category_jsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<String>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String productCategoryName = jsonObject.getString("productCategoryName").toString();
                    String num = jsonObject.getString("num").toString();
                    arrayList.add(num);
                    arrayList.add(productCategoryName);

                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}
