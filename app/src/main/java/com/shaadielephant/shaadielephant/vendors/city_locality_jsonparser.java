package com.shaadielephant.shaadielephant.vendors;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/10/2016.
 */
public class city_locality_jsonparser {

    ArrayList<customclass_group_city> arrayListgroup = null;
    ArrayList<customclass_child_locality> arrayListchild = null;

    city_locality_jsonparser() {}
    public ArrayList<customclass_group_city> parser(String strJson) {
        try

        {

            customclass_group_city objrow_group=null;
            customclass_child_locality objrow_child=null;
            JSONObject jsonRootObject = new JSONObject(strJson);
            int status = jsonRootObject.getInt("status");
            if (status == 1) {
                //Get the instance of JSONArray that contains JSONObjects
                arrayListgroup = new ArrayList<customclass_group_city>();

                JSONArray jsonArraygroup = jsonRootObject.optJSONArray("Message");

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArraygroup.length(); i++) {


                    objrow_group= new customclass_group_city();

                    JSONObject jsonObjectgroup = jsonArraygroup.getJSONObject(i);
                    int cityNum = Integer.parseInt(jsonObjectgroup.getString("num").toString());
                    objrow_group.setNumCity(cityNum);
                    String cityName = jsonObjectgroup.getString("fullName").toString();
                    objrow_group.setFullNameCity(cityName);
                    Log.w("num and name of city " , cityNum + " " + cityName);
                    arrayListchild = new ArrayList<customclass_child_locality>();
                    JSONArray jsonArraychild = jsonObjectgroup.optJSONArray("AreaList");
                    for (int j = 0; j < jsonArraychild.length(); j++) {
                        objrow_child = new customclass_child_locality();
                        JSONObject jsonObjectchild = jsonArraychild.getJSONObject(j);
                        int numLocality = Integer.parseInt(jsonObjectchild.getString("num").toString());
                        objrow_child.setNumLocality(numLocality);
                        int cityId = Integer.parseInt(jsonObjectchild.getString("cityID").toString());
                        objrow_child.setCityID(cityId);
                        String fullNameLocality = jsonObjectchild.getString("fullName").toString();
                        objrow_child.setFullNameLocality(fullNameLocality);
                        Log.w("Locality  name is ", fullNameLocality);

                        arrayListchild.add(objrow_child);

                    }
                    objrow_group.setItems(arrayListchild);
                    arrayListgroup.add(objrow_group);

                }
            }
        } catch (Exception ex) {
        }
        return arrayListgroup;
    }
}
