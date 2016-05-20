package com.shaadielephant.shaadielephant.vendorsdetails;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class vendordetailsparser {
    customclassvendordetailcombine parseroutput=null;
   ArrayList<customclassvendordetaildata> custclassvendordetaildata=null;

    ArrayList<customclassvendordetailpictures> custclassvendorpicture=null;
    ArrayList<customclassvendordetailcomment> custclassvendorcomment=null;
   public  vendordetailsparser(){

    }
    public customclassvendordetailcombine parser(String strJson) {
        try

        {
            customclassvendordetailcombine objrow=null;
            customclassvendordetaildata objclsdata=null;
            customclassvendordetailpictures objclasspic=null;
            customclassvendordetailcomment objclasscomment=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                parseroutput = new customclassvendordetailcombine();
//                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                 custclassvendordetaildata= new ArrayList<customclassvendordetaildata>();
                JSONArray jsonarrmsg= jsonRootObject.getJSONArray("Message");
                for(int j=0;j<jsonarrmsg.length();j++) {
                    objclsdata= new customclassvendordetaildata();
                 JSONObject objdata= jsonarrmsg.getJSONObject(j);
                    int num = objdata.getInt("num");
                    objclsdata.setNum(num);

                    int productCatID = objdata.getInt("productCatID");
                    objclsdata.setProductCatID(productCatID);


                    String fullName = objdata.getString("fullName");
                    objclsdata.setFullName(fullName);

                    String address = objdata.getString("address");
                    objclsdata.setAddress(address);

                    String phone = objdata.getString("phone");
                    objclsdata.setPhone(phone);

                    int starrating = objdata.getInt("starrating");
                    objclsdata.setStarrating(starrating);

                    String localityName = objdata.getString("localityName");
                    objclsdata.setLocalityName(localityName);

                    String ThumbPic = objdata.getString("ThumbPic");
                    objclsdata.setThumbPic(ThumbPic);
                    String profile=objdata.getString("profile");
                    objclsdata.setProfile(profile);

                    custclassvendordetaildata.add(objclsdata);
                }

                custclassvendorpicture= new ArrayList<customclassvendordetailpictures>();
                JSONArray arrpicture= jsonRootObject.getJSONArray("pictures");
                for(int p=0; p<arrpicture.length();p++)
                {
                    JSONObject objpicture= arrpicture.getJSONObject(p);
                    objclasspic= new customclassvendordetailpictures();
                    String picPath= objpicture.getString("imageurl");
                    objclasspic.setImageurl(picPath);
                    int num= objpicture.getInt("num");
                    objclasspic.setNum(num);

                    custclassvendorpicture.add(objclasspic);
                }
                custclassvendorcomment= new ArrayList<customclassvendordetailcomment>();
                JSONArray arrcomment= jsonRootObject.getJSONArray("comments");
                for(int c=0;c<arrcomment.length();c++)
                {
                    JSONObject objcomment= arrcomment.getJSONObject(c);
                    objclasscomment= new customclassvendordetailcomment();
                    String commentorName= objcomment.getString("userName");
                    objclasscomment.setUserName(commentorName);
                    String givencomments= objcomment.getString("givencomments");
                    objclasscomment.setGivencomments(givencomments);
                    String voterate= objcomment.getString("voterate");
                    objclasscomment.setVoterate(voterate);
                    custclassvendorcomment.add(objclasscomment);
                    Log.w("comments" ,commentorName  + " "+ givencomments + " " + voterate );

                }

                parseroutput.setCustclassvendordetaildata(custclassvendordetaildata);
                parseroutput.setCustclassvendordetailpicture(custclassvendorpicture);
                parseroutput.setCustclassvendordetailcomment(custclassvendorcomment);

            }
        } catch (Exception ex) {
        }
        return parseroutput;
    }
}
