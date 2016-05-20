package com.shaadielephant.shaadielephant.guestlist;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.guestlist_item.fragment_guestlist_item;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/1/2016.
 */
public class fragment_invitation extends Fragment implements networkresponse1,networkresponse2{
    private static Dialog dialog ;
    ListView list_guest;
    ImageView add;
    View v;
    SharedPreferences shared;
    String userID;
    ArrayList<String > ob_item_add;
    ArrayList<customclass_invitation> obArrayList;
    int invitationID;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentguestlist, container, false);

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page > Guest List");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment guestlist rl1");
            }
        });

        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        list_guest = (ListView)v.findViewById(R.id.list_guest);
        add= (ImageView)v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddInvitation();
            }
        });

        refresh();

        list_guest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                customclass_invitation test = obArrayList.get(position);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_guestlist_item fc = new fragment_guestlist_item();
                Bundle bundle = new Bundle();
                bundle.putInt("invitationID", test.getNum());
                bundle.putString("name", test.getFullName());
                fc.setArguments(bundle);
                // fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.add(R.id.mainContent, fc, "dsff").addToBackStack("xyz");
                ft.commit();
            }
        });


        return v;
    }


    //popup dialog to add invitation
    private void showDialogToAddInvitation() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_invitation);
        dialog.setCancelable(false);
        Button dialog_add_invitation = (Button) dialog.findViewById(R.id.dialog_add_invitation);
        final EditText invitation_name = (EditText) dialog.findViewById(R.id.invitation_name);
        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        dialog_add_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invitation_name.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask2 objasyntask2 = new asyntask2(getActivity());
                    asyntask2.setListner(fragment_invitation.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/userInvitationAdd", "userID=" + userID,
                            "eventName=" + invitation_name.getText().toString()};
                    objasyntask2.execute(strinputarray);

                }

            }
        });
        dialog.show();

    }


    // invitation list
    @Override
    public void getResponse1(String strinput) {


            invitation_jsonparser ob1 = new invitation_jsonparser();
            obArrayList = ob1.parser(strinput);
            if (obArrayList != null) {
                invitation_adapter obj_checklistadapter = new invitation_adapter(getActivity(), obArrayList);
                list_guest.setAdapter(obj_checklistadapter);

        }
    }

    //adding invitation
    @Override
    public void getResponse2(String strinput) {



            add_invitation_jsonparser obj = new add_invitation_jsonparser();
            ob_item_add = obj.parser(strinput);
            dialog.dismiss();
            String status_report = obj.arrayList.get(0);

            if (status_report != "0") {
                refresh();
                Toast.makeText(getActivity(), "Item added successfully", Toast.LENGTH_SHORT).show();


        }
    }

    public void refresh()
    {
        asyntask1 objasyntask1 = new asyntask1(getActivity());
        asyntask1.setListner(fragment_invitation.this);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/usersInvitationList", "userID=" + userID};
        objasyntask1.execute(strinputarray);
    }

}
