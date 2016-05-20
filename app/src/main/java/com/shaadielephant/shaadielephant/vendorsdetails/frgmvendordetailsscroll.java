package com.shaadielephant.shaadielephant.vendorsdetails;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;
import com.shaadielephant.shaadielephant.main_drawer;


import java.util.ArrayList;


public class frgmvendordetailsscroll extends Fragment implements networkresponse1,networkresponse3 {
    String headerImage;
    customclassvendordetailcombine objvendordetils;
    ArrayList<customclassvendordetaildata> arrdata;
    ArrayList<customclassvendordetailpictures> arrpucture;
    ArrayList<customclassvendordetailcomment> arraycomment;
    TextView txtviewname,textviewlocation;
    ImageView imageviewtop;
    RatingBar vendordetailrating;
    ListView listViewvendordetailscroll;
    int vendorid;
    SharedPreferences shared;
    ArrayList<String> ob_img_fav;
    String fav_vendor = "0";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retview=null;
        retview= inflater.inflate(R.layout.frgmvendordetailsscroll, container, false);
        Bundle getdata=getArguments();
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         vendorid= getdata.getInt("vendorid");
       headerImage=getdata.getString("ImageHeader");
        Log.d("PS", vendorid + "");
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Vendor Detail");
        ((main_drawer) getActivity()).setbartitle(getActivity(),call.TITLE_LIST.get(call.TITLE_LIST.size()-1));

/*
        txtviewname= (TextView) retview.findViewById(R.id.textViewName);
        imageviewtop = (ImageView) retview.findViewById(R.id.imageViewTop);
        vendordetailrating= (RatingBar) retview.findViewById(R.id.vendordetailrating);
        textviewlocation= (TextView) retview.findViewById(R.id.textViewLocation);
*/
        // service for making heart shaped icon of liking vendor pink if it is already added as fav vendor
        if((shared.getString("num", "0")!="0"))
        {
            asyntask3 objasyntask1 = new asyntask3(getActivity());
            asyntask3.setListner(frgmvendordetailsscroll.this);
            String[] strinputarray1 = {"http://webservices.shaadielephant.com/usersVendors.asmx/usersCheckIfVendorIsFavVendor", "userID=" + (shared.getString("num", "0")),
                    "vendorID=" + vendorid};
            objasyntask1.execute(strinputarray1);
        }

        Log.w("vendor list id = ", vendorid + " ");
        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(frgmvendordetailsscroll.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/vendors.asmx/vendorDetails","vendorID="+vendorid};
        objasyntask.execute(strinputarray);


        listViewvendordetailscroll= (ListView) retview.findViewById(R.id.listViewvendordetailscroll);
        setHasOptionsMenu(false);
        return retview;
    }

    @Override
    public void getResponse1(String strinput) {
        vendordetailsparser objvendorlisVendordetailjsonparser = new vendordetailsparser();
        objvendordetils= objvendorlisVendordetailjsonparser.parser(strinput);

        arrdata = objvendordetils.getCustclassvendordetaildata();

        vendordetailscrolladapter objvendoradapter= new vendordetailscrolladapter(getActivity(),objvendordetils);
        listViewvendordetailscroll.setAdapter(objvendoradapter);

    }

