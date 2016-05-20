package com.shaadielephant.shaadielephant.mydesignerspeaklist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/8/2016.
 */
public class mydesignerspeaklist_jsonparser {


    ArrayList<customclass_mydesignerspeaklist> arrayList = null;
    mydesignerspeaklist_jsonparser(){}

    public ArrayList<customclass_mydesignerspeaklist> parser(String strJson) {
        try

        {
            customclass_mydesignerspeaklist objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {

                arrayList = new ArrayList<customclass_mydesignerspeaklist>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_mydesignerspeaklist();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);
                    String AuthorName = jsonObject.getString("AuthorName").toString();
                    objrow.setAuthorName(AuthorName);
                    Log.w("Invitation AuthorName  ", AuthorName + "  num=  " + num);

                    String brief = jsonObject.getString("brief").toString();
                    objrow.setAuthorName(brief);



                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }


}
