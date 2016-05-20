package com.shaadielephant.shaadielephant.guestlist;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/1/2016.
 */
public class add_invitation_jsonparser  {
    ArrayList<String> arrayList = null;
    public add_invitation_jsonparser(){}

    public ArrayList<String> parser(String strJson)
    {
        try

        {
            Log.w("str", strJson.toString());
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            Log.w("status is ", status + " ");

            if (status != 0) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<String>();
                arrayList.add(String.valueOf(status));
                String jsonArray = jsonRootObject.getString("Message");

            }
        }
        catch (Exception ex) {
        }
        return arrayList;
    }
}
