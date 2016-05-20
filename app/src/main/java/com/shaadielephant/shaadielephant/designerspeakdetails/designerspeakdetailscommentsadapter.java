package com.shaadielephant.shaadielephant.designerspeakdetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;


public class designerspeakdetailscommentsadapter extends BaseAdapter {

    ArrayList<customclassdesignerspeakdetailscomments> arraycomment;
    customclassdesignerspeakdetailscomments clscomment;
    Context context;

    public designerspeakdetailscommentsadapter(Context c, ArrayList<customclassdesignerspeakdetailscomments> clscmnt)
    {  Log.w("designer coments ", "yes");
        this.context=c;
        this.arraycomment=clscmnt;
    }
    @Override
    public int getCount() {
        return arraycomment.size();
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
        holderdesignerdtailscomment hldcomment;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowdesignerspeakdetailscomments, null);
            hldcomment= new holderdesignerdtailscomment();
            hldcomment.name= (TextView) convertView.findViewById(R.id.name);
            hldcomment.comment= (TextView) convertView.findViewById(R.id.comments);
            convertView.setTag(hldcomment);
        }
        else
        {
            hldcomment= (holderdesignerdtailscomment) convertView.getTag();
        }

        clscomment= arraycomment.get(position);

       if(clscomment!=null) {
                Log.w("position is  ", position + "  Username " + clscomment.userName);
                hldcomment.name.setText(clscomment.userName);
                hldcomment.comment.setText(clscomment.givencomments);

        }

        return convertView;
    }
}
class holderdesignerdtailscomment
{

    TextView name, comment;

}
