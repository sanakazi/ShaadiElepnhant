package com.shaadielephant.shaadielephant.mypage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Callndata on 3/9/2016.
 */
public class asyntask2 extends AsyncTask<String, Void, String> {
    StringBuffer response;
    Context context;
    static networkresponse2 objlistner;

    public asyntask2(Context c) {
        this.context = c;
    }

    public static void setListner(networkresponse2 objnetworkresponse) {
        objlistner = objnetworkresponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(response!=null)
            objlistner.getResponse2(response.toString());
      //  else  objlistner.getResponse2("noInternet");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

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
            String urlParameters7 = params[7];
            String urlParameters8 = params[8];


            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(urlParameters1+"&"+urlParameters2+"&"+urlParameters3+"&"+urlParameters4+"&"+urlParameters5+"&"+urlParameters6+"&"+urlParameters7+"&"+urlParameters8);
            Log.w("params are ", urlParameters1 + "&" + urlParameters2 + "&" + urlParameters3+"&" +urlParameters4+"&" +urlParameters5+"&" + urlParameters6+"&" +urlParameters7+"&" +urlParameters8);
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