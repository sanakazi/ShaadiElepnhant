package com.shaadielephant.shaadielephant.vendorsdetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

public class vendordetailscrolladaptercomment extends BaseAdapter{
    ArrayList<customclassvendordetailcomment> arraycomment;
    customclassvendordetailcomment clscomment;
    Context context;

    public vendordetailscrolladaptercomment(Context c, ArrayList<customclassvendordetailcomment> clscmnt)
    {
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
        holdercomment hldcomment;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowvendorcomment, null);
            hldcomment= new holdercomment();
            hldcomment.txtcommentor= (TextView) convertView.findViewById(R.id.textViewCommentorName);
            hldcomment.txtcomment= (TextView) convertView.findViewById(R.id.textViewSingleComment);
            hldcomment.ratingBarComment=(RatingBar) convertView.findViewById(R.id.ratingBarComment);
            hldcomment.getratingnum= (TextView) convertView.findViewById(R.id.getratingnum);
            convertView.setTag(hldcomment);
        }
        else
        {
            hldcomment= (holdercomment) convertView.getTag();
        }

        clscomment= arraycomment.get(position);
        hldcomment.txtcommentor.setText(clscomment.userName);
        hldcomment.txtcomment.setText(clscomment.givencomments);
        hldcomment.ratingBarComment.setRating(Float.parseFloat(clscomment.voterate));
        hldcomment.getratingnum.setText(clscomment.voterate);

        Log.w("adapter size is ", arraycomment.size() + " ");
        Log.w("adapter ", position + " " + clscomment.userName + "--" + clscomment.givencomments);
//        hldcomment.ratingBarComment.setForeground();
        return convertView;
    }
}
class holdercomment
{

    TextView txtcomment,txtcommentor,getratingnum;
    RatingBar ratingBarComment;
}
