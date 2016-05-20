package com.shaadielephant.shaadielephant.designerspeakdetails;

import android.content.Context;
import android.text.Html;
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


public class designerspeakdetailsresponseadapter extends BaseAdapter {
    ArrayList<customclassdesignerspeakdetailsresponse> arrayresp;
    customclassdesignerspeakdetailsresponse clsresp;
    Context context;

    designerspeakdetailsresponseadapter(Context context, ArrayList<customclassdesignerspeakdetailsresponse> arrayresp )
    {
        Log.w("designer response ", "yes");
        this.context=context;
        this.arrayresp = arrayresp;


    }

    @Override
    public int getCount() {
        return arrayresp.size();
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
        holderdesignerresponse hr;
        if(convertView==null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowdesignerspeakdetailsresponse, null);
            hr = new holderdesignerresponse();
            hr.img= (ImageView)convertView.findViewById(R.id.img);
            hr.name=(TextView)convertView.findViewById(R.id.name);
            hr.desc=(TextView)convertView.findViewById(R.id.desc);
            convertView.setTag(hr);
        }
        else
        {
            hr=(holderdesignerresponse) convertView.getTag();
        }

        clsresp=arrayresp.get(position);
        hr.name.setText(clsresp.getUserName());
        hr.desc.setText(Html.fromHtml(clsresp.getGivencomments()));
        imagecreator imgvendor= new imagecreator(context);
        imgvendor.imagecreatorvendrodetail("http://shaadielephant.com/ImageUserPicture" + clsresp.getUserPic(), hr.img);


        return convertView;
    }

}

class holderdesignerresponse
{
    TextView name, desc;
    ImageView img;
}