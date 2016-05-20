package com.shaadielephant.shaadielephant.fav_vendors;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/3/2016.
 */

public class fav_vendor_del_jsonparser {
    ArrayList<String> arrayList = null;
    public fav_vendor_del_jsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");

            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                Log.w("inside statuts", status + " ");
                arrayList = new ArrayList<String>();
                arrayList.add(String.valueOf(status));
                String jsonArray = jsonRootObject.getString("Message");
                arrayList.add(jsonArray.toString());
                Log.w("del status", status + "Message is " + jsonArray.toString());

            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}