package com.shaadielephant.shaadielephant.budget_item;

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
 * Created by Callndata on 2/22/2016.
 */
public class asyntask6 extends AsyncTask<String, Void, String> {
    StringBuffer response;
    Context context;
    static networkresponse6 objlistner;
    customProgressDialog progressDialog;

    public asyntask6(Context c) {
        this.context = c;
    }

    public static void setListner(networkresponse6 objnetworkresponse) {
        objlistner = objnetworkresponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog != null) {
            progressDialog.dismiss();

        }
        if(response!=null)
            objlistner.getResponse6(response.toString());
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
            HttpURLConnection httpURLConnection = (HttpURLConnection) objulr.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            String urlParameters1 = params[1];
            String urlParameters2 = params[2];
            String urlParameters3 = params[3];
            String urlParameters4 = params[4];
            String urlParameters5 = params[5];
            String urlParameters6 = params[6];
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(urlParameters1+"&"+urlParameters2+"&"+urlParameters3+"&"+urlParameters4+"&"+urlParameters5+"&"+urlParameters6);
            wr.flush();
            wr.close();

            int responsecode = httpURLConnection.getResponseCode();

            if (responsecode == 200) {
                Log.w("response", "true");
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