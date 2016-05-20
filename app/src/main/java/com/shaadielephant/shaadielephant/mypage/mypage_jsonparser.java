package com.shaadielephant.shaadielephant.mypage;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/7/2016.
 */

public class mypage_jsonparser {
    ArrayList<customclass_mypage> arrayList=null;
    public mypage_jsonparser(){

    }
    public ArrayList<customclass_mypage> parser(String strJson) {
        try

        {
            customclass_mypage objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclass_mypage>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_mypage();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                   int num = Integer.parseInt(jsonObject.getString("num").toString());
                    objrow.setNum(num);

                    String name = jsonObject.getString("Name").toString();
                    objrow.setName(name);

                    String mobileNo = jsonObject.getString("MobileNo").toString();
                    objrow.setMobileNo(mobileNo);

                    String gender = jsonObject.getString("gender").toString();
                    objrow.setGender(gender);

                    String userPicture = jsonObject.getString("userPicture").toString();
                    objrow.setUserPicture(userPicture);

                    String emailID = jsonObject.getString("emailID").toString();
                    objrow.setEmailID(emailID);

                    String address = jsonObject.getString("Address").toString();
                    objrow.setAddress(address);

                    String city = jsonObject.getString("city").toString();
                    objrow.setCity(city);

                   String country = jsonObject.getString("country").toString();
                    objrow.setCountry(country);

                    String insertDate = jsonObject.getString("insertDate").toString();
                    objrow.setInsertDate(insertDate);

                    Log.w("name = " , name + " email id = "+ emailID);

                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }

}