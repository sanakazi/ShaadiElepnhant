package com.shaadielephant.shaadielephant.fav_vendors;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shaadielephant.shaadielephant.customProgressDialog;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//adding new fav vendor


public class asyntask3 extends AsyncTask<String, Void, String> {
    StringBuffer response;
    Context context;
    static networkresponse3 objlistner;
    customProgressDialog progressDialog;

    public asyntask3(Context c) {
        this.context = c;
    }

    public static void setListner(networkresponse3 objnetworkresponse) {
        objlistner = objnetworkresponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog!=null){
            progressDialog.dismiss();

        }
        if(response!=null)
        objlistner.getResponse3(response.toString());
     //   else  objlistner.getResponse3("noInternet");

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
        String url = params[0];

        try {
            URL objulr = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            String urlParameters1 = params[1];
            Log.w("url", urlParameters1);
            String urlParameters2 = params[2];
            Log.w("url", urlParameters2);
            String urlParameters3 = params[3];
            Log.w("url", urlParameters3);
            String urlParameters4 = params[4];
            Log.w("url", urlParameters4);
            String urlParameters5 = params[5];
            Log.w("url", urlParameters5);
            String urlParameters6 = params[6];
            Log.w("url", urlParameters6);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(urlParameters1+"&"+urlParameters2+"&"+urlParameters3+"&"+urlParameters4+"&"+urlParameters5+"&"+urlParameters6);
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