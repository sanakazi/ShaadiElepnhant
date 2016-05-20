package com.shaadielephant.shaadielephant.mypage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.shaadielephant.shaadielephant.MainActvity;
import com.shaadielephant.shaadielephant.fav_vendors.fragmentfav_vendor;
import com.shaadielephant.shaadielephant.guestlist.fragment_invitation;
import com.shaadielephant.shaadielephant.imagecreator;

import com.shaadielephant.shaadielephant.main_drawer;
import com.shaadielephant.shaadielephant.mydesignerspeaklist.fragment_mydesignerspeaklist;
import com.shaadielephant.shaadielephant.mynotes.fragment_mynotes;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielephant.planning_page.frg_planning;
import com.shaadielephant.shaadielephant.planning_page.frg_webview;

import com.shaadielephant.shaadielepnhant.R;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Callndata on 1/28/2016.
 */
public class fragmentmypage extends Fragment implements networkresponse1,networkresponse2,networkresponse3,networkresponse4{
    ImageView img_planning,designer_speak,my_vendors,my_notes,wedding_website,guest_list;
    SharedPreferences shared;
    String userID;
    String email;
    ArrayList<customclass_mypage>objArraylist;
    ArrayList<String> ob_update,ob_change_pass;
    TextView txt_name,txt_member_since,change_password,email_id;
    ImageView logout_leaf, edit_mypage;
    private static Dialog dialog ;
    public Spinner gender;
    public String myName,myGender,myAdd,myCountry,myCity,myPhone,myUserPic;
    private String encoded;
    private Bitmap bm;
    String base64;

    ImageView img_profile_pic;


