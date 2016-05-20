package com.shaadielephant.shaadielephant.mydesignerspeaklist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

/**
 * Created by Callndata on 3/8/2016.
 */
public class mydesignerspeaklist_adapter extends BaseAdapter implements networkresponse3 {
    Context c;
    ArrayList<customclass_mydesignerspeaklist> mydesigner_array;
    ArrayList<String> ob_del;
    SharedPreferences shared;
    String userID;
    mydesignerspeaklist_adapter(Context c, ArrayList<customclass_mydesignerspeaklist> mydesigner_array)
    {
        this.c=c;
        this.mydesigner_array=mydesigner_array;
    }

    @Override
    public int getCount() {
        return mydesigner_array.size();
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
        mydesignerspeaklistholder ch;
        shared = c.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerow_mydesignerspeaklist, null);
            ch= new mydesignerspeaklistholder();
            ch.name=(TextView) convertView.findViewById(R.id.name);
            ch.img_del= (ImageView)convertView.findViewById(R.id.img_del);

            convertView.setTag(ch);
        }
        else
        {
            ch= (mydesignerspeaklistholder) convertView.getTag();
        }
        customclass_mydesignerspeaklist test= mydesigner_array.get(position);
        if(test!=null) {
            ch.name.setText(test.getAuthorName());
        }
        MyTag myTagcat=new MyTag(test.getNum(),position);

        ch.img_del.setTag(myTagcat);
        //deleting items locally
        ch.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTag myTagcat = (MyTag) v.getTag();
                int num = myTagcat.num;
                int pos = myTagcat.pos;
                Log.w("num and pos are ", num + " , " + pos);
                delete_invitation(num,pos);


            }
        });

        return convertView;
    }
    // deleting invitation
    @Override
    public void getResponse3(String strinput) {
        del_mydesignerspeaklist_jsonparser obj = new del_mydesignerspeaklist_jsonparser();
        ob_del = obj.parser(strinput);
        String status_report = obj.arrayList.get(0);
        //  if(status_report=="1") {
        Toast.makeText(c, "Item deleted successfully", Toast.LENGTH_SHORT).show();

        //  }
    }

    class MyTag
    {   int num;
        int pos;



        public MyTag()
        {
            num=0;
            pos=0;

        }

        public MyTag(int num,int pos)
        {
            this.num=num;
            this.pos=pos;
        }

    }



    public void delete_invitation(int number,int position){
        final int num = number;
        final int pos = position;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deletefromService(num);
                mydesigner_array.remove(pos);
                notifyDataSetChanged();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void  deletefromService(int number){
        asyntask3 objasyntask3 = new asyntask3(c);
        asyntask3.setListner(mydesignerspeaklist_adapter.this);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersDesignerSpeak.asmx/usersDesignerSpeakRemove","num="+number, "userID=" + userID};
        objasyntask3.execute(strinputarray);
    }
}

class mydesignerspeaklistholder{
    TextView name;
    ImageView img_del;
}
