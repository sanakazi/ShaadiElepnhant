package com.shaadielephant.shaadielephant.login;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
/*
public class loginjsonparser {

    ArrayList<customclasslogin> arrayList=null;
    loginjsonparser(){}

    public ArrayList<customclasslogin> parser(String strJson) {
        try{

        customclasslogin objrow=null;
        JSONObject jsonRootObject = new JSONObject(strJson);


            Log.w("json obj",jsonRootObject.toString()+"");
        String status = jsonRootObject.getString("status");
            Log.w("status",status+"");
         //  objrow.setStatus(status);

           // if (status.equals("1"))
           // {
                Log.w("Inside if", " true");
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclasslogin>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.w("Inside for", " true");
                    objrow= new customclasslogin();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    objrow.setNum(num);

                    String Name = jsonObject.getString("Name").toString();
                    objrow.setName(Name);
                    Log.w("name=", Name + " ");
                    String MobileNo = jsonObject.getString("MobileNo").toString();
                    objrow.setMobileNo(MobileNo);

                    String gender = jsonObject.getString("gender").toString();
                    objrow.setGender(gender);

                    String userPicture = jsonObject.getString("userPicture").toString();
                     objrow.setUserPicture(userPicture);

                    arrayList.add(objrow);
                }


       // }

        }
        catch (Exception ex) {}
        return arrayList;
    }

}*/


public class loginjsonparser {

    ArrayList<String> arrayList = null;

    loginjsonparser() {
    }

    public ArrayList<String> parser(String strJson) {
        try {

            JSONObject jsonRootObject = new JSONObject(strJson);
            arrayList = new ArrayList<String>();

            String status = jsonRootObject.getString("status");
            arrayList.add(status);

            if(status.compareTo("1")==0)
            {
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    arrayList.add(String.valueOf(num));
                    String Name = jsonObject.getString("Name").toString();
                    arrayList.add(Name);
                    String MobileNo = jsonObject.getString("MobileNo").toString();
                    arrayList.add(MobileNo);

                    String gender = jsonObject.getString("gender").toString();
                    arrayList.add(gender);

                    String userPicture = jsonObject.getString("userPicture").toString();
                    arrayList.add(userPicture);

                    String emailID = jsonObject.getString("emailID").toString();
                    arrayList.add(emailID);

                }
            }

            else
            {

                String message = jsonRootObject.getString("Message").toString();
                arrayList.add(message);
                Log.w("Message is " , message);
            }



        } catch (Exception ex) {
        }
        return arrayList;
    }
}




