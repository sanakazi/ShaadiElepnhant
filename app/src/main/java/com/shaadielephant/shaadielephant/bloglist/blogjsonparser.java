package com.shaadielephant.shaadielephant.bloglist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class blogjsonparser
{

    ArrayList<customblogclass> arrayList=null;
    blogjsonparser(){

    }

    public ArrayList<customblogclass> parser(String strJson) {
        try

        {
            customblogclass objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customblogclass>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customblogclass();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String heading = jsonObject.getString("heading").toString();
                    objrow.setTxtheading(heading);

                    String imageurl = jsonObject.getString("imageurl").toString();
                    objrow.setImageurl(imageurl);

                    String brief = jsonObject.getString("brief").toString();
                    objrow.setTxtbrief(brief);

                    String insertdate = jsonObject.getString("insertdate").toString();
                    objrow.setTxtinsertdate(insertdate);

                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}
