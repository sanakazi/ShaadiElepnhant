package com.shaadielephant.shaadielephant.vendors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.imagecreator;

import java.util.ArrayList;

public class vendoradapter extends BaseAdapter {

    Context context;
    ArrayList<customvendorclass> vendorarray;
   public vendoradapter(Context c, ArrayList<customvendorclass> temparray)
    {
        this.context=c;
        this.vendorarray=temparray;
    }
    @Override
    public int getCount() {
        return vendorarray.size();
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
//        View row=null;

        vendorholder vholder;
        if(convertView==null)
        {
            LayoutInflater li= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= li.inflate(R.layout.singlerowvendor,null);
            vholder= new vendorholder();
            vholder.holderimageview=(ImageView) convertView.findViewById(R.id.imageViewVendor);
            vholder.holderTextView=(TextView) convertView.findViewById(R.id.textViewVendor);
            convertView.setTag(vholder);
        }
        else
        {
            vholder= (vendorholder) convertView.getTag();
        }


        customvendorclass test= vendorarray.get(position);



        if(test!=null) {
            vholder.holderTextView.setText(test.getProductCategoryName());
            imagecreator imgcreator = new imagecreator(context);
            imgcreator.imagecreatorvendor("http://shaadielephant.com/imagesProductImages360/"+test.getImageurl(),vholder.holderimageview);

        }


        return convertView;
    }
}
class vendorholder {
    ImageView holderimageview;
    TextView holderTextView;

}