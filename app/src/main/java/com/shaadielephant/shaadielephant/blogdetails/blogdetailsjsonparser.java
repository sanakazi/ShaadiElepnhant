package com.shaadielephant.shaadielephant.blogdetails;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class blogdetailsjsonparser
{
    customclassblogdetailscombine parseroutput=null;
    ArrayList<customclassblogdetails> arrayList=null;
    ArrayList<customclassblogdetailscomment> customclassblogdetailscomment=null;

        blogdetailsjsonparser(){

    }

    public customclassblogdetailscombine parser(String strJson) {
        try

        {
            customclassblogdetailscombine objrowcombine =null;
            customclassblogdetails objrow=null;
            customclassblogdetailscomment objclasscomment = null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                parseroutput = new customclassblogdetailscombine();
                arrayList = new ArrayList<customclassblogdetails>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclassblogdetails();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String heading = jsonObject.getString("heading").toString();
                    objrow.setHeading(heading);

                    String imageurl = jsonObject.getString("imageurl").toString();
                    objrow.setImageurl(imageurl);

                    String brief = jsonObject.getString("brief").toString();
                    objrow.setBrief(brief);

                    String insertdate = jsonObject.getString("insertdate").toString();
                    objrow.setInsertdate(insertdate);

                    String insertdescr = jsonObject.getString("description").toString();
                    objrow.setDescription(insertdescr);
                    arrayList.add(objrow);
                }

                customclassblogdetailscomment= new ArrayList< customclassblogdetailscomment>();
                JSONArray arrcomment= jsonRootObject.getJSONArray("comments");
                for(int c=0;c<arrcomment.length();c++)
                {
                    JSONObject objcomment= arrcomment.getJSONObject(c);
                    objclasscomment= new customclassblogdetailscomment();
                    String commentorName= objcomment.getString("userName");
                    objclasscomment.setUserName(commentorName);
                    String givencomments= objcomment.getString("givencomments");
                    objclasscomment.setGivencomments(givencomments);
                    customclassblogdetailscomment.add(objclasscomment);

                }
                parseroutput.setCustomclassblogdetails(arrayList);
                parseroutput.setCustomclassblogdetailscomment(customclassblogdetailscomment);
            }
        } catch (Exception ex) {
        }
        return parseroutput;
    }
}
