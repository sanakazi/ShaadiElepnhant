package com.shaadielephant.shaadielephant.vendorslist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class vendorlistjsonparser {
    ArrayList<customclassvendorlist> arrayList=null;
    public vendorlistjsonparser(){

    }
    public ArrayList<customclassvendorlist> parser(String strJson) {


        try

        {
            customclassvendorlist objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");

            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclassvendorlist>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //pass empty values
               /* objrow= new customclassvendorlist();
                objrow.setFullName(" ");
                objrow.setAddress(" ");
                objrow.setNum(0);
                objrow.setStarrating(0);
                objrow.setLocalityName(" ");
                objrow.setThumbPic(" ");
                objrow.setcityName(" ");
                arrayList.add(objrow);
                */

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclassvendorlist();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    String fullName = jsonObject.getString("fullName").toString();
                    objrow.setFullName(fullName);

                    String address = jsonObject.getString("address").toString();
                    objrow.setAddress(address);

                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String phone = jsonObject.getString("phone").toString();
                    objrow.setPhone(phone);
                    int starrating= jsonObject.getInt("starrating");
                    objrow.setStarrating(starrating);

                    String localityName=jsonObject.getString("localityName").toString();
                    objrow.setLocalityName(localityName);

                    String ThumbPic=jsonObject.getString("ThumbPic").toString();
                    objrow.setThumbPic(ThumbPic);

                    String cityName=jsonObject.getString("cityName").toString();
                    objrow.setcityName(cityName);
                    arrayList.add(objrow);
                }
            }

         /*   if (status == 0) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclassvendorlist>();
                Log.w("inside statuts 0", status + " ");
                customclassvendorlist csm= new customclassvendorlist();
                String m1 = jsonRootObject.getString("Message");
                Log.w("Message", m1.toString());
                csm.setFullName("Message");
                arrayList.add(csm);


            }
            */

        } catch (Exception ex) {
        }
        return arrayList;
    }

}