    public static  HashMap<String,String> data = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentmypage, container, false);
        FacebookSdk.sdkInitialize(this.getActivity());
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        email = (shared.getString("Email","0"));




        refresh();
        LinearLayout main = (LinearLayout)v.findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment mypage main");
            }
        });

        img_planning=(ImageView)v.findViewById(R.id.img_planning);
        designer_speak=(ImageView)v.findViewById(R.id.designer_speak);
        my_vendors=(ImageView)v.findViewById(R.id.my_vendors);
        my_notes=(ImageView)v.findViewById(R.id.my_notes);
        wedding_website=(ImageView)v.findViewById(R.id.wedding_website);
        guest_list=(ImageView)v.findViewById(R.id.guest_list);
        edit_mypage = (ImageView)v.findViewById(R.id.edit_mypage);
        img_profile_pic = (ImageView)v.findViewById(R.id.img_profile_pic);



        txt_name = (TextView)v.findViewById(R.id.txt_name);
        txt_member_since = (TextView)v.findViewById(R.id.txt_member_since);
        change_password = (TextView)v.findViewById(R.id.change_password);
        email_id = (TextView)v.findViewById(R.id.email_id);
        logout_leaf = (ImageView)v.findViewById(R.id.logout_leaf);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToChangePassword();
            }
        });
        logout_leaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Inside logout menu", "true " + (shared.getString("isFacebookLoggedin", "0")));

                //for fb logout
                if ((shared.getString("isFacebookLoggedin", "0")).compareTo("1") == 0) {
                    LoginManager.getInstance().logOut();
                }
                shared.edit().clear().commit();

                Intent intent1 = new Intent(getActivity(), MainActvity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });


        edit_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToEdit();
            }
        });


        img_planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                frg_planning frgplan = new frg_planning();
                fragmentTransaction.add(R.id.mainContent, frgplan, "FragmentPlanning").addToBackStack("1");
                fragmentTransaction.commit();
            }
        });
        my_vendors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentfav_vendor favendors = new fragmentfav_vendor();
                ft.add(R.id.mainContent, favendors, "FavVendorDetails").addToBackStack("kj");
                ft.commit();
            }
        });

        wedding_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frg_webview fc = new frg_webview();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "mobileuserWebsite.aspx");
                bundle.putString("pageheader", "Website");
                fc.setArguments(bundle);
                ft.add(R.id.mainContent, fc, "dsff").addToBackStack("3");
                ft.commit();
            }
        });

        guest_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_invitation fg_guests = new fragment_invitation();
                ft.add(R.id.mainContent, fg_guests, "Guests").addToBackStack("dfd");
                ft.commit();
            }
        });

        my_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                fragment_mynotes fg_notes= new fragment_mynotes();
                ft.add(R.id.mainContent, fg_notes, "Guests").addToBackStack("dfd");
                ft.commit();
            }
        });

        designer_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_mydesignerspeaklist fg_expert = new fragment_mydesignerspeaklist();
                ft.add(R.id.mainContent, fg_expert, "Guests").addToBackStack("dfd");
                ft.commit();
            }
        });


        img_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });



        return v;
    }


    //popup dialog to edit myPage
    private void showDialogToEdit() {

        int pos;
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_mypage);
        dialog.setCancelable(false);
        Button edit_mypage_save = (Button) dialog.findViewById(R.id.edit_mypage_save);

        final EditText name = (EditText) dialog.findViewById(R.id.name);
        gender = (Spinner)dialog.findViewById(R.id.gender);
        spinnermethod();
        final EditText address = (EditText) dialog.findViewById(R.id.address);
        final EditText country = (EditText) dialog.findViewById(R.id.country);
        final EditText city = (EditText) dialog.findViewById(R.id.city);
        final EditText phoneNo = (EditText) dialog.findViewById(R.id.phoneNo);


        name.setText(myName);
        address.setText(myAdd);
        country.setText(myCountry);
        city.setText(myCity);
        phoneNo.setText(myPhone);
      //  Log.w("myGender = ", myGender);
        if(myGender!=null)
        {
            if (myGender.equals("Female")) {
                pos = 1;
            } else {
                pos = 0
                ;
            }
            gender.setSelection(pos);
        }

        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        edit_mypage_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().length() == 0 ||address.getText().toString().length() == 0||
                        city.getText().toString().length() == 0
                        ||country.getText().toString().length() == 0||gender.getSelectedItem().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask2 objasyntask2 = new asyntask2(getActivity());
                    asyntask2.setListner(fragmentmypage.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/MemberProfileUpdate", "userID=" + userID,
                            "Name=" + name.getText().toString(),"emailID="+email,"PhoneNo="+phoneNo.getText().toString(),
                            "gender="+gender.getSelectedItem().toString(),"Address="+address.getText().toString(),"country="+ country.getText().toString(),
                            "city="+city.getText().toString()};
                    objasyntask2.execute(strinputarray);


                }

            }
        });
        dialog.show();

    }


    //popup dialog to change Password
    private void showDialogToChangePassword() {

        int pos;
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_change_password);
        dialog.setCancelable(false);
        Button save_password = (Button) dialog.findViewById(R.id.save_password);

        final EditText enter_old_pass = (EditText) dialog.findViewById(R.id.enter_old_pass);
        final EditText enter_new_pass = (EditText) dialog.findViewById(R.id.enter_new_pass);
        final EditText retype_new_pass = (EditText) dialog.findViewById(R.id.retype_new_pass);

        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter_old_pass.getText().toString().length() == 0 || enter_new_pass.getText().toString().length() == 0 ||
                        retype_new_pass.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else if (enter_new_pass.getText().toString().equals(retype_new_pass.getText().toString())) {
                    asyntask3 objasyntask3 = new asyntask3(getActivity());
                    asyntask3.setListner(fragmentmypage.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/MemberChangePassword",
                            "userID=" + userID,
                            "oldPassword=" + enter_old_pass.getText().toString(),
                            "newpassword=" + retype_new_pass.getText().toString()};
                    objasyntask3.execute(strinputarray);

                } else {

                    Toast.makeText(getActivity(), "Retyped password is different", Toast.LENGTH_SHORT).show();


                }

            }
        });
        dialog.show();

    }



    public void spinnermethod()

    {
        String[] categories = new String[2];
        categories[0] = "Male";
        categories[1] = "Female";


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender.setAdapter(dataAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{

            // if request code is same we pass as argument in startActivityForResult
            if (requestCode == 1) {
                // create instance of File with same name we created before to get
                // image from storage
                File file = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "img.jpg");
                // Crop the captured image using an other intent
                try {
				/* the user's device may not support cropping */
                    cropCapturedImage(Uri.fromFile(file));
                } catch (ActivityNotFoundException aNFE) {
                    // display an error message if user device doesn't support
                    String errorMessage = "Sorry - your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(this.getActivity(), errorMessage,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else if (requestCode == 2) {
                // Create an instance of bundle and get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap from extras
                Bitmap thePic = extras.getParcelable("data");
                // set image bitmap to image view
                img_profile_pic.setImageBitmap(thePic);

                convertBase64(thePic);
            } else if (requestCode == 3) {
                Uri picUri = data.getData();
                performCrop(picUri);
            } else if (requestCode == 4) {
                // get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");
                // set image bitmap to image view
                //iv_profile_picture.setImageBitmap(thePic);

                convertBase64(thePic);
            }

        }catch(NullPointerException e){
            e.printStackTrace();
        }


    }


    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {

					/*
					 * create an instance of intent pass action
					 * android.media.action.IMAGE_CAPTURE as argument to launch
					 * camera
					 */
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					/* create instance of File with name img.jpg */
                    File file = new File(Environment.getExternalStorageDirectory()
                            + File.separator + "img.jpg");
					/* put uri as extra in intent object */
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					/*
					 * start activity for result pass intent as argument and request
					 * code
					 */
                    startActivityForResult(intent, 1);

                } else if (items[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 3);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public void convertBase64(Bitmap bitmap){
        bm = bitmap;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        img_profile_pic.setImageBitmap(bm);
       // img_profile_pic.setScaleY((float) 1.2);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // convrting into base64
		/*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);*/
        byte[] byteArray = bytes.toByteArray();
        encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.w("Base64encodedvalue:", encoded);

        //  new AddProfilePicTask().execute();
        // call webservice here
        asyntask4 objasyntask4 = new asyntask4(getActivity());
        asyntask4.setListner(fragmentmypage.this);

        data.put("UserID", userID);
        data.put("UserProfilePicture", encoded);
        String[] strinputarray = {"http://shaadielephant.com/InsertProfilePicture.asmx/UpdateProfilePicture",
                "UserID="+userID,
                "UserProfilePicture="+base64};
        objasyntask4.execute(strinputarray);

    }

    // create helping method cropCapturedImage(Uri picUri)
    public void cropCapturedImage(Uri picUri) {
        // call the standard crop action intent
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        // indicate image type and Uri of image
        cropIntent.setDataAndType(picUri, "image/*");
        // set crop properties
        cropIntent.putExtra("crop", "true");
        // indicate aspect of desired crop
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        // indicate output X and Y
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        // retrieve data on return
        cropIntent.putExtra("return-data", true);
        // start the activity - we handle returning in onActivityResult
        startActivityForResult(cropIntent, 2);
    }

    public void performCrop(Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");



            // indicate selection ratio
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);

            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);


            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, 4);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void getResponse1(String strinput)
    {

        if(strinput.compareTo("noInternet")==0)
        {
            Log.w("strinput true no", " ");

            FragmentManager fm= getActivity().getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();
            noInternetConnection_fragment frgmvendorlist= new noInternetConnection_fragment();
            ft.replace(R.id.mainContent, frgmvendorlist, "VendorList").addToBackStack("abc");
            ft.commit();

        }

        mypage_jsonparser obj = new mypage_jsonparser();
        objArraylist= obj.parser(strinput);

        customclass_mypage test = objArraylist.get(0);

        if (test != null) {


            txt_name.setText(test.getName());
            txt_member_since.setText(test.getInsertDate());
            email_id.setText(test.getEmailID());

            //store values for further use;
            myName = test.getName();
            myGender= test.getGender();
            myAdd= test.getAddress();
            myCountry = test.getCountry();
            myCity = test.getCity();
            myPhone = test.getMobileNo();
            myUserPic=test.getUserPicture();
            if(myUserPic.compareTo("")==0)
            {}
            else {
                new DownloadImageTask(img_profile_pic).execute("http://shaadielephant.com/imageuserProfile/" +myUserPic);
                Log.w("link is ", "http://shaadielephant.com/imageuserProfile/" + (shared.getString("num", "0")) + ".jpg");

            }

        }


    }

    //edit profile save
    @Override
    public void getResponse2(String strinput) {
        update_myprofile_jsonparser ob = new update_myprofile_jsonparser();
        ob_update = ob.parser(strinput);
        dialog.dismiss();

        String status_report = ob.arrayList.get(0);
        Log.w("status report is ", status_report);
        if(status_report!="0") {
            refresh();
            Toast.makeText(getActivity(), "Profile updated", Toast.LENGTH_SHORT).show();

        }
        else
        {

            Toast.makeText(getActivity(), " " +ob.arrayList.get(1) , Toast.LENGTH_SHORT).show();
        }



    }




    //change password
    @Override
    public void getResponse3(String strinput) {
        Log.w("Inside response", " true");
        change_pass_jsonparser ob = new change_pass_jsonparser();
        ob_change_pass = ob.parser(strinput);
        // ob.arrayList.get(0);

        String status_report = ob.arrayList.get(0);
        Log.w("status_report = " , status_report);
        if(status_report!="0") {
            Toast.makeText(getActivity(), " " +ob.arrayList.get(1) , Toast.LENGTH_SHORT).show();
        }
        else
        {
            refresh();
            Toast.makeText(getActivity(), "Password changed successfully", Toast.LENGTH_SHORT).show();

        }

        dialog.dismiss();
    }

    //upload profile pic
    @Override
    public void getResponse4(String strinput) {
        Log.w("str output = " , strinput);
        update_myprofile_jsonparser ob = new update_myprofile_jsonparser();
         Toast.makeText(getActivity(), "Profile picture updated", Toast.LENGTH_SHORT).show();

      /*  if(isInternetConnection.compareTo("no")==0) { // String status_report = ob.arrayList.get(0);
            Toast.makeText(getActivity(), "No internet connection, profile picture cannot be updated", Toast.LENGTH_SHORT).show();
        }
        */
    }

    public void refresh()
    {
        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentmypage.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/members.asmx/memberDetails", "userID=" + userID, "emailID=" +email};
        objasyntask.execute(strinputarray);
    }

    //for creating image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
