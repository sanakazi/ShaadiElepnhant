package com.shaadielephant.shaadielephant.vendors;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;

import com.shaadielephant.shaadielephant.customProgressDialog;
import com.shaadielephant.shaadielephant.vendors.networkresponse1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class asyntask1 extends AsyncTask<String,Void,String>{
    StringBuffer response;
    Context context;
    //ProgressDialog progressDialog;
    customProgressDialog progressDialog;
    static networkresponse1 objlistner;
     asyntask1(Context c){
        this.context=c;
    }


    public static void setListner(networkresponse1 objnetworkresponse){
        objlistner=objnetworkresponse;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog!=null){
            progressDialog.dismiss();

        }
                if(response!=null)
          objlistner.getResponse1(response.toString());
     //   else objlistner.getResponse1("no");


    }

    @Override
       protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new customProgressDialog(context);
      //  progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        String url=params[0];
        try{
            URL objulr= new URL(url);
            Log.w("objulr", " " + objulr.toString());
            HttpURLConnection httpURLConnection= (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(2000);
            Log.w("httpURLConnection", " " +httpURLConnection.toString());

            int responsecode=httpURLConnection.getResponseCode();

           if (responsecode==200){
                Log.w("inside if", "true ");

                BufferedReader in= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                Log.w("in", " " +in.toString());

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
