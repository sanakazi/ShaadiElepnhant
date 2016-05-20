package com.shaadielephant.shaadielephant.budget_item;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 2/18/2016.
 */
public class budget_item_jsonparser {

    ArrayList<String> arrayListdetails = null;
    ArrayList<customclass_budget_item_group> arrayListgroup = null;
    ArrayList<customclass_budget_item_child> arrayListchild = null;

    customclass_budget_item_combine parseroutput=null;
    customclass_budget_details obj_budget_details=null;
    customclass_budget_item_group objrow_group=null;
    customclass_budget_item_child objrow_child=null;


    //ItemList for child
    String itemlist_fullName,itemlist_vendorName,itemlist_vendorPhone,itemlist_vendorOtherRemarks,itemlist_status;
    int itemlist_num,itemlist_plannedCost,itemlist_actualAmount,itemlist_DiffAmount,itemlist_paidAmount,itemlist_pendingAmount,itemlist_vendorID;

    // VendorList for child
    String vendorlist_fullName;
    int vendorlist_num,vendorlist_vendorID;

    // for group
    int categoryID,userCatID,CatplannedCost,CatactualAmount,CatDiffAmount,CatpaidAmount,CatpendingAmount;
    String CatfullName;

    //for budget
    String marriageDate,BudgetfullName;
    int budgetID,TotalBudget,budget_plannedCost,budget_availbleAmount,budget_actualAmount,budget_paidAmount,budget_pendingAmount;


