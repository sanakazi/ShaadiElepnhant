package com.shaadielephant.shaadielephant.guestlist;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/1/2016.
 */

public class del_jsonparser {
    ArrayList<String> arrayList = null;
    public del_jsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");

            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<String>();
                arrayList.add(String.valueOf(status));
                String jsonArray = jsonRootObject.getString("Message");

            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}