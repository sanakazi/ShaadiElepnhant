package com.shaadielephant.shaadielephant.login;

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


public class asyntask4 extends AsyncTask<String, Void, String> {
    StringBuffer response;
    Context context;
    //ProgressDialog progressDialog;
    customProgressDialog progressDialog;
    static networkresponse4 objlistner;

    public asyntask4(Context c) {
        this.context = c;
    }

    public static void setListner(networkresponse4 objnetworkresponse) {
        objlistner = objnetworkresponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog != null) {
            progressDialog.dismiss();

        }
        if(response!=null)
            objlistner.getResponse4(response.toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new customProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];

        try {
            URL objulr = new URL(url);
            Log.w("url is " , url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            String urlParameters1 = params[1];
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(urlParameters1);
            Log.w("params are ", urlParameters1 );
            wr.flush();
            wr.close();

            int responsecode = httpURLConnection.getResponseCode();
            if (responsecode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            }
        } catch (Exception ex) {
        }
        return null;
    }
}