    budget_item_jsonparser() {}
    public customclass_budget_item_combine parser(String strJson) {
        try

        {

            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                parseroutput= new customclass_budget_item_combine();
                arrayListdetails = new ArrayList<String>();
                JSONArray jsonArray = jsonRootObject.optJSONArray("details");

                //for budget details
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                     budgetID= jsonObject.getInt("num");
                    arrayListdetails.add(String.valueOf(budgetID));

                    BudgetfullName= jsonObject.getString("fullName");
                    arrayListdetails.add(BudgetfullName);

                    marriageDate= jsonObject.getString("marriageDate");
                    arrayListdetails.add(BudgetfullName);

                     TotalBudget= jsonObject.getInt("TotalBudget");
                    Log.w("TotalBudget = ", TotalBudget + " ");
                    arrayListdetails.add(String.valueOf(TotalBudget));

                    budget_plannedCost= jsonObject.getInt("plannedCost");
                    arrayListdetails.add(String.valueOf(budget_plannedCost));

                    budget_availbleAmount= jsonObject.getInt("availbleAmount");
                    arrayListdetails.add(String.valueOf(budget_availbleAmount));

                     budget_actualAmount= jsonObject.getInt("actualAmount");
                    arrayListdetails.add(String.valueOf(budget_actualAmount));

                    budget_paidAmount= jsonObject.getInt("paidAmount");
                    arrayListdetails.add(String.valueOf(budget_paidAmount));

                    budget_pendingAmount= jsonObject.getInt("pendingAmount");
                    arrayListdetails.add(String.valueOf(budget_pendingAmount));

                }




                arrayListgroup = new ArrayList<customclass_budget_item_group>();

                JSONArray jsonArraygroup = jsonRootObject.optJSONArray("CategoryList");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArraygroup.length(); i++) {
                    objrow_group= new customclass_budget_item_group();
                    //   objrow_group.setFullNameChecklist(fullNameChecklist);
                    //  objrow_group.setMarriageDate(marriageDate);
                    JSONObject jsonObjectgroup = jsonArraygroup.getJSONObject(i);
                    categoryID = Integer.parseInt(jsonObjectgroup.getString("categoryID").toString());
                    objrow_group.setCategoryID(categoryID);
                    userCatID=Integer.parseInt(jsonObjectgroup.getString("userCatID").toString());
                    objrow_group.setUserCatID(userCatID);
                    CatplannedCost=Integer.parseInt(jsonObjectgroup.getString("plannedCost").toString());
                    objrow_group.setCatplannedCost(CatplannedCost);
                    CatactualAmount = Integer.parseInt(jsonObjectgroup.getString("actualAmount").toString());
                    objrow_group.setCatactualAmount(CatactualAmount);
                    CatDiffAmount=Integer.parseInt(jsonObjectgroup.getString("DiffAmount").toString());
                    objrow_group.setCatDiffAmount(CatDiffAmount);
                    CatpaidAmount=Integer.parseInt(jsonObjectgroup.getString("paidAmount").toString());
                    objrow_group.setCatpaidAmount(CatpaidAmount);
                    CatpendingAmount = Integer.parseInt(jsonObjectgroup.getString("pendingAmount").toString());
                    objrow_group.setCatpendingAmount(CatpendingAmount);
                    CatfullName = jsonObjectgroup.getString("fullName").toString();
                    objrow_group.setCatfullName(CatfullName);

                    Log.w("cat id = ", categoryID + "    CatfullName= " + CatfullName );
                    arrayListchild = new ArrayList<customclass_budget_item_child>();

                    JSONArray jsonArraychild = jsonObjectgroup.optJSONArray("ItemList");
                    arrayListchild.add(objrow_child);

                    for (int j = 0; j < jsonArraychild.length(); j++) {
                        objrow_child = new customclass_budget_item_child();
                        JSONObject jsonObjectchild = jsonArraychild.getJSONObject(j);
                        // for item_list
                        itemlist_num = Integer.parseInt(jsonObjectchild.getString("num").toString());
                        objrow_child.setItemlist_num(itemlist_num);

                        itemlist_fullName = jsonObjectchild.getString("fullName").toString();
                        objrow_child.setItemlist_fullName(itemlist_fullName);


                        itemlist_plannedCost = Integer.parseInt(jsonObjectchild.getString("plannedCost").toString());
                        objrow_child.setItemlist_plannedCost(itemlist_plannedCost);

                        itemlist_actualAmount = Integer.parseInt(jsonObjectchild.getString("actualAmount").toString());
                        objrow_child.setItemlist_actualAmount(itemlist_actualAmount);

                        itemlist_DiffAmount = Integer.parseInt(jsonObjectchild.getString("DiffAmount").toString());
                        objrow_child.setItemlist_DiffAmount(itemlist_DiffAmount);

                        itemlist_paidAmount = Integer.parseInt(jsonObjectchild.getString("paidAmount").toString());
                        objrow_child.setItemlist_paidAmount(itemlist_paidAmount);

                        itemlist_pendingAmount = Integer.parseInt(jsonObjectchild.getString("pendingAmount").toString());
                        objrow_child.setItemlist_pendingAmount(itemlist_pendingAmount);

                        itemlist_vendorID = Integer.parseInt(jsonObjectchild.getString("vendorID").toString());
                        objrow_child.setItemlist_vendorID(itemlist_vendorID);

                        itemlist_vendorName = jsonObjectchild.getString("vendorName").toString();
                        objrow_child.setItemlist_vendorName(itemlist_vendorName);

                        itemlist_vendorPhone = jsonObjectchild.getString("vendorPhone").toString();
                        objrow_child.setItemlist_vendorPhone(itemlist_vendorPhone);

                        itemlist_vendorOtherRemarks = jsonObjectchild.getString("vendorOtherRemarks").toString();
                        objrow_child.setItemlist_vendorOtherRemarks(itemlist_vendorOtherRemarks);

                        itemlist_status = jsonObjectchild.getString("status").toString();
                        objrow_child.setItemlist_status(itemlist_status);


                    //for vendor_list

                        vendorlist_num = Integer.parseInt(jsonObjectchild.getString("num").toString());
                        objrow_child.setVendorlist_num(vendorlist_num);

                        vendorlist_vendorID = Integer.parseInt(jsonObjectchild.getString("vendorID").toString());
                        objrow_child.setVendorlist_num(vendorlist_vendorID);

                        vendorlist_fullName = jsonObjectchild.getString("fullName").toString();
                        objrow_child.setVendorlist_fullName(vendorlist_fullName);

                        Log.w("Item num = ", itemlist_num + " fullName= " + itemlist_fullName + " remarks = " +itemlist_vendorOtherRemarks + " phone = " + itemlist_vendorPhone );


                        arrayListchild.add(objrow_child);

                    }

                            setnullValues();

                    arrayListchild.add(objrow_child);

                    objrow_group.setItems(arrayListchild);
                    arrayListgroup.add(objrow_group);

                }

            }

            else
            {
                arrayListgroup = new ArrayList<customclass_budget_item_group>();
                arrayListchild = new ArrayList<customclass_budget_item_child>();

                for (int j = 0; j < 2; j++) {
                    setnullValues();
                    arrayListchild.add(objrow_child);
                }
                objrow_group.setItems(arrayListchild);
                arrayListgroup.add(objrow_group);
            }
            parseroutput.setBudget_details(arrayListdetails);
            parseroutput.setBudget_item_group(arrayListgroup);

        } catch (Exception ex) {
        }
        return parseroutput;
    }

    public void setnullValues()
    {
        objrow_child = new customclass_budget_item_child();
        //for itemlist

        objrow_child.setItemlist_num(0);
        objrow_child.setItemlist_fullName("");
        // for vendor list
        objrow_child.setVendorlist_num(0);
        objrow_child.setVendorlist_fullName("");
    }



}
