package com.shaadielephant.shaadielephant.checklist_item;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/4/2016.
 */


public class checklist_item_jsonparser {
    int numItem,daysCompleteBeforeEvents;
    String fullNameItem,statusItem,dateofCompletion;
    ArrayList<customclass_checklist_item_group> arrayListgroup = null;
    ArrayList<customclass_checklist_item_child> arrayListchild = null;
    customclass_checklist_item_group objrow_group=null;
    customclass_checklist_item_child objrow_child=null;

    checklist_item_jsonparser() {}
    public ArrayList<customclass_checklist_item_group> parser(String strJson) {
        try

        {

            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayListgroup = new ArrayList<customclass_checklist_item_group>();

                JSONArray jsonArraygroup1 = jsonRootObject.optJSONArray("details");
                JSONObject jsonObjectgroup1 = jsonArraygroup1.getJSONObject(0);
                int numChecklist = Integer.parseInt(jsonObjectgroup1.getString("num").toString());
                String fullNameChecklist= jsonObjectgroup1.getString("fullName").toString();
               // String marriageDate= jsonObjectgroup1.getString("marriageDate").toString();




                JSONArray jsonArraygroup = jsonRootObject.optJSONArray("CategoryList");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArraygroup.length(); i++) {
                    objrow_group= new customclass_checklist_item_group();
                    objrow_group.setNumChecklist(numChecklist);
                    objrow_group.setFullNameChecklist(fullNameChecklist);
                  //  objrow_group.setMarriageDate(marriageDate);
                    JSONObject jsonObjectgroup = jsonArraygroup.getJSONObject(i);
                    int categoryID = Integer.parseInt(jsonObjectgroup.getString("categoryID").toString());
                    objrow_group.setCategoryID(categoryID);
                    int userEventCatID=Integer.parseInt(jsonObjectgroup.getString("userEventCatID").toString());
                    objrow_group.setUserEventCatID(userEventCatID);
                    String categoryName = jsonObjectgroup.getString("fullName").toString();
                    objrow_group.setFullNameCategory(categoryName);
                    Log.w("cat id = ", categoryID + "    userEventCatID= " +userEventCatID + "    cat name= " +categoryName);


                    arrayListchild = new ArrayList<customclass_checklist_item_child>();
                    JSONArray jsonArraychild = jsonObjectgroup.optJSONArray("ItemList");

                    setnullValues();

                    for (int j = 0; j < jsonArraychild.length(); j++) {
                        objrow_child = new customclass_checklist_item_child();
                        JSONObject jsonObjectchild = jsonArraychild.getJSONObject(j);
                         numItem = Integer.parseInt(jsonObjectchild.getString("num").toString());
                        objrow_child.setNumItem(numItem);
                         daysCompleteBeforeEvents = Integer.parseInt(jsonObjectchild.getString("daysCompleteBeforeEvents").toString());
                        objrow_child.setDaysCompleteBeforeEvents(daysCompleteBeforeEvents);

                         fullNameItem = jsonObjectchild.getString("fullName").toString();
                        objrow_child.setFullNameItem(fullNameItem);

                         statusItem = jsonObjectchild.getString("status").toString();
                        objrow_child.setStatusItem(statusItem);
                        Log.w("numItem = ", numItem + " , " + "fullNameItem = " + fullNameItem + "  Status =  " +statusItem );
                        dateofCompletion = jsonObjectchild.getString("dateofCompletion").toString();
                        objrow_child.setDateofCompletion(dateofCompletion);
                        arrayListchild.add(objrow_child);

                    }


                    setnullValues();

                    objrow_group.setItems(arrayListchild);
                    arrayListgroup.add(objrow_group);

                }
            }

            else
            {
                arrayListgroup = new ArrayList<customclass_checklist_item_group>();
                arrayListchild = new ArrayList<customclass_checklist_item_child>();

                for (int j = 0; j < 2; j++) {
                    setnullValues();
                }
                objrow_group.setItems(arrayListchild);
                arrayListgroup.add(objrow_group);
            }

        } catch (Exception ex) {
        }
        return arrayListgroup;
    }

    public void setnullValues()
    {
        objrow_child = new customclass_checklist_item_child();
        objrow_child.setNumItem(0);
        objrow_child.setDaysCompleteBeforeEvents(0);
        objrow_child.setFullNameItem("");
        objrow_child.setStatusItem("");
        objrow_child.setDateofCompletion("");
        arrayListchild.add(objrow_child);
    }

}