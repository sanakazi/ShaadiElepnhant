package com.shaadielephant.shaadielephant.budget;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 1/29/2016.
 */
public class budgetjsonparser {
    ArrayList<customclassbudget> arrayList = null;
    public budgetjsonparser(){}

    public ArrayList<customclassbudget> parser(String strJson) {
        try

        {
            customclassbudget objrow=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayList = new ArrayList<customclassbudget>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    objrow= new customclassbudget();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    int num = Integer.parseInt(jsonObject.getString("num").toString());
                    int num= jsonObject.getInt("num");
                    objrow.setNum(num);

                    String fullName = jsonObject.getString("fullName").toString();
                    objrow.setFullName(fullName);
                    String marriageDate = jsonObject.getString("marriageDate").toString();
                    objrow.setMarriageDate(marriageDate);
                    int TotalBudget = jsonObject.getInt("TotalBudget");
                    Log.w("Budget id = ", num + "  full name=  " + fullName + " TotalBudget= "+ TotalBudget);
                    objrow.setTotalBudget(TotalBudget);
                    int plannedCost = jsonObject.getInt("plannedCost");
                    objrow.setPlannedCost(plannedCost);
                    int availbleAmount = jsonObject.getInt("availbleAmount");
                    objrow.setAvailbleAmount(availbleAmount);
                    int actualAmount = jsonObject.getInt("actualAmount");
                    objrow.setActualAmount(actualAmount);
                    int paidAmount = jsonObject.getInt("paidAmount");
                    objrow.setPaidAmount(paidAmount);
                    int pendingAmount = jsonObject.getInt("pendingAmount");
                    objrow.setPendingAmount(pendingAmount);

                    arrayList.add(objrow);
                }
            }
        } catch (Exception ex) {
        }
        return arrayList;
    }
}
