package com.shaadielephant.shaadielephant.bloglist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;

import java.util.ArrayList;

public class blogadapter extends BaseAdapter {
    Context context;
    ArrayList<customblogclass> blogarray;
    blogadapter(Context c, ArrayList<customblogclass> temparray)
    {
        this.context=c;
        this.blogarray=temparray;
    }
    @Override
    public int getCount() {
        return blogarray.size();
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
        blogholder blgholder;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowblog, null);
            blgholder= new blogholder();
            blgholder.holderimageViewBlog = (ImageView) convertView.findViewById(R.id.imageViewBlog);
            blgholder.holdertextViewheading = (TextView) convertView.findViewById(R.id.textViewheading);
            blgholder.holdertextViewInsertDate = (TextView) convertView.findViewById(R.id.textViewInsertDate);
            blgholder.holdertextViewBrief = (TextView) convertView.findViewById(R.id.textViewBrief);
            convertView.setTag(blgholder);
        }
        else
        {
           blgholder= (blogholder) convertView.getTag();
        }
        customblogclass test= blogarray.get(position);
        Log.w("position is",position + "");
        if(test!=null) {
            Log.d("PS", "test == " + test);
            blgholder.holdertextViewheading.setText(test.getTxtheading());
            blgholder.holdertextViewInsertDate.setText(test.getTxtinsertdate());
            blgholder.holdertextViewBrief.setText(test.getTxtbrief() + "");
            imagecreator imgcreator= new imagecreator(context);
//        imgcreator.imagecreatorblog(test.getImageurl(), imageView);
          //  imgcreator.imagecreatorblog("http://content.icicidirect.com/IdirectContent/Basemasterpage/images/iciciLogo.png", blgholder.holderimageViewBlog);
            imgcreator.imagecreatorblog("http://shaadielephant.com/Imageblog/"+test.getImageurl(), blgholder.holderimageViewBlog);
        }

        return convertView;
    }
}
class blogholder
{
    ImageView holderimageViewBlog;
    TextView holdertextViewheading;
    TextView holdertextViewInsertDate;
    TextView holdertextViewBrief;

}