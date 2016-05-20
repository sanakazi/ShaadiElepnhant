package com.shaadielephant.shaadielephant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shaadielephant.shaadielepnhant.R;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//accessing from Main Page
public class MainActvity extends Activity {
    //    Button btnloign,btnvendor,btnplanning,btninvities,btnmypage,btncommunity,btncontact;
    ImageView imageVieweinvite, imageViewvendor;
    ImageView imageViewplanning, imageViewMyPage;
    ImageView imageViewCommunity, imageViewContact;
    static ImageView imageViewLogin;
    static Button imageViewLogout;
    static  SharedPreferences shared;
    static String num;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static String getHashKey(Context cntxt)
    {

        String hashKey = "";

        try {
            Log.w("KeyHash:", "getKeyHash");
            PackageInfo info = cntxt.getPackageManager().getPackageInfo(
                    "com.shaadielephant.shaadielepnhant",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

                hashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT).toString();
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        return  hashKey;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        imageViewLogin = (ImageView) findViewById(R.id.imageViewLogin);
        imageViewLogout = (Button) findViewById(R.id.imageViewLogout);
        imageViewLogout.setVisibility(View.INVISIBLE);

        getHashKey(this);

        shared = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        String email = (shared.getString("Email", "ravi@callndata.com"));
         num = (shared.getString("num", "0"));
      Log.w("facebook logged in " , "activity "+ (shared.getString("isFacebookLoggedin", "0")) + " ");


        Display display = getWindowManager().getDefaultDisplay();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        float density = getResources().getDisplayMetrics().density;
        float heightDp = displayMetrics.heightPixels / density;
        float widthDp = displayMetrics.widthPixels / density;

        Log.d("PS", "Density" + density);
        Log.d("PS", "height in pixels" + heightPixels);
        Log.d("PS", "Width in pixels" + widthPixels);
        Log.d("PS", "height in DP" + heightDp);
        Log.d("PS", "Width in Dp" + widthDp);


        visibilityLogin(num);
       /* if (num != "0") {//user is logged in

            imageViewLogin.setVisibility(View.INVISIBLE);
            imageViewLogout.setVisibility(View.VISIBLE);

        }
        */


        imageViewvendor = (ImageView) findViewById(R.id.imageViewvendor);
        imageViewvendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageViewvendor.setImageResource(R.drawable.ic_vendor_hover);
                Intent intVendor = new Intent(getApplicationContext(), main_drawer.class);
                intVendor.putExtra("FragSequence", "v");
                intVendor.putExtra("Title", "Vendor");
                startActivity(intVendor);
               // finish();
            }
        });

        imageViewplanning = (ImageView) findViewById(R.id.imageViewplanning);
        imageViewplanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewplanning.setImageResource(R.drawable.ic_planning_hover);
                Intent intPlanning = new Intent(getApplicationContext(), main_drawer.class);
                intPlanning.putExtra("FragSequence", "p");
                intPlanning.putExtra("Title", "Planning");
                startActivity(intPlanning);


            }
        });

        imageVieweinvite = (ImageView) findViewById(R.id.imageVieweinvite);
        imageVieweinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageVieweinvite.setImageResource(R.drawable.ic_einvites_hover);
                Intent intEinvities = new Intent(getApplicationContext(), main_drawer.class);
                intEinvities.putExtra("FragSequence", "e");
                intEinvities.putExtra("Title", "E-Invites");
                startActivity(intEinvities);

            }
        });

        imageViewMyPage = (ImageView) findViewById(R.id.imageViewMyPage);
        imageViewMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewMyPage.setImageResource(R.drawable.ic_mypage_hover);
                Intent intMyPage = new Intent(getApplicationContext(), main_drawer.class);
                intMyPage.putExtra("FragSequence", "m");
                intMyPage.putExtra("Title", "My Page");
                startActivity(intMyPage);

            }
        });

        imageViewCommunity = (ImageView) findViewById(R.id.imageViewCommunity);
        imageViewCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewCommunity.setImageResource(R.drawable.ic_blog_hover);
                Intent intCommunity = new Intent(getApplicationContext(), main_drawer.class);
                intCommunity.putExtra("FragSequence", "c");
                intCommunity.putExtra("Title", "Blog");
                startActivity(intCommunity);

            }
        });

        imageViewContact = (ImageView) findViewById(R.id.imageViewContact);
        imageViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewContact.setImageResource(R.drawable.ic_contact_hover);
                Intent intContact = new Intent(getApplicationContext(), main_drawer.class);
                intContact.putExtra("FragSequence", "t");
                intContact.putExtra("Title", "Contact");
                startActivity(intContact);

            }
        });


        imageViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intLogin = new Intent(getApplicationContext(), main_drawer.class);
                intLogin.putExtra("FragSequence", "l");
                intLogin.putExtra("Title", "Login");
                startActivity(intLogin);


            }
        });

        imageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for fb logout
               if((shared.getString("isFacebookLoggedin", "0")).compareTo("1")==0)
                { FacebookSdk.sdkInitialize(MainActvity.this);
                    LoginManager.getInstance().logOut();}


                shared.edit().clear().commit();
                Toast.makeText(MainActvity.this, "Logged Out", Toast.LENGTH_SHORT).show();

                imageViewLogout.setVisibility(View.INVISIBLE);
                imageViewLogin.setVisibility(View.VISIBLE);
              //  finish();


            }
        });

