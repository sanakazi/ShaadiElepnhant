package com.shaadielephant.shaadielephant.budget;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/1/2016.
 */
public class budgetaddjsonparser {
    ArrayList<String> arrayList = null;
    budgetaddjsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {

            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<String>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");
            Log.w("Message", jsonArray.toString());
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

