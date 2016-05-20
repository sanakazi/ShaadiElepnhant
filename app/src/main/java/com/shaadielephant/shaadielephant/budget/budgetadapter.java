package com.shaadielephant.shaadielephant.budget;

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
public class budgetadapter extends BaseAdapter{
    Context c;
    ArrayList<customclassbudget> budgetarray;

    budgetadapter(Context c, ArrayList<customclassbudget> budgetarray)
    {
        this.c=c;
        this.budgetarray=budgetarray;
    }


    @Override
    public int getCount() {
        return budgetarray.size();
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
        budgetholder bg;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowbudget, null);
            bg= new budgetholder();
            bg.date=(TextView) convertView.findViewById(R.id.date);
            bg.name=(TextView) convertView.findViewById(R.id.name);
            bg.budget=(TextView) convertView.findViewById(R.id.budget);
            convertView.setTag(bg);
        }
        else
        {
            bg= (budgetholder) convertView.getTag();
        }
        customclassbudget test= budgetarray.get(position);
        if(test!=null) {
            bg.date.setText(test.getMarriageDate());
            bg.name.setText(test.getFullName());
            bg.budget.setText(String.valueOf(test.getTotalBudget()));
        }

        return convertView;
    }
}

class budgetholder{
    TextView name, date,budget;
}
