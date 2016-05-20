package com.shaadielephant.shaadielephant.login;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/14/2016.
 */
public class forgot_password_jsonparser {

    ArrayList<String> arrayList = null;
    public forgot_password_jsonparser(){}

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


        }
        catch (Exception ex) {
        }
        return arrayList;
    }

}
