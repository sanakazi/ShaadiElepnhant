package com.shaadielephant.shaadielephant.checklist;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.checklist_item.fragmentchecklist_item;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public  class fragmentchecklist extends Fragment implements networkresponse1,networkresponse2 {
    ListView listchecklist;
    ImageView add;
    private static View v;
    SharedPreferences shared;
    ArrayList<customclasschecklist>objArraylist;
    FragmentManager fm;
    private static int YEAR, MONTH,DATE;
    private static String[] arrMonth;
    private static Dialog dialog ;
    ArrayList<String> objArraylistadd;
    String userID;
    int eventID;
    String checklistName,checklistDate;
    checklistadapter adapter;
    RelativeLayout l2;


    RelativeLayout rl1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragmentchecklist,container,false);
        listchecklist=(ListView)v.findViewById(R.id.listchecklist);
        add= (ImageView)v.findViewById(R.id.add);
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        //For changing actionbar title
      //  ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Checklist");

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > Checklist");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        Log.w("Inside Fragment ", "cHECKLIST ");
        l2 = (RelativeLayout)v.findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Don not reload ", " fragment");
            }
        });

        rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment checklist rl1");
            }
        });


        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentchecklist.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersCheckList.asmx/usersCheckLists","userID="+userID};
        objasyntask.execute(strinputarray);
        fm = getActivity().getSupportFragmentManager();

        init();

        listchecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                customclasschecklist test= objArraylist.get(position);
                eventID = test.getNum();
                checklistName = test.getFullName();
                checklistDate=test.getMarriageDate();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentchecklist_item obj_checklistItem = new fragmentchecklist_item();
                Bundle bundleChecklist_item= new Bundle();
                bundleChecklist_item.putInt("eventID", eventID);
                bundleChecklist_item.putString("checklistName", checklistName);
                bundleChecklist_item.putString("checklistDate", checklistDate);
                obj_checklistItem.setArguments(bundleChecklist_item);
               // fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.add(R.id.mainContent, obj_checklistItem, "abc").addToBackStack("23");
                ft.commit();

            }
        });
        return v;
    }

    /**
     * initialisation of this fragment
     */
    private void init(){

        Resources res = getResources();
        arrMonth = res.getStringArray(R.array.month);

        ((ImageView) v.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddChecklist();
            }
        });
    }


    /**
     * function to show pop up dialog
     */
    private void showDialogToAddChecklist(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_checklist);
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
        final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
        final  EditText dialog_event_date =  (EditText) dialog.findViewById(R.id.dialog_event_date);

        ((Button) dialog.findViewById(R.id.dialog_add_checklist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write your ws implementation here
                if(dialog_event_name.getText().toString().length() == 0||dialog_event_date.getText().toString().length() == 0 )
                {
                    Toast.makeText(getActivity(),"Please insert all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    asyntask2 objasyntask = new asyntask2(getActivity());
                    asyntask2.setListner(fragmentchecklist.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/usersChecklist.asmx/userCheckListAdd", "userID=" + userID, "eventName=" + dialog_event_name.getText().toString()
                            , "eventDate=" + dialog_event_date.getText().toString()};
                    objasyntask.execute(strinputarray);
                }

            }
        });

        dialog_event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });
        ((ImageView) dialog.findViewById(R.id.dialog_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }



    /**
     * date picker dialog
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
            YEAR = year;
            MONTH = month+1;
            DATE = day;

            try {
                Date CurrentDate = sdf.parse(""+calendar.get(Calendar.YEAR) + "/"+(calendar.get(Calendar.MONTH)+1) + "/"+calendar.get(Calendar.DAY_OF_MONTH)) ;
                Date SelectedDate = sdf.parse( ""+year + "/"+(month+1) + "/"+day);

                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                if( SelectedDate.after(CurrentDate)){
                    ((EditText) dialog.findViewById(R.id.dialog_event_date)).setText(day+"-"+arrMonth[month]+"-"+year);
                }else {
                    Toast.makeText(getActivity(), "Please select future date", Toast.LENGTH_SHORT).show();
                }

            }catch(ParseException pe){
                pe.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Checklist");
        Log.w("log Checklist resume ", "true");

    }

 // display checklist
    @Override
    public void getResponse1(String strinput) {
        Log.w("Inside response 1", " true");
            checklistjsonparser obj_checklistjsonparser = new checklistjsonparser();
            objArraylist = obj_checklistjsonparser.parser(strinput);
            if (objArraylist != null) {
                checklistadapter obj_checklistadapter = new checklistadapter(getActivity(), objArraylist);
                listchecklist.setAdapter(obj_checklistadapter);
            }

    }

    //add checklist
    @Override
    public void getResponse2(String strinput) {

            Log.w("Inside response 2", " true");
            checklistadd_jsonparser obj_checklistadd_jsonparser = new checklistadd_jsonparser();
            objArraylistadd = obj_checklistadd_jsonparser.parser(strinput);
            dialog.dismiss();
            Toast.makeText(getActivity(),"Checklist created" , Toast.LENGTH_SHORT).show();
           asyntask1 objasyntask = new asyntask1(getActivity());
            asyntask1.setListner(fragmentchecklist.this);
            String[] strinputarray = {"http://webservices.shaadielephant.com/usersCheckList.asmx/usersCheckLists", "userID=" + userID};
            objasyntask.execute(strinputarray);


    }


}
