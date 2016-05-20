package com.shaadielephant.shaadielephant.fav_vendors;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shaadielephant.shaadielephant.customProgressDialog;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Callndata on 2/3/2016.
 */

public class asyntask1 extends AsyncTask<String,Void,String> {
    StringBuffer response;
    public Context context;
    public static customProgressDialog progressDialog;
    static networkresponse1 objlistner;


    public asyntask1(Context context)
    {this.context=context;}

    public static void setListner(networkresponse1 objnetworkresponse) {
        objlistner = objnetworkresponse;
    }
    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);
         if(progressDialog!=null){
               progressDialog.dismiss();

          }
        if(response!=null)
        objlistner.getResponse1(response.toString());
       // else  objlistner.getResponse1("noInternet");


    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
          progressDialog=new customProgressDialog(context);
          progressDialog.setMessage("Loading....");
          progressDialog.show();

    }
    @Override
    public String doInBackground(String... params) {
        String url=params[0];
        try{
            URL objulr= new URL(url);

            HttpURLConnection httpURLConnection= (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            String urlParameters = params[1];
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responsecode=httpURLConnection.getResponseCode();
            if (responsecode==200){
                BufferedReader in= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response=new StringBuffer();
                while((inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }
                in.close();

            }
        }
        catch (Exception ex)
        {}
        return null;
    }
}