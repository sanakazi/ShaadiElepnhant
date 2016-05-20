package com.shaadielephant.shaadielephant.guestlist_item;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielephant.registration.registration_Activity;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/2/2016.
 */
public class fragment_guestlist_item extends Fragment implements networkresponse1,networkresponse2{
    private static Dialog dialog ;
    ListView list_guest_item;
    ImageView add;
    View v;
    SharedPreferences shared;
    String userID,name;
    int invitationID;
    ArrayList<customclass_guestlist_item> obArrayList;
    ArrayList<String>ob_item_add;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentguestlist_item, container, false);

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragmentguestlist_item_rl1");
            }
        });

        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        Bundle bundle = getArguments();
        invitationID = bundle.getInt("invitationID");
        name = bundle.getString("name");
        Log.w("invitationID = ", " "+ invitationID);

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page > " + name);
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        list_guest_item = (ListView)v.findViewById(R.id.list_guest_item);
        add= (ImageView)v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddGuest();
            }
        });

        refresh();
        return v;
    }

    public void showDialogToAddGuest(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_guest_invite);
        dialog.setCancelable(false);
        Button dialog_add_guest = (Button) dialog.findViewById(R.id.dialog_add_guest);
        final EditText dialog_guest_name = (EditText) dialog.findViewById(R.id.dialog_guest_name);
        final EditText dialog_guest_contact = (EditText) dialog.findViewById(R.id.dialog_guest_contact);
        final EditText dialog_guest_email = (EditText) dialog.findViewById(R.id.dialog_guest_email);
        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final registration_Activity call = new registration_Activity();


        dialog_add_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog_guest_name.getText().toString().length() == 0||dialog_guest_contact.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    asyntask2 objasyntask2 = new asyntask2(getActivity());
                    asyntask2.setListner(fragment_guestlist_item.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/userInvitationInviteeDetailsAdd",
                            "userID=" + userID, "invitationID="+invitationID, "fullName=" + dialog_guest_name.getText().toString(),
                            "contactNo=" + dialog_guest_contact.getText().toString(),"emailID=" + dialog_guest_email.getText().toString()};
                    objasyntask2.execute(strinputarray);

                }

            }
        });
        dialog.show();

    }

    public void refresh()
    {
        asyntask1 objasyntask1 = new asyntask1(getActivity());
        asyntask1.setListner(fragment_guestlist_item.this);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/userInvitationInviteeList", "userID=" + userID
                , "invitationID=" + invitationID};
        objasyntask1.execute(strinputarray);
    }

    //show guestlist
    @Override
    public void getResponse1(String strinput) {

            guestlist_item_jsonparser ob1 = new guestlist_item_jsonparser();
            obArrayList = ob1.parser(strinput);
            if (obArrayList != null) {
                guestlist_item_adapter obj_checklistadapter = new guestlist_item_adapter(getActivity(), obArrayList);
                list_guest_item.setAdapter(obj_checklistadapter);

        }
    }
 // adding guests to list
    @Override
    public void getResponse2(String strinput) {

            guestlist_item_add_jsonparser obj = new guestlist_item_add_jsonparser();
            ob_item_add = obj.parser(strinput);
            dialog.dismiss();
            String status_report = obj.arrayList.get(0);
            if (status_report != "0") {
                refresh();
                Toast.makeText(getActivity(), "Guest added successfully", Toast.LENGTH_SHORT).show();


        }
    }

}
