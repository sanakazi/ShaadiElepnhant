package com.shaadielephant.shaadielephant.guestlist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/1/2016.
 */
public class invitation_jsonparser {

    ArrayList<customclass_invitation> arrayList = null;
    invitation_jsonparser(){}

    public ArrayList<customclass_invitation> parser(String strJson) {
        try

        {
            customclass_invitation objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclass_invitation>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_invitation();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);
                    String fullName = jsonObject.getString("fullName").toString();
                    Log.w("Invitation name is ", fullName + "  num=  " + num);
                    objrow.setFullName(fullName);
                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }

}
