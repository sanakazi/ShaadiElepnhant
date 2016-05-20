package com.shaadielephant.shaadielephant.checklist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 1/29/2016.
 */
public class checklistjsonparser {
    ArrayList<customclasschecklist> arrayList = null;
    checklistjsonparser(){}

    public ArrayList<customclasschecklist> parser(String strJson) {
        try

        {
            customclasschecklist objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclasschecklist>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclasschecklist();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);
                    String fullName = jsonObject.getString("fullName").toString();
                    objrow.setFullName(fullName);
                    String marriageDate = jsonObject.getString("marriageDate").toString();
                    objrow.setMarriageDate(marriageDate);

                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }

}
