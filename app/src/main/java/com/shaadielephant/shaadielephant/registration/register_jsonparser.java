package com.shaadielephant.shaadielephant.registration;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/8/2016.
 */

public class register_jsonparser {
    ArrayList<customclass_register> arrayList=null;
    public register_jsonparser(){

    }
    public ArrayList<customclass_register> parser(String strJson) {
        try

        {
            customclass_register objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclass_register>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_register();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    objrow.setStatus(status);
                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    objrow.setNum(num);

                    String name = jsonObject.getString("Name").toString();
                    objrow.setName(name);

                    String mobileNo = jsonObject.getString("MobileNo").toString();
                    objrow.setMobileNo(mobileNo);

                    String gender = jsonObject.getString("gender").toString();
                    objrow.setGender(gender);

                    String userPicture = jsonObject.getString("userPicture").toString();
                    objrow.setUserpicture(userPicture);

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

                    Log.w("name = ", name + " email id = " + emailID);

                    arrayList.add(objrow);
                }
            }
            else if (status==0)
            {
                arrayList = new ArrayList<customclass_register>();
                String message = jsonRootObject.getString("Message").toString();
                objrow= new customclass_register();
                objrow.setStatus(status);
                objrow.setMsg(message);
                arrayList.add(objrow);
            }


        } catch (Exception ex) {
        }
        return arrayList;
    }

}