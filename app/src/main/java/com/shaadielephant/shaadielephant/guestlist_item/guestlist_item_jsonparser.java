package com.shaadielephant.shaadielephant.guestlist_item;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/2/2016.
 */
public class guestlist_item_jsonparser {


    ArrayList<customclass_guestlist_item> arrayList = null;
    guestlist_item_jsonparser(){}

    public ArrayList<customclass_guestlist_item> parser(String strJson) {
        try

        {
            customclass_guestlist_item objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclass_guestlist_item>();

                JSONArray jsonArray1 = jsonRootObject.optJSONArray("details");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    objrow= new customclass_guestlist_item();
                    JSONObject jsonObject = jsonArray1.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int numInvitation= jsonObject.getInt("num");
                    objrow.setNumInvitation(numInvitation);
                    String fullNameInvitation = jsonObject.getString("fullName").toString();
                    objrow.setFullNameInvitation(fullNameInvitation);
                    Log.w("Invitation is ", fullNameInvitation + "  num=  " + numInvitation);

                }



                JSONArray jsonArray = jsonRootObject.optJSONArray("InviteeList");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclass_guestlist_item();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);
                    int invitationID= jsonObject.getInt("invitationID");
                    objrow.setInvitationID(invitationID);
                    String fullName = jsonObject.getString("fullName").toString();
                    objrow.setFullName(fullName);
                    Log.w("Guest name name is ", fullName + "  num=  " + num + " invitationId= " + invitationID);
                    String contactNo = jsonObject.getString("contactNo").toString();
                    objrow.setContactNo(contactNo);
                    String emailID = jsonObject.getString("emailID").toString();
                    objrow.setEmailID(emailID);

                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }

}
