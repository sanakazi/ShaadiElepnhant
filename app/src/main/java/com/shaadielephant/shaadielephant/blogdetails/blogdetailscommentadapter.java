package com.shaadielephant.shaadielephant.blogdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

public class blogdetailscommentadapter extends BaseAdapter {
    ArrayList<customclassblogdetailscomment> arraycomment;
    customclassblogdetailscomment clscomment;
    Context context;

    blogdetailscommentadapter(Context c, ArrayList<customclassblogdetailscomment> clscmnt) {
        this.context = c;
        this.arraycomment = clscmnt;
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

        holderblogcomment holderblogcomment;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowblogdetailscomment, null);
            holderblogcomment = new holderblogcomment();
            holderblogcomment.txtcommentor = (TextView) convertView.findViewById(R.id.textViewCommentorName);
            holderblogcomment.txtcomment = (TextView) convertView.findViewById(R.id.textViewSingleComment);
            convertView.setTag(holderblogcomment);
        } else {
            holderblogcomment = (holderblogcomment) convertView.getTag();
        }

        clscomment = arraycomment.get(position);
        holderblogcomment.txtcommentor.setText(clscomment.getUserName());
        holderblogcomment.txtcomment.setText(clscomment.getGivencomments());
//        hldcomment.ratingBarComment.setForeground();
        return convertView;

    }
}
class holderblogcomment
{

    TextView txtcomment,txtcommentor;

}