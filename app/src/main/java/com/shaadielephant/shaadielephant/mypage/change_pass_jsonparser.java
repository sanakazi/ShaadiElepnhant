package com.shaadielephant.shaadielephant.mypage;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/9/2016.
 */
public class change_pass_jsonparser
{
    ArrayList<String> arrayList = null;
    public change_pass_jsonparser(){}

    public ArrayList<String> parser(String strJson)
    {
        try

        {
            Log.w("str", strJson.toString());
            JSONObject jsonRootObject = new JSONObject(strJson);

            arrayList = new ArrayList<String>();
            String status = jsonRootObject.getString("status");
            arrayList.add(String.valueOf(status));

            String message = jsonRootObject.getString("Message");
            arrayList.add(String.valueOf(message));

            Log.w("status is ", status + " msg is " + message);


        }
        catch (Exception ex) {
        }
        return arrayList;
    }
}