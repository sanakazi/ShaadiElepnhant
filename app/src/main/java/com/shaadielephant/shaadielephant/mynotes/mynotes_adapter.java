/*
package com.shaadielephant.shaadielephant.mynotes;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;

import java.util.ArrayList;

    public class mynotes_adapter extends CursorAdapter
    {
        private LayoutInflater cursorInflater;
        String fcount;
        Context context;
        mynotes holder;
        databaseHelper db;
        Cursor c ;

        public mynotes_adapter(Context context, Cursor c, int flags) {
            super(context, c, 0);
            // TODO Auto-generated constructor stub
            this.context = context;
            this.c = c;

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // TODO Auto-generated method stub
            View root = LayoutInflater.from(context).inflate(R.layout.singlerow_mynotes, parent, false);
            holder = new  mynotes();
            holder.text1 = (TextView)root.findViewById(R.id.text1);
            holder.del_note = (ImageView)root.findViewById(R.id.del_note);
            root.setTag(holder);
            return root;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // TODO Auto-generated method stub
            db = new databaseHelper(context);
            holder = (mynotes) view.getTag();
            String key = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_ID));
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_NAME));
            String fdesc = cursor.getString(cursor.getColumnIndexOrThrow( databaseHelper.Mynotes.COL_DESC));
            Log.w("key = ",key + " name= "+fname + " desc= "+ fdesc);
            holder.text1.setText(fname);



            class MyTag{
                String key1,fname1,fdesc1;

                public MyTag(){

                    key1= null;
                    fname1=null;
                    fdesc1 = null;

                }

                public MyTag(String key1, String fname1,String fdesc1)
                {
                    this.key1=key1;
                    this.fname1 = fname1;
                    this.fdesc1=fdesc1;

                }

                public MyTag(String key1, String fcount1)
                {
                    this.key1=key1;
                }
            }

            MyTag myTag = new MyTag(key,fname,fdesc);
            holder.del_note.setTag(myTag);
            holder.del_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyTag myTag = (MyTag) v.getTag();
                    String newKey = myTag.key1;
                    delete_item(newKey);

                }
            });

        }

        // dialogbox to confirm if you want to delete item
        public void delete_item(String a){
            Log.w("key to delete ", a + " ");
            final String ky = a;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setMessage("Are you sure you want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                  db.getWritableDatabase().delete(databaseHelper.Mynotes.TABLE_NAME, databaseHelper.Mynotes.COL_ID + "=" + ky, null);
                  //  fragment_mynotes f = new fragment_mynotes();
                 //   f.delete_note(ky);
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


    }

class mynotes {
    TextView text1;
    ImageView del_note;
}
 */