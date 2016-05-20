package com.shaadielephant.shaadielephant.designerspeakdetails;

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
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.vendorsdetails.add_comments_rating_jsonparser;
import com.shaadielephant.shaadielephant.vendorsdetails.asyntask4;
import com.shaadielephant.shaadielephant.vendorsdetails.networkresponse4;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;


import java.util.ArrayList;


public class fragmentdesignerspeakdetails extends Fragment implements networkresponse1 {
    customclassdesignerspeakdetailscombine objcombineclass;
    ArrayList<customclassdesignerspeakdetailsmessage> arrayListmessage;
    ArrayList<customclassdesignerspeakdetailsresponse> arrayListresponse;

    customclassdesignerspeakdetailsmessage objmessageclass;
    ListView listViewdesignerspeakdetails;
    TextView textViewSubject,textViewInserDate,textViewmemberName,textViewmessage;
    int speakid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retview= inflater.inflate(R.layout.fragmentdesignerspeakdetails,container,false);
        listViewdesignerspeakdetails= (ListView) retview.findViewById(R.id.listViewdesignerspeakdetails);

        LinearLayout l1 = (LinearLayout)retview.findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment fragmentdesignerspeakdetails l1");
            }
        });

        Bundle getdata= getArguments();
        speakid= getdata.getInt("num");
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Designer Speak");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));


        asyntask1 objasyn= new asyntask1(getActivity());
        asyntask1.setListner(fragmentdesignerspeakdetails.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/DesignerSpeak.asmx/askADesignerDetails","askADesignerID="+speakid};
        objasyn.execute(strinputarray);

        return retview;
    }

    @Override
    public void getResponse1(String strinput) {
        designerspeakdetailsparser objparser= new designerspeakdetailsparser();
        objcombineclass= objparser.parser(strinput);

        arrayListmessage = objcombineclass.getArrayListmessage();
        designerspeakdetailsadapter objdesignerspeakdetailsadapter = new designerspeakdetailsadapter(getActivity(), objcombineclass);
        listViewdesignerspeakdetails.setAdapter(objdesignerspeakdetailsadapter);

    }


    //----------------------------adapter class----------------------------------------

    public class designerspeakdetailsadapter extends BaseAdapter implements networkresponse4 {
        Context context;
        ArrayList<customclassdesignerspeakdetailsmessage> outputdata;
        ArrayList<customclassdesignerspeakdetailsresponse> outputresp;
        ArrayList<customclassdesignerspeakdetailscomments> outputcomment;
        customclassdesignerspeakdetailscombine output;
        customclassdesignerspeakdetailsmessage clsdata;
        fragmentdesignerspeakdetails hi;
        SharedPreferences shared;
        Dialog dialog;

        designerspeakdetailsadapter(Context c, customclassdesignerspeakdetailscombine  output)

        {
            Log.w("designer adapter", "yes");
            this.context=c;
            this.output=output;
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
            designerholder vholder;

            if(convertView==null){
                LayoutInflater li= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView= li.inflate(R.layout.singlerowdesignerspeakdetails, null);
                vholder= new designerholder();
                shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

                vholder.textViewSubject= (TextView) convertView.findViewById(R.id.textViewSubject);
                vholder.textViewInserDate= (TextView) convertView.findViewById(R.id.textViewInserDate);
                vholder.textViewmemberName= (TextView) convertView.findViewById(R.id.textViewmemberName);
                vholder.textViewmessage= (TextView) convertView.findViewById(R.id.textViewmessage);
                vholder.listViewDesignerResponse= (ListView) convertView.findViewById(R.id.listViewDesignerResponse);
                vholder.listViewDetailscomment= (ListView) convertView.findViewById(R.id.listViewDetailscomment);
                vholder.text_review=(TextView)convertView.findViewById(R.id.text_review);
                convertView.setTag(vholder);
            }
            else
            {
                vholder= (designerholder) convertView.getTag();
            }
            outputdata= output.getArrayListmessage();
            outputresp=output.getArrayListresponse();
            outputcomment =output.getArrayListcomments();


            clsdata= outputdata.get(position);
            if(clsdata!=null) {
                vholder.textViewSubject.setText(clsdata.getSubject());
                vholder.textViewInserDate.setText(clsdata.getInsertDate());
                vholder.textViewmemberName.setText(clsdata.getMemberName());
                vholder.textViewmessage.setText(clsdata.getMessage());
                designerspeakdetailsresponseadapter objrespadapter= new  designerspeakdetailsresponseadapter(context,outputresp);
                vholder.listViewDesignerResponse.setAdapter(objrespadapter);

                if(outputcomment!=null) {
                    designerspeakdetailscommentsadapter objcommentadapter = new designerspeakdetailscommentsadapter(context, outputcomment);
                    vholder.listViewDetailscomment.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, outputcomment.size()*200));
                    vholder.listViewDetailscomment.setAdapter(objcommentadapter);
                }
            }


            vholder.text_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shared.getString("num", "0") != "0")
                    {
                        showDialogToPostComments();
                    }
                    else{
                        Toast.makeText(context, "You need to login first to comment", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            return convertView;
        }

        //popup dialog to show comments
        private void showDialogToPostComments() {

            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_comments);
            dialog.setCancelable(false);
            Button comment_submit = (Button) dialog.findViewById(R.id.comment_submit);
            final EditText comments_text = (EditText) dialog.findViewById(R.id.comments_text);


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
                        asyntask4.setListner(designerspeakdetailsadapter.this);
                        String[] strinputarray = {"http://webservices.shaadielephant.com/RatingAndComments.asmx/AddRatinAndComments", "sectionName=" + "master_askADesigner",
                                "ID=" + clsdata.getNum() , "rateVal="+ "5" , "givencomments="+comments_text.getText().toString(),
                                "userID=" +(shared.getString("num","0")) };
                        objasyntask4.execute(strinputarray);

                    }

                }
            });
            dialog.show();

        }

        // for posting comments

        @Override
        public void getResponse4(String strinput) {
            add_comments_rating_jsonparser ob = new add_comments_rating_jsonparser();
            dialog.dismiss();
            Toast.makeText(context,"Your comment has been sent for approval" , Toast.LENGTH_SHORT).show();

            if(ob.arrayList!=null)
            {
                String status_report = ob.arrayList.get(0);
                if (status_report.compareTo("1") == 0) {
                }
            }
        }
    }

    class designerholder {
        TextView textViewSubject,textViewInserDate,textViewmemberName,textViewmessage,text_review;
        ListView listViewDesignerResponse,listViewDetailscomment;

    }
}
