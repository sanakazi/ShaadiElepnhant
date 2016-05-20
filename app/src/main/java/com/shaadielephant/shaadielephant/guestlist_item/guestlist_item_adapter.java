package com.shaadielephant.shaadielephant.guestlist_item;

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
 * Created by Callndata on 3/2/2016.
 */

public class guestlist_item_adapter extends BaseAdapter implements networkresponse3{
    Context c;
    ArrayList<customclass_guestlist_item> checklistarray;
    ArrayList<String> ob_del;
    SharedPreferences shared;
    String userID;
    guestlist_item_adapter(Context c, ArrayList<customclass_guestlist_item> checklistarray)
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
        guestlistholder ch;
        shared = c.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        userID = (shared.getString("num","0"));

        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerow_guestlist_item, null);
            ch= new guestlistholder();
            ch.fullName=(TextView) convertView.findViewById(R.id.fullName);
            ch.contact=(TextView) convertView.findViewById(R.id.contact);
            ch.email=(TextView) convertView.findViewById(R.id.email);
            ch.img_del_invite= (ImageView)convertView.findViewById(R.id.img_del_invite);

            convertView.setTag(ch);
        }
        else
        {
            ch= (guestlistholder) convertView.getTag();
        }
        customclass_guestlist_item test= checklistarray.get(position);
        if(test!=null) {
            ch.fullName.setText(test.getFullName());
            ch.contact.setText(test.getContactNo());
            ch.email.setText(test.getEmailID());
        }
        MyTag myTagcat=new MyTag(test.getInvitationID(),test.getNum(),position);
        ch.img_del_invite.setTag(myTagcat);


        //deleting items locally
        ch.img_del_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTag myTagcat = (MyTag) v.getTag();
                int invitationNum = myTagcat.invitation_num;
                int num = myTagcat.num;
                int pos = myTagcat.pos;
                Log.w("invitation & invitee ", invitationNum + " , " + num + "  position is  " + pos);
                delete_invitation(invitationNum,num,pos);
            }
        });

        return convertView;
    }

    // deleting invitation
    @Override
    public void getResponse3(String strinput) {
        guestlist_item_del_jsonparser obj = new guestlist_item_del_jsonparser();
        ob_del = obj.parser(strinput);
        String status_report = obj.arrayList.get(0);
        //  if(status_report=="1") {
        Toast.makeText(c, "Item deleted successfully", Toast.LENGTH_SHORT).show();

        //  }
    }

    class MyTag{

        int invitation_num;
        int num;
        int pos;



        public MyTag()
        {   invitation_num=0;
            num=0;
            pos=0;

        }

        public MyTag( int invitation_num, int num,int pos)
        {
            this.invitation_num = invitation_num;
            this.num=num;
            this.pos=pos;
        }

    }



    public void delete_invitation(int invitationNumber , int number,int position){
        final int invitationNum = invitationNumber;
        final int num = number;
        final int pos = position;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setMessage("Are you sure you want to delete?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deletefromService(invitationNum,num);
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

    public void  deletefromService(int invitationNumber , int number){
        asyntask3 objasyntask3 = new asyntask3(c);
        asyntask3.setListner(guestlist_item_adapter.this);
        Log.w("Invitation Num = ",invitationNumber + " invitee number = "+ number);
        String[] strinputarray = {"http://webservices.shaadielephant.com/usersInvitation.asmx/userInvitationInviteeRemove", "userID=" + userID,
                "invitationID=" + invitationNumber, "inviteeID="+number};
        objasyntask3.execute(strinputarray);
    }
}

class guestlistholder{
    TextView fullName,contact,email;
    ImageView img_del_invite;
}