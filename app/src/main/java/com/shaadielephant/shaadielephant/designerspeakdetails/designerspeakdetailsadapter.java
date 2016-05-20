/*package com.shaadielephant.shaadielephant.designerspeakdetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

public class designerspeakdetailsadapter extends BaseAdapter{
    Context context;
    ArrayList<customclassdesignerspeakdetailsmessage> outputdata;
    ArrayList<customclassdesignerspeakdetailsresponse> outputresp;
    ArrayList<customclassdesignerspeakdetailscomments> outputcomment;
    customclassdesignerspeakdetailscombine output;
    customclassdesignerspeakdetailsmessage clsdata;
    fragmentdesignerspeakdetails hi;
    SharedPreferences shared;

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

            designerspeakdetailscommentsadapter objcommentadapter = new designerspeakdetailscommentsadapter(context,outputcomment);
            vholder.listViewDetailscomment.setAdapter(objcommentadapter);
        }


        vholder.text_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shared.getString("num", "0") != "0")
                { //showDialogToPostComments();
                 }
                else{
                    Toast.makeText(context, "You need to login first to comment", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return convertView;
    }
}

class designerholder {
    TextView textViewSubject,textViewInserDate,textViewmemberName,textViewmessage,text_review;
    ListView listViewDesignerResponse,listViewDetailscomment;

}

*/