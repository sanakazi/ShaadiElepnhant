package com.shaadielephant.shaadielephant.vendorsdetails;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/7/2016.
 */

public class vendorsdetails_add_fav_img_jsonparser {
    ArrayList<String> arrayList = null;
    public vendorsdetails_add_fav_img_jsonparser(){}

    public ArrayList<String> parser(String strJson) {
        try

        {
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");

         //   if (status == 1)
           // {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<String>();
                arrayList.add(String.valueOf(status));

          //  }
          //  else
          //      arrayList.add(String.valueOf(status));

        } catch (Exception ex) {
        }
        return arrayList;
    }
}