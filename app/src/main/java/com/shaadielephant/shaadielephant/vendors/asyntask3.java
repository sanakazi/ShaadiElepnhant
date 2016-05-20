package com.shaadielephant.shaadielephant.vendors;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shaadielephant.shaadielephant.customProgressDialog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Callndata on 3/10/2016.
 */
public class asyntask3 extends AsyncTask<String,Void,String> {
    StringBuffer response;
    Context context;
    //ProgressDialog progressDialog;
    customProgressDialog progressDialog;
    static networkresponse3 objlistner;
    asyntask3(Context c){
        this.context=c;
    }


    public static void setListner(networkresponse3 objnetworkresponse){
        objlistner=objnetworkresponse;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog!=null){
            progressDialog.dismiss();

        }
        if(response!=null)
            objlistner.getResponse3(response.toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new customProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        String url=params[0];
        try{
            URL objulr= new URL(url);
            Log.w("objulr 3 ", " " + objulr.toString());
            HttpURLConnection httpURLConnection= (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(2000);

            int responsecode=httpURLConnection.getResponseCode();

            if (responsecode==200){
                Log.w("response code 3", "true ");

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