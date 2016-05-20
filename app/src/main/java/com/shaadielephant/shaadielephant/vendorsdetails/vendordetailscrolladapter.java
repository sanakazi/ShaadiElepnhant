/*package com.shaadielephant.shaadielephant.vendorsdetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;

import java.util.ArrayList;

public class vendordetailscrolladapter extends BaseAdapter implements networkresponse2,networkresponse3{
    Context context;
    ArrayList<customclassvendordetaildata> outputdata;
    ArrayList<customclassvendordetailpictures> outputpicture;
    ArrayList<customclassvendordetailcomment> outputcomment;
    customclassvendordetailcombine output;
    customclassvendordetailpictures clspicture;
    customclassvendordetaildata clsdata;
    frgmvendordetailsscroll hi;
    SharedPreferences shared;
    ArrayList<String>objadd;

    vendordetailscrolladapter(Context c, customclassvendordetailcombine custoutput)

    {
        this.context=c;
        this.output=custoutput;

    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        outputdata= output.getCustclassvendordetaildata();
        outputpicture=output.getCustclassvendordetailpicture();
        outputcomment =output.getCustclassvendordetailcomment();
        LayoutInflater li= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        v= li.inflate(R.layout.singlerowvendordetail, null);
        clsdata= outputdata.get(0);



        TextView  txtviewname= (TextView) v.findViewById(R.id.textViewName);
        txtviewname.setText(clsdata.getFullName());

        ImageView  imageviewtop = (ImageView) v.findViewById(R.id.imageViewTop);
        imagecreator imgcreator= new imagecreator(context);
          imgcreator.imagecreatorvendor("http://shaadielephant.com/ImageVendor450/" + clsdata.getThumbPic(), imageviewtop);
        //imgcreator.imagecreatorvendor(hi.headerImage, imageviewtop);


        RatingBar vendordetailrating= (RatingBar) v.findViewById(R.id.vendordetailrating);
        vendordetailrating.setRating((float) clsdata.getStarrating());

        TextView  textviewlocation= (TextView) v.findViewById(R.id.textViewLocation);
        textviewlocation.setText(clsdata.getLocalityName());

        TextView txtbasicinfor= (TextView) v.findViewById(R.id.textViewBasicInformation);
        txtbasicinfor.setText(clsdata.getProfile());

        TextView contactname= (TextView) v.findViewById(R.id.contactname);
        contactname.setText(clsdata.fullName);

        TextView contactaddress= (TextView) v.findViewById(R.id.contactaddress);
        contactaddress.setText(clsdata.getAddress());

        TextView contactphone=(TextView) v.findViewById(R.id.contactphone);
        contactphone.setText(clsdata.getPhone());

        ListView listviewComment= (ListView) v.findViewById(R.id.listviewComment);

        final ImageView img_favorites_icon =(ImageView)v.findViewById(R.id.img_favorites_icon);
        img_favorites_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("onclick", " true");
                if (shared.getString("num", "0") != "0")
                {
                    img_favorites_icon.setImageResource(R.drawable.favorites_icon_pink);
                    asyntask2 objasyntask2 = new asyntask2(context);
                    asyntask2.setListner(vendordetailscrolladapter.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/Vendors.asmx/VendorAddTouserFavorite",
                            "productCatID=" + clsdata.getProductCatID(), "vendorID=" + clsdata.getNum(), "vendorName=" + clsdata.getFullName(), "VendorBrief=" + clsdata.getProfile(), "address=" + clsdata.getAddress(), "phoneNumbers=" + clsdata.getPhone(),
                            "userID=" + (shared.getString("num", "0"))};
                    objasyntask2.execute(strinputarray);
                }
                else
                {
                    Toast.makeText(context,"You need to login first",Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayout linlay= (LinearLayout) v.findViewById(R.id.linlayoutScrollImages);
        for(int imgcount=0;imgcount<outputpicture.size();imgcount++)
        {

            clspicture= outputpicture.get(imgcount);
            ImageView imageView = new ImageView(context);
            imagecreator imgvendor= new imagecreator(context);
            imgvendor.imagecreatorvendrodetail("http://shaadielephant.com/ImageVendor/" + clspicture.getImageurl(), imageView);

           //This will give dimensions to imagesize
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    200, 300);
            lp.setMargins(3, 3, 3, 3);
            imageView.setLayoutParams(lp);

            linlay.addView(imageView);

        }

        vendordetailscrolladaptercomment objvendoradapter= new vendordetailscrolladaptercomment(context,outputcomment);
        listviewComment.setAdapter(objvendoradapter);

        return v;
    }
// to make as favorite vendor
    @Override
    public void getResponse2(String strinput) {
        fav_vendor_jsonparser obj_fav_vendor_add_jsonparser = new fav_vendor_jsonparser();
        objadd =obj_fav_vendor_add_jsonparser.parser(strinput);

    }

    // for heart shape marking of fav_vendor
    @Override
    public void getResponse3(String strinput) {

    }
}
*/