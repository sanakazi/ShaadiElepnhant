package com.shaadielephant.shaadielephant.designerspeakdetails;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Durgesh on 12-01-2016.
 */
public class designerspeakdetailsparser {

    customclassdesignerspeakdetailscombine objcombine=null;
    ArrayList<customclassdesignerspeakdetailsmessage> arrayListmessage=null;
    ArrayList<customclassdesignerspeakdetailsresponse> arrayListresponse=null;
    ArrayList<customclassdesignerspeakdetailscomments> arrayListcomments= null;

    public customclassdesignerspeakdetailscombine parser(String strJson)
    {
        try{
            JSONObject jsonrootObject= new JSONObject(strJson);
            int status= jsonrootObject.getInt("status");
            if(status==1)
            {
                objcombine= new customclassdesignerspeakdetailscombine();
                customclassdesignerspeakdetailsmessage objinnermessage=null;
                customclassdesignerspeakdetailsresponse objinnerresponse=null;
                customclassdesignerspeakdetailscomments objinnercomment=null;


                JSONArray arraymessage= jsonrootObject.getJSONArray("Message");
                if(arraymessage.length()>0) {
                    arrayListmessage= new ArrayList<customclassdesignerspeakdetailsmessage>();
                    for (int m = 0; m < arraymessage.length(); m++) {
                        objinnermessage = new customclassdesignerspeakdetailsmessage();
                        JSONObject innerobject = arraymessage.getJSONObject(m);

                        int num = innerobject.getInt("num");
                        String subject = innerobject.getString("subject");
                        String message = innerobject.getString("message");
                        String memberName = innerobject.getString("memberName");
                        String memberPicture = innerobject.getString("memberPicture");
                        String insertDate = innerobject.getString("insertDate");

                        objinnermessage.setNum(num);
                        objinnermessage.setSubject(subject);
                        objinnermessage.setMessage(message);
                        objinnermessage.setMemberName(memberName);
                        objinnermessage.setMemberPicture(memberPicture);
                        objinnermessage.setInsertDate(insertDate);

                        arrayListmessage.add(objinnermessage);

                    }
                }

                JSONArray arrayresponse= jsonrootObject.getJSONArray("DesignersResponse");
                if(arrayresponse.length()>0) {
                    arrayListresponse= new ArrayList<customclassdesignerspeakdetailsresponse>();
                    for (int r = 0; r < arrayresponse.length(); r++) {
                        objinnerresponse = new customclassdesignerspeakdetailsresponse();
                        JSONObject innerobject = arrayresponse.getJSONObject(r);

                        int num = innerobject.getInt("num");
                        String givencomments = innerobject.getString("givencomments");
                        String userName = innerobject.getString("userName");
                        String userPic = innerobject.getString("userPic");


                        objinnerresponse.setNum(num);
                        objinnerresponse.setGivencomments(givencomments);
                        objinnerresponse.setUserName(userName);
                        objinnerresponse.setUserPic(userPic);

                        arrayListresponse.add(objinnerresponse);

                    }
                }

                JSONArray arraycomment= jsonrootObject.getJSONArray("comments");
                if(arraycomment.length()>0) {
                    arrayListcomments= new ArrayList<customclassdesignerspeakdetailscomments>();
                    for (int c = 0; c < arraycomment.length(); c++) {
                        objinnercomment = new customclassdesignerspeakdetailscomments();
                        JSONObject innerobject = arraycomment.getJSONObject(c);

                        int num = innerobject.getInt("num");
                        String givencomments = innerobject.getString("givencomments");
                        String userName = innerobject.getString("userName");
                        Log.w("username", userName);
                        String userPic = innerobject.getString("userPic");


                        objinnercomment.setNum(num);
                        objinnercomment.setGivencomments(givencomments);
                        objinnercomment.setUserName(userName);
                        objinnercomment.setUserPic(userPic);

                        arrayListcomments.add(objinnercomment);

                    }
                }

                objcombine.setArrayListmessage(arrayListmessage);
                objcombine.setArrayListresponse(arrayListresponse);
                objcombine.setArrayListcomments(arrayListcomments);
            }
        }
        catch (Exception ex)
        {}

        return objcombine;
    }
}
