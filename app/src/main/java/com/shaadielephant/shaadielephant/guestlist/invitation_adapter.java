package com.shaadielephant.shaadielephant.guestlist;

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
 * Created by Callndata on 3/1/2016.
 */

public class invitation_adapter extends BaseAdapter implements networkresponse3{
    Context c;
    ArrayList<customclass_invitation> checklistarray;
    ArrayList<String> ob_del;
    SharedPreferences shared;
    String userID;
    invitation_adapter(Context c, ArrayList<customclass_invitation> checklistarray)
    {
        this.c=c;
        this.checklistarray=checklistarray;
    }




    @Override
    public int getCount() {
        return checklistarray.size();
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
        invitationholder ch;
        shared = c.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowchecklist_item_group, null);
            ch= new invitationholder();
            ch.name=(TextView) convertView.findViewById(R.id.categoryname);
            ch.img_del_cat= (ImageView)convertView.findViewById(R.id.img_del_cat);

            convertView.setTag(ch);
        }
        else
        {
            ch= (invitationholder) convertView.getTag();
        }
        customclass_invitation test= checklistarray.get(position);
        if(test!=null) {
            ch.name.setText(test.getFullName());
        }
        MyTag myTagcat=new MyTag(test.getNum(),position);
        ch.img_del_cat.setTag(myTagcat);
        //deleting items locally
        ch.img_del_cat.setOnClickListener(new View.OnClickListener() {
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
        del_jsonparser obj = new del_jsonparser();
        ob_del = obj.parser(strinput);
        String status_report = obj.arrayList.get(0);
      //  if(status_report=="1") {
            Toast.makeText(c , "Item deleted successfully", Toast.LENGTH_SHORT).show();

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
                checklistarray.remove(pos);
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
        asyntask3.setListner(invitation_adapter.this);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/userInvitationRemove", "userID=" + userID, "invitationID=" + number};
        objasyntask3.execute(strinputarray);
    }
}

class invitationholder{
    TextView name;
    ImageView img_del_cat;
}