//        imageViewLogo= (ImageView) findViewById(R.id.imageViewBlog);
//        FrameLayout.LayoutParams lp=new  FrameLayout.LayoutParams( (int) pixelstodp(getApplicationContext(),(widthPixels/4)),(int)pixelstodp(getApplicationContext(),(heightPixels/8)));
//        imageViewLogo.setLayoutParams(lp);
//        lp.width= (int) pixelstodp(getApplicationContext(),(widthPixels/4));
//        lp.height= (int) pixelstodp(getApplicationContext(),(heightPixels/8));
        //  temp layout start
//        btnloign= (Button) findViewById(R.id.btnLogin);
//        btnvendor= (Button) findViewById(R.id.btnVendor);
//        btnplanning= (Button) findViewById(R.id.btnPlanning);
//        btninvities = (Button) findViewById(R.id.btnInvities);
//        btnmypage = (Button) findViewById(R.id.btnMypage);
//        btncommunity= (Button) findViewById(R.id.btnCommunity);
//        btncontact= (Button) findViewById(R.id.btnContact);
//
//        btnloign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intLogin= new Intent(getApplicationContext(),main_drawer.class);
//                intLogin.putExtra("FragSequence","b");
//                intLogin.putExtra("Title","Login");
//                startActivity(intLogin);
//            }
//        });
//        btnvendor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intVendor= new Intent(getApplicationContext(),main_drawer.class);
//                intVendor.putExtra("FragSequence","v");
//                intVendor.putExtra("Title","Vendor");
//                startActivity(intVendor);
//            }
//        });
        //tempp layout end
//
//        /* calculation of height and width in pixel and dp*/
//
//        DisplayMetrics displayMetrics= getResources().getDisplayMetrics();
//        float dpHeight=displayMetrics.heightPixels/displayMetrics.density;
//
//        float dpWidth= displayMetrics.widthPixels/displayMetrics.density;
//
//        ImageView imgLogo=(ImageView)findViewById(R.id.imageViewLogo);
//        imgLogo.getLayoutParams().height=(int)(dpHeight/5.2);
//        imgLogo.getLayoutParams().width=(int)((dpWidth*2)/3.2);
//
//        ImageView imgTop=(ImageView)findViewById(R.id.imageViewTop);
//        imgTop.getLayoutParams().height=(int)((dpHeight-imgLogo.getLayoutParams().height)*20)/22;
//        imgTop.getLayoutParams().width=(int)(dpWidth*1.6);
//
//        ImageView imgBottom=(ImageView)findViewById(R.id.imageViewBottom);
//        imgBottom.getLayoutParams().height=(int)((dpHeight-imgLogo.getLayoutParams().height)*19)/22;
//        imgBottom.getLayoutParams().width=(int)(dpWidth*1.2);
//
//        ImageButton imgButton= (ImageButton)findViewById(R.id.imageButtonLogin);
//        imgButton.getLayoutParams().height=imgButton.getLayoutParams().width=(int)(dpWidth*1.5)/3;
//        imgButton.setOnClickListener(new ClickHandler(this));
//        imgButton.getLayoutParams().width=(int)(dpWgeidth*3)/4;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public float pixelstodp(Context context, int px) {
        Resources res = context.getResources();
        float density = res.getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

//    public class ClickHandler implements  View.OnClickListener
//    {
//        Context c;
//        ClickHandler(Context cc)
//        {
//            c=cc;
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId())
//            {
//
//                case R.id.imageButtonLogin:
//                Toast.makeText(c, "hello", Toast.LENGTH_SHORT).show();
//                    Intent intLogin= new Intent(c,main_drawer.class);
////                    Intent intLogin= new Intent(c,activity_imagetest.class);
//                    intLogin.putExtra("FragSequence","b");
//                    intLogin.putExtra("Title","Login");
//                    startActivity(intLogin);
//                    break;
//
//            }
//
//        }
//    }


    @Override
    protected void onResume() {

        Log.w("onresume MainActivity= ", (shared.getString("num", "0")));
        super.onResume();
        imageViewvendor.setImageResource(R.drawable.ic_vendor);
        imageViewplanning.setImageResource(R.drawable.ic_planning);
        imageVieweinvite.setImageResource(R.drawable.ic_einvites);
        imageViewMyPage.setImageResource(R.drawable.ic_mypage);
        imageViewCommunity.setImageResource(R.drawable.ic_blog);
        imageViewContact.setImageResource(R.drawable.ic_contact);
        imageViewLogin.setImageResource(R.drawable.login_leaf);

        if ((shared.getString("num","0")) != "0") {

            imageViewLogin.setVisibility(View.INVISIBLE);
            imageViewLogout.setVisibility(View.VISIBLE);

        }

    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActvity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.shaadielephant.shaadielepnhant/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActvity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.shaadielephant.shaadielepnhant/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public static void visibilityLogin(String stat) {
        if ((shared.getString("num","0")) != "0") {//user is logged in
            Log.w("num visibility is" , num);
            imageViewLogin.setVisibility(View.INVISIBLE);
            imageViewLogout.setVisibility(View.VISIBLE);
        }
    }

}


