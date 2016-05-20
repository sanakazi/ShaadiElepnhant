package com.shaadielephant.shaadielephant.vendors;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class vendorjsonparser {
    ArrayList<customvendorclass> arrayList=null;
    public vendorjsonparser(){

    }
    public ArrayList<customvendorclass> parser(String strJson) {
        try

        {
            customvendorclass objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customvendorclass>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customvendorclass();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    String productCategoryName = jsonObject.getString("productCategoryName").toString();
                    objrow.setProductCategoryName(productCategoryName);

                    String imageurl = jsonObject.getString("imageurl").toString();
                    objrow.setImageurl(imageurl);

                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String iconurl = jsonObject.getString("iconurl").toString();
                    objrow.setIconurl(iconurl);



                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }

}
