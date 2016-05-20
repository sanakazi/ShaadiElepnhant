package com.shaadielephant.shaadielephant.checklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

/**
 * Created by Callndata on 1/29/2016.
 */
public class checklistadapter extends BaseAdapter{
    Context c;
    ArrayList<customclasschecklist> checklistarray;

    checklistadapter(Context c, ArrayList<customclasschecklist> checklistarray)
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
        checklistholder ch;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowchecklist, null);
            ch= new checklistholder();
            ch.date=(TextView) convertView.findViewById(R.id.date);
            ch.name=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(ch);
        }
        else
        {
            ch= (checklistholder) convertView.getTag();
        }
        customclasschecklist test= checklistarray.get(position);
        if(test!=null) {
            ch.date.setText(test.getMarriageDate());
            ch.name.setText(test.getFullName());
        }

        return convertView;
    }
}

class checklistholder{
    TextView name, date;
}
