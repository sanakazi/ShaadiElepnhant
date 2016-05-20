package com.shaadielephant.shaadielephant.budget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

import java.util.ArrayList;

import android.widget.DatePicker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.support.v4.app.DialogFragment;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.budget_item.fragmentbudget_item;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

public class fragmentbudget extends Fragment implements networkresponse1,networkresponse2 {
    ListView listbudget;
    SharedPreferences shared;
    ArrayList<customclassbudget> objArraylist;
    private static View v;
    private static int YEAR, MONTH,DATE;
    private static String[] arrMonth;
    private static Dialog dialog ;
    String userID,marriageDate,BudgetfullName;
    int eventID,TotalBudget,plannedCost,availbleAmount,actualAmount,paidAmount,pendingAmount;
    ArrayList<String> objArraylistadd;
    RelativeLayout l2;
    RelativeLayout r1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragmentbudget, container, false);
        listbudget=(ListView)v.findViewById(R.id.listbudget);

        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));
        //For changing actionbar title
       // ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Budget");

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > Budget");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
       r1 = (RelativeLayout)v.findViewById(R.id.rl1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment budget r1");
            }
        });
        l2 = (RelativeLayout)v.findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Don not reload ", " fragment");
            }
        });

        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentbudget.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersBudgetList.asmx/usersBudgetLists","userID="+userID};
        objasyntask.execute(strinputarray);

        init();

        listbudget.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                customclassbudget test = objArraylist.get(position);
                marriageDate = test.getMarriageDate();
                BudgetfullName = test.getFullName();
                eventID = test.getNum();
                TotalBudget = test.getTotalBudget();
                plannedCost = test.getNum();
                availbleAmount = test.getTotalBudget();
                actualAmount = test.getNum();
                paidAmount = test.getTotalBudget();
                pendingAmount = test.getNum();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragmentbudget_item obj_budget_item = new fragmentbudget_item();

                Bundle b = new Bundle();
                b.putInt("eventID", eventID);
                b.putString("BudgetfullName", BudgetfullName);
                b.putString("marriageDate", marriageDate);
                b.putInt("TotalBudget", TotalBudget);
                b.putInt("plannedCost", plannedCost);
                b.putInt("availbleAmount", availbleAmount);
                b.putInt("actualAmount", actualAmount);
                b.putInt("paidAmount", paidAmount);
                b.putInt("pendingAmount", pendingAmount);


                obj_budget_item.setArguments(b);

                ft.add(R.id.mainContent, obj_budget_item, "abc").addToBackStack("lmc");
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

        ((ImageView) v.findViewById(R.id.budget_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddBudget();
            }
        });
    }




    /**
     * function to show pop up dialog
     */
    private void showDialogToAddBudget(){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_budget);
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
       final EditText dialog_event_name = (EditText)dialog.findViewById(R.id.dialog_event_name);
       final  EditText dialog_event_date =  (EditText) dialog.findViewById(R.id.dialog_event_date);
        final  EditText dialog_total_budget =  (EditText) dialog.findViewById(R.id.dialog_total_budget);

                ((Button) dialog.findViewById(R.id.dialog_add_budget)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //write your ws implementation here
                        if(dialog_event_name.getText().toString().length() == 0||dialog_event_date.getText().toString().length() == 0||dialog_total_budget.getText().toString().length() == 0 )
                        {  Log.w("inside if",  "tu");
                            Toast.makeText(getActivity(),"Please insert all the fields",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            asyntask2 objasyntask = new asyntask2(getActivity());
                            asyntask2.setListner(fragmentbudget.this);
                            Log.w("date is", dialog_event_date.getText().toString());
                            String[] strinputarray = {"http://webservices.shaadielephant.com/usersBudgetList.asmx/userBudgetAdd", "userID=" + userID, "eventName=" + dialog_event_name.getText().toString()
                                    , "TotalBudget=" + dialog_total_budget.getText().toString(), "eventDate=" + dialog_event_date.getText().toString()};

                            Log.w("string login budget", strinputarray + "");
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

        //to display list
    @Override
    public void getResponse1(String strinput) {
        budgetjsonparser obj_budgetjsonparser = new budgetjsonparser();
        objArraylist = obj_budgetjsonparser.parser(strinput);
        if (objArraylist != null) {
            budgetadapter obj_budgetadapter = new budgetadapter(getActivity(), objArraylist);
            listbudget.setAdapter(obj_budgetadapter);

        }

    }
    //to add budget
    @Override
    public void getResponse2(String strinput) {

        budgetaddjsonparser obj_budgetaddjsonparser = new budgetaddjsonparser();
        objArraylistadd =obj_budgetaddjsonparser.parser(strinput);
        dialog.dismiss();

         Toast.makeText(getActivity(),"Budget created" , Toast.LENGTH_SHORT).show();
         asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentbudget.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/usersBudgetList.asmx/usersBudgetLists","userID="+userID};
        objasyntask.execute(strinputarray);

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
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Planning > Budget");

    }
}
