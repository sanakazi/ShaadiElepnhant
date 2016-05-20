package com.shaadielephant.shaadielephant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

/**
 * Created by Callndata on 3/17/2016.
 */
public class fragment_contactus extends Fragment{
    ImageView facebook,twitter,instagram,youtube,google_plus;
    TextView phone,email;
    View v;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_contactus, container, false);
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Contact Us");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        phone = (TextView)v.findViewById(R.id.phone);
        email = (TextView)v.findViewById(R.id.email);
        facebook = (ImageView)v.findViewById(R.id.facebook);
        twitter = (ImageView)v.findViewById(R.id.twitter);
        instagram = (ImageView)v.findViewById(R.id.instagram);
        youtube = (ImageView)v.findViewById(R.id.youtube);
        google_plus = (ImageView)v.findViewById(R.id.google_plus);

        phone = (TextView)v.findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:91-9969 670679"));
                startActivity(phoneIntent);

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"contact@shaadielephant.com"});
                emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   boolean installed  =   appInstalledOrNot("com.facebook.katana");
                if(installed)
                {   String uri = "facebook://facebook.com/inbox";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);}
                else {
                */
                    String url = "https://www.facebook.com/ShaadiElephant";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
               // }
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/ShaadiElephant";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/shaadielephant";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=7QkoV9c4T3k&feature=youtu.be";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        google_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://plus.google.com/107583153870208213205";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return v;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed = false;
        try
        {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            app_installed = false;
        }
        return app_installed ;
    }
}
