package com.shaadielephant.shaadielephant.vendorsdetails;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/15/2016.
 */
public class add_comments_rating_jsonparser {
    public ArrayList<String> arrayList = null;
    public add_comments_rating_jsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {
            Log.w("str", strJson.toString());
            JSONObject jsonRootObject = new JSONObject(strJson);
            arrayList = new ArrayList<String>();
            int status = jsonRootObject.getInt("status");
            arrayList.add(String.valueOf(status));
            String msg = jsonRootObject.getString("Message");
            arrayList.add(String.valueOf(msg));
            Log.w("arraylist " , "status = " + status + " msg= " +msg);


        }
        catch (Exception ex) {
        }
        return arrayList;
    }

}
