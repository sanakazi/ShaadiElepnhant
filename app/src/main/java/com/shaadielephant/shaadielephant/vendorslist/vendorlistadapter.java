package com.shaadielephant.shaadielephant.vendorslist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;

import java.util.ArrayList;

public class vendorlistadapter extends BaseAdapter {
    Context context;
    ArrayList<customclassvendorlist> vendorlistarray;
    public vendorlistadapter(Context c, ArrayList<customclassvendorlist> temparray)
    {
        this.context=c;
        this.vendorlistarray=temparray;
    }
    @Override
    public int getCount() {
        return vendorlistarray.size();
    }

    @Override
    public Object getItem(int position) {
//        return vendorlistarray(position);
        return  null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View row=null;
        vendorlistholder vholder;
        try {
            if (convertView == null) {
                LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = li.inflate(R.layout.singlerowvendorlist, null);
                vholder = new vendorlistholder();
                vholder.holderimageviewicon = (ImageView) convertView.findViewById(R.id.imageViewvendorlisticon);
                vholder.holderTextViewName = (TextView) convertView.findViewById(R.id.textViewFullName);
                vholder.holderTextViewAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
                vholder.holderTextViewCityNam = (TextView) convertView.findViewById(R.id.textViewCityNam);
                //  vholder.holderTextViewStarring = (TextView) convertView.findViewById(R.id.textViewStarring);
                vholder.rating=(RatingBar)convertView.findViewById(R.id.rating);
                convertView.setTag(vholder);
            } else {
                vholder = (vendorlistholder) convertView.getTag();
            }


            customclassvendorlist test = vendorlistarray.get(position);
            if (test != null) {
                vholder.holderTextViewName.setText(test.getFullName());
                vholder.holderTextViewAddress.setText(test.getLocalityName());
                vholder.holderTextViewCityNam.setText(test.getcityName());
                // vholder.holderTextViewStarring.setText(test.getStarrating());
                vholder.rating.setRating((float) test.getStarrating());
                imagecreator imgcreator = new imagecreator(context);
//                            imgcreator.imagecreatorvendor("http://content.icicidirect.com/IdirectContent/Basemasterpage/images/iciciLogo.png",vholder.holderimageview);
                Log.d("PS", "http://shaadielephant.com/ImageVendor300/" + test.getThumbPic());
                imgcreator.imagecreatorvenderlist("http://shaadielephant.com/ImageVendor300/" + test.getThumbPic(), vholder.holderimageviewicon);

            }

        }
        catch (Exception ex)
        {
            Log.d("PS", ex.getMessage());
        }
        return convertView;
    }
}
class vendorlistholder {
    ImageView holderimageviewicon;
    TextView holderTextViewName;
    TextView holderTextViewAddress;
   // TextView holderTextViewStarring;
    TextView holderTextViewCityNam;
    RatingBar rating;

}