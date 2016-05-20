package com.shaadielephant.shaadielephant.designerspeaklist;

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


public class designerspeaklistadapter extends BaseAdapter {
    ArrayList<customclassdesignerspeaklist> arrayList;
    Context context;
    designerspeaklistadapter(Context c, ArrayList<customclassdesignerspeaklist> inputarraylist){
        this.context=c;
        this.arrayList=inputarraylist;
    }
    @Override
    public int getCount() {
        return arrayList.size();
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
        holderdesignerspeaklist holder;
        if(convertView==null)
        {
            LayoutInflater li= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= li.inflate(R.layout.singlerowdesignerspeaklist,null);
            holder= new holderdesignerspeaklist();
            holder.subject= (TextView) convertView.findViewById(R.id.textViewSubject);
            holder.message= (TextView) convertView.findViewById(R.id.textViewmessage);
            holder.insertdate= (TextView) convertView.findViewById(R.id.textViewdate);
            holder.membername= (TextView) convertView.findViewById(R.id.textViewname);
            holder.membericon= (ImageView) convertView.findViewById(R.id.iVDesignerpic);
            convertView.setTag(holder);
        }
        else{
            holder= (holderdesignerspeaklist) convertView.getTag();
        }

        customclassdesignerspeaklist objclass= arrayList.get(position);
        if(objclass!=null)
        {
            holder.subject.setText(objclass.getSubject());
            holder.message.setText(objclass.getMessage());
            holder.insertdate.setText(objclass.getInsertDate());
            holder.membername.setText(objclass.getMemberName());
            imagecreator imgcreator= new imagecreator(context);
           imgcreator.imagecreatorblog("http://shaadielephant.com/ImageUserPicture/"+objclass.getMemberPicture(),holder.membericon);
          //  imgcreator.imagecreatordesignerspeak("http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png",holder.membericon);
        }
        return convertView;
    }
}

class holderdesignerspeaklist
{
    ImageView membericon;
    TextView subject;
    TextView message;
    TextView insertdate;
    TextView membername;
    TextView readmore;
}
