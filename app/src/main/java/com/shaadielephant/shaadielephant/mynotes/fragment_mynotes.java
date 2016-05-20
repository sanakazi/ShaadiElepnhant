package com.shaadielephant.shaadielephant.mynotes;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.mynotes_details.fragment_mynotesdetails;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

/**
 * Created by Callndata on 3/2/2016.
 */
public class fragment_mynotes extends Fragment{
    View v;
    ListView mynotes_list;
    mynotes_adapter adapter;
    private static Dialog dialog ;
    ImageView add_new_note;
    Cursor c;
    databaseHelper db;
    EditText dialog_mynote_name;
    Button dialog_add_new_mynote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mynotes, container, false);
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page > My notes ");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        mynotes_list = (ListView)v.findViewById(R.id.mynotes_list);
        add_new_note = (ImageView)v.findViewById(R.id.add_new_note);
        db = new databaseHelper(getActivity());
        asyntask_mynotes task = new asyntask_mynotes();
        task.execute();

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment my_notes rl1");
            }
        });

        add_new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddMyNotes();
            }
        });

        mynotes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.w("Listview position", " " + c.getPosition() + " and id is = " + id + "name is " + c.getString(c.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_NAME)));
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_mynotesdetails fc = new fragment_mynotesdetails();
                Bundle bundle = new Bundle();
                bundle.putString("id" ,  c.getString(c.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_ID)));
                bundle.putString("name" , c.getString(c.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_NAME)));
                bundle.putString("desc" , c.getString(c.getColumnIndexOrThrow(databaseHelper.Mynotes.COL_DESC)));
                fc.setArguments(bundle);
                ft.add(R.id.mainContent, fc, "dsd").addToBackStack("xyz");
                ft.commit();

            }
        });


        return v;
    }


    //popup dialog to add invitation
    public void showDialogToAddMyNotes() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_mynotes);
        dialog.setCancelable(false);
        dialog_mynote_name = (EditText) dialog.findViewById(R.id.dialog_mynote_name);
         dialog_add_new_mynote = (Button)dialog.findViewById(R.id.dialog_add_new_mynote);
        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


       dialog_add_new_mynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Yes you ", "clicked right");

                if (dialog_mynote_name.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                } else {



                   ContentValues values = new ContentValues();
                    values.put(databaseHelper.Mynotes.COL_NAME, dialog_mynote_name.getText().toString());
                    values.put(databaseHelper.Mynotes.COL_DESC, " ");
                    db.getWritableDatabase().insertOrThrow(databaseHelper.Mynotes.TABLE_NAME, null, values);
                    dialog.dismiss();
                    asyntask_mynotes task = new asyntask_mynotes();
                    task.execute();

                }

            }
        }

       );

        dialog.show();

    }

     public class asyntask_mynotes extends  AsyncTask
    {	ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage("Please wait...");
            pd.show();
        }

        @Override
        protected void onPostExecute(Object o) {

            adapter =  new mynotes_adapter(getActivity(), c,0);
            mynotes_list.setAdapter(adapter);
            pd.dismiss();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            c = db.getReadableDatabase().query(databaseHelper.Mynotes.TABLE_NAME, null, null, null, null, null, null);
            return null;
        }
    }

//adapter

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
                    asyntask_mynotes task = new asyntask_mynotes();
                    task.execute();
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();


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

}
