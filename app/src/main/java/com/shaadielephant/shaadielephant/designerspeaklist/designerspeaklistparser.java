package com.shaadielephant.shaadielephant.designerspeaklist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class designerspeaklistparser {
    ArrayList<customclassdesignerspeaklist> arrayList=null;

    public  ArrayList<customclassdesignerspeaklist> parser(String input){

        try{

            customclassdesignerspeaklist objrow=null;
            JSONObject jsonRootObject= new JSONObject(input);
            int status= jsonRootObject.getInt("status");
            if(status==1)
            {
                arrayList= new ArrayList<customclassdesignerspeaklist>();
                JSONArray jsonArray= jsonRootObject.getJSONArray("Message");
                for(int i=0;i<jsonArray.length();i++){
                    objrow= new customclassdesignerspeaklist();
                    JSONObject jsonObject= jsonArray.getJSONObject(i);

                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String subject= jsonObject.getString("subject");
                    objrow.setSubject(subject);

                    String message= jsonObject.getString("message");
                    objrow.setMessage(message);

                    String memberName= jsonObject.getString("memberName");
                    objrow.setMemberName(memberName);

                    String memberPicture = jsonObject.getString("memberPicture");
                    objrow.setMemberPicture(memberPicture);

                    String insertDate= jsonObject.getString("insertDate");
                    objrow.setInsertDate(insertDate);

                    arrayList.add(objrow);
                }
            }
        }
        catch (Exception ex)
        {}
        return arrayList;

    }
}
