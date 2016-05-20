package com.shaadielephant.shaadielephant.mynotes_details;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.shaadielephant.shaadielephant.mynotes.databaseHelper;
import com.shaadielephant.shaadielephant.mynotes.fragment_mynotes;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

/**
 * Created by Callndata on 3/7/2016.
 */
public class fragment_mynotesdetails extends Fragment {
    EditText note_title,note_desc;
    Button note_save;
    Cursor c;
    databaseHelper db;
    String id,name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mynotesdetails, container, false);
        Bundle bundle = getArguments();
        db = new databaseHelper(getActivity());
        id = bundle.getString("id");
         name = bundle.getString("name");
        String desc = bundle.getString("desc");
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("My Page > " + name );
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        RelativeLayout rl1 = (RelativeLayout)v.findViewById(R.id.rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot", "reload fragment mynotes_details rl1");
            }
        });

        note_title = (EditText)v.findViewById(R.id.note_title);
        note_desc = (EditText)v.findViewById(R.id.note_desc);
        note_save = (Button)v.findViewById(R.id.note_save);

        note_title.setText(name.toString());
        note_desc.setText(desc.toString());

        note_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_drawer call = new main_drawer();
                call.TITLE_LIST.remove(call.TITLE_LIST.size() - 1);
                call.TITLE_LIST.remove(call.TITLE_LIST.size() - 1);
                ContentValues values = new ContentValues();
                values.put(databaseHelper.Mynotes.COL_NAME, note_title.getText().toString());
                values.put(databaseHelper.Mynotes.COL_DESC, note_desc.getText().toString());
                db.getWritableDatabase().update(databaseHelper.Mynotes.TABLE_NAME, values, databaseHelper.Mynotes.COL_ID + "=" + id, null);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_mynotes fc = new fragment_mynotes();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.add(R.id.mainContent, fc, "dsd").addToBackStack("xyz");
                ft.commit();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "My Page > " + name);
    }
}
