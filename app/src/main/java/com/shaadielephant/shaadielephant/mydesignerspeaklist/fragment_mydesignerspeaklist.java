package com.shaadielephant.shaadielephant.mydesignerspeaklist;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/8/2016.
 */
public class fragment_mydesignerspeaklist extends Fragment implements  networkresponse1, networkresponse2{
    private static Dialog dialog ;
    ListView list_mydesignerspeaklist;
    ImageView add;
    View v;
    SharedPreferences shared;
    String userID,userName;
    ArrayList<String > ob_item_add;
    ArrayList<customclass_mydesignerspeaklist> obArrayList;
    int invitationID;
    int showinhome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mydesignerspeaklist, container, false);

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page > Designer Speak");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        userName=(shared.getString("name","0"));

        list_mydesignerspeaklist = (ListView)v.findViewById(R.id.list_mydesignerspeaklist);
        add= (ImageView)v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAskExpert();
            }
        });

        refresh();

        list_mydesignerspeaklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




              /*  customclass_mydesignerspeaklist objclass= obArrayList.get(position);
                int index= objclass.getNum();
                FragmentManager fm =getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                fragmentdesignerspeakdetails_new fragdesignerspeakdetails= new fragmentdesignerspeakdetails_new();
                Bundle bundledesignerspeak= new Bundle();
                bundledesignerspeak.putInt("num",index);
                fragdesignerspeakdetails.setArguments(bundledesignerspeak);
                ft.replace(R.id.mainContent,fragdesignerspeakdetails,"DesignerSpeakDetails").addToBackStack("efg");
                ft.commit();

*/

            }
        });


        return v;
    }


    //popup dialog to ask expert
    private void showDialogToAskExpert() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_ask_expert);
        dialog.setCancelable(false);
        Button add_ask_expert = (Button) dialog.findViewById(R.id.add_ask_expert);
        final EditText subject = (EditText) dialog.findViewById(R.id.subject);
        final  EditText desc = (EditText)dialog.findViewById(R.id.brief);
        final Button img_private = (Button)dialog.findViewById(R.id.img_private);
        final  Button img_public = (Button)dialog.findViewById(R.id.img_public);
        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        img_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            img_private.setBackgroundResource(R.drawable.btn_rounded_left_pink);
                img_public.setBackgroundResource(R.drawable.btn_rounded_right);
                showinhome=0;

            }
        });

        img_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_public.setBackgroundResource(R.drawable.btn_rounded_right_pink);
                img_private.setBackgroundResource(R.drawable.btn_rounded_left);
                showinhome=1;
            }
        });



        add_ask_expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subject.getText().toString().length() == 0 ||desc.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    asyntask2 objasyntask2 = new asyntask2(getActivity());
                    asyntask2.setListner(fragment_mydesignerspeaklist.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersDesignerSpeak.asmx/userDesignerSpeakAdd",
                            "AuthorName="+userName,"brief="+subject.getText().toString(),
                            "description="+desc.getText().toString(),
                            "showinhome="+showinhome,
                            "userID=" + userID};
                    objasyntask2.execute(strinputarray);

                }

            }
        });
        dialog.show();

    }


    // list
    @Override
    public void getResponse1(String strinput) {
        mydesignerspeaklist_jsonparser ob1 = new mydesignerspeaklist_jsonparser();
        obArrayList = ob1.parser(strinput);
        if(obArrayList!=null) {
            mydesignerspeaklist_adapter obj_checklistadapter = new mydesignerspeaklist_adapter(getActivity(), obArrayList);
            list_mydesignerspeaklist.setAdapter(obj_checklistadapter);
        }

    }

    //ask expert
    @Override
    public void getResponse2(String strinput) {

        Log.w("string ", strinput);
        add_mydesignerspeaklist_jsonparser obj = new add_mydesignerspeaklist_jsonparser();
        ob_item_add = obj.parser(strinput);
        dialog.dismiss();
        String status_report = obj.arrayList.get(0);
        if(status_report!="0") {
            refresh();
            Toast.makeText(getActivity(), "Your question has been posted. Our designer will get respond soon.", Toast.LENGTH_LONG).show();

        }


    }

    public void refresh()
    {
        asyntask1 objasyntask1 = new asyntask1(getActivity());
        asyntask1.setListner(fragment_mydesignerspeaklist.this);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersDesignerSpeak.asmx/usersDesignerSpeakList", "userID=" + userID};
        objasyntask1.execute(strinputarray);
    }


}