    // for heart shape marking of fav_vendor
    @Override
    public void getResponse3(String strinput) {

        vendorsdetails_add_fav_img_jsonparser obj = new vendorsdetails_add_fav_img_jsonparser();
        ob_img_fav = obj.parser(strinput);
        String status_report = obj.arrayList.get(0);
        Log.w("status report is " , status_report);

        if (status_report == "1")
        {
            fav_vendor = "1";

        }
        else
        {
            fav_vendor = "0";
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Vendor Details");

    }


    //adapter class


    public class vendordetailscrolladapter extends BaseAdapter implements networkresponse2,networkresponse4{
        Context context;
        ArrayList<customclassvendordetaildata> outputdata;
        ArrayList<customclassvendordetailpictures> outputpicture;
        ArrayList<customclassvendordetailcomment> outputcomment;
        customclassvendordetailcombine output;
        customclassvendordetailpictures clspicture;
        customclassvendordetaildata clsdata;
        frgmvendordetailsscroll hi;
        SharedPreferences shared;
        ArrayList<String>objadd,ob_com_rat;
        private Dialog dialog;
        private Dialog dialog1;
        RatingBar comments_rating;
        int get_rating;
        int ID;
        ImageView imageView;
        String imageUrl;

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
            clsdata= outputdata.get(position);

            ID = clsdata.getNum();
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

            TextView text_review = (TextView)v.findViewById(R.id.text_review);
            text_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shared.getString("num", "0") != "0")
                    { showDialogToPostComments();}
                    else{
                        Toast.makeText(context, "You need to login first to comment", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            if (fav_vendor=="1") {
                img_favorites_icon.setImageResource(R.drawable.favorites_icon_pink);
            }

            img_favorites_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shared.getString("num", "0") != "0")
                    {
                        if(fav_vendor.compareTo("0")==0)
                        {
                            img_favorites_icon.setImageResource(R.drawable.favorites_icon_pink);
                            asyntask2 objasyntask2 = new asyntask2(context);
                            asyntask2.setListner(vendordetailscrolladapter.this);
                            String[] strinputarray = {"http://webservices.shaadielephant.com/Vendors.asmx/VendorAddTouserFavorite",
                                    "productCatID=" + clsdata.getProductCatID(), "vendorID=" + clsdata.getNum(), "vendorName=" + clsdata.getFullName(), "VendorBrief=" + clsdata.getProfile(), "address=" + clsdata.getAddress(), "phoneNumbers=" + clsdata.getPhone(),
                                    "userID=" + (shared.getString("num", "0"))};
                            objasyntask2.execute(strinputarray);
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "You need to login first", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            LinearLayout linlay= (LinearLayout) v.findViewById(R.id.linlayoutScrollImages);
            for(int imgcount=0;imgcount<outputpicture.size();imgcount++)
            {
                final int i = imgcount;
                clspicture= outputpicture.get(imgcount);
                 imageView = new ImageView(context);
                imagecreator imgvendor= new imagecreator(context);
                imageUrl= clspicture.getImageurl();
                Log.w("image shown ","http://shaadielephant.com/ImageVendor/" + clspicture.getImageurl());
                imgvendor.imagecreatorvendrodetail("http://shaadielephant.com/ImageVendor/" + clspicture.getImageurl(), imageView);

                imageView.setTag(imageUrl);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String clspicTopass = (String)v.getTag();
                        Log.w("image to be passed ","http://shaadielephant.com/ImageVendor/" + clspicTopass);
                        showDialogToShowImage(i,clspicTopass);
                    }
                });

                //This will give dimensions to imagesize
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        200, 300);
                lp.setMargins(3, 3, 3, 3);
                imageView.setLayoutParams(lp);
                linlay.addView(imageView);

            }


            vendordetailscrolladaptercomment objvendoradapter= new vendordetailscrolladaptercomment(context,outputcomment);
            listviewComment.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, outputcomment.size()*150));
            listviewComment.setAdapter(objvendoradapter);

            return v;
        }




        //popup dialog to show  popup image
        private void showDialogToShowImage(int i,String s) {

            final String display = s;
            Log.w("string is" ,"http://shaadielephant.com/ImageVendor/" + display );
            dialog1 = new Dialog(getActivity());
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog1.setContentView(R.layout.dialog_vendordetails_image);
            dialog1.setCancelable(true);


            final ImageView img1 = (ImageView)dialog1.findViewById(R.id.img1);

            imagecreator imgvendor= new imagecreator(context);
            imgvendor.imagecreatorvendrodetail("http://shaadielephant.com/ImageVendor/" + display, img1);

            dialog1.show();

        }




        // to make as favorite vendor
        @Override
        public void getResponse2(String strinput) {
            fav_vendor_jsonparser obj_fav_vendor_add_jsonparser = new fav_vendor_jsonparser();
            objadd =obj_fav_vendor_add_jsonparser.parser(strinput);

        }

        //for comments and rating


        @Override
        public void getResponse4(String strinput) {
            add_comments_rating_jsonparser ob = new add_comments_rating_jsonparser();
            ob_com_rat = ob.parser(strinput);
            dialog.dismiss();
            if(ob.arrayList!=null)
            {
                String status_report = ob.arrayList.get(0);
                if (status_report.compareTo("1") == 0) {
                    Toast.makeText(context, "Your comment has been sent for approval", Toast.LENGTH_SHORT).show();
                }
            }

        }

        //popup dialog to show comments
        private void showDialogToPostComments() {

            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_comments_rating);
            dialog.setCancelable(false);
            Button comment_submit = (Button) dialog.findViewById(R.id.comment_submit);
            final EditText comments_text = (EditText) dialog.findViewById(R.id.comments_text);
            comments_rating = (RatingBar)dialog.findViewById(R.id.rating);

            get_rating = (int)comments_rating.getRating();

            ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
            dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });


            comment_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comments_text.getText().toString().length() == 0) {
                        Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        asyntask4 objasyntask4 = new asyntask4(getActivity());
                        asyntask4.setListner(vendordetailscrolladapter.this);
                        String[] strinputarray = {"http://webservices.shaadielephant.com/RatingAndComments.asmx/AddRatinAndComments", "sectionName=" + "master_vendors",
                                "ID=" + clsdata.getNum() , "rateVal="+ get_rating , "givencomments="+comments_text.getText().toString(),
                        "userID=" +(shared.getString("num","0")) };
                        objasyntask4.execute(strinputarray);

                    }

                }
            });
            dialog.show();

        }



    }

}
