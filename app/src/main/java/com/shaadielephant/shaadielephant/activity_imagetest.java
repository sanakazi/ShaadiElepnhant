package com.shaadielephant.shaadielephant;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.shaadielephant.shaadielepnhant.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class activity_imagetest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityimagetest);

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .displayer(new CircleBitmapDisplayer(20))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(displayImageOptions)

                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ImageLoader.getInstance().displayImage(url,imageView);
//        Log.d("PS", "A");
        ImageLoader imageLoader = ImageLoader.getInstance();
//        Log.d("PS", "b");
//        Log.d("PS", "A]e");
        downloadblog dwnblog= new downloadblog();
        dwnblog.execute(new String[]{""});

    }
    class downloadblog extends AsyncTask<String,Void,String>
    {
        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }

            /* Close Stream */
            if(null!=inputStream){
                inputStream.close();
            }
            return result;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection=null;
            InputStream inputStream =null;
            String result="";
//            try
//            {
//                URL url= new URL(params[0]);
//                urlConnection= (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("POST");
//                int statuscode=urlConnection.getResponseCode();
//                if(statuscode==200)
//                {
//                    inputStream= new BufferedInputStream(urlConnection.getInputStream());
//                    result= convertInputStreamToString(inputStream);
//                }
//                else
//                {
//                    result="Error";
//                }
//            }
//            catch (Exception e)
//            {
//                Log.d("PS", e.getLocalizedMessage());
//                return e.getLocalizedMessage();
//            }
//             final String SOAP_ACTION = "http://tempuri.org/latest50BlogList";
            final String SOAP_ACTION = "http://tempuri.org/Add";

//              final String OPERATION_NAME = "latest50BlogList";
            final String OPERATION_NAME = "Add";

              final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

//              final String SOAP_ADDRESS = "http://webservices.shaadielephant.com/blog.asmx";
     final String SOAP_ADDRESS="http://grasshoppernetwork.com/NewFile.asmx";

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

            PropertyInfo pi=new PropertyInfo();
            pi.setName("a");
            pi.setValue(4);
            pi.setType(Integer.class);
            request.addProperty(pi);
            pi=new PropertyInfo();
            pi.setName("b");
            pi.setValue(8);
            pi.setType(Integer.class);
            request.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try{
                httpTransport.call(SOAP_ACTION, envelope);
                response = envelope.getResponse();
                result=response.toString();
                Log.d("PS",result);

            }
            catch (Exception e)
            {
                Log.e("PS",e.getLocalizedMessage());
                result=e.getLocalizedMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
