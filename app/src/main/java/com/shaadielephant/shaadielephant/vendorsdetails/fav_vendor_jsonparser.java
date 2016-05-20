package com.shaadielephant.shaadielephant.vendorsdetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/3/2016.
 */
public class fav_vendor_jsonparser {

    ArrayList<String> arrayList = null;
    public fav_vendor_jsonparser(){}

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
                    arrayList.add("true");


                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}
