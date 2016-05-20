/*package com.shaadielephant.shaadielephant.blogdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;

public class blogdetailsadapter extends BaseAdapter{
    Context context;

    ArrayList<customclassblogdetails> blogarray;
    ArrayList<customclassblogdetailscomment> outputcomment;
   customclassblogdetailscombine output;
    customclassblogdetails clsdata;
    fragmentblogdetails hi;


    blogdetailsadapter(Context c,  customclassblogdetailscombine custoutput)
    {
        this.context=c;
        this.output = custoutput;
    }
    @Override
    public int getCount() {
        return 1;
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
        blogdetailholder blogdetailholder;
        if(convertView==null) {
            blogarray= output.getCustomclassblogdetails();
            outputcomment =output.getCustomclassblogdetailscomment();


            LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.singlerowblogdetails, null);
            clsdata= blogarray.get(0);

            blogdetailholder= new   blogdetailholder();
           // blogdetailholder.holderimageViewBlog = (ImageView) convertView.findViewById(R.id.imageViewBlog);
            blogdetailholder.holdertextViewheading = (TextView) convertView.findViewById(R.id.textViewheading);
            blogdetailholder.holdertextViewInsertDate = (TextView) convertView.findViewById(R.id.textViewInsertDate);
            blogdetailholder.holdertextViewBrief = (TextView) convertView.findViewById(R.id.textViewBrief);
        //    blogdetailholder.holdertextViewDesc= (TextView) convertView.findViewById(R.id.textViewdesc);
            blogdetailholder.holdertextViewDesc= (WebView) convertView.findViewById(R.id.blog_detail_webView);
            blogdetailholder.holdertextViewDesc.getSettings().setJavaScriptEnabled(true);
            blogdetailholder.listBlogComments= (ListView) convertView.findViewById(R.id.listBlogComments);
            convertView.setTag( blogdetailholder);
        }
        else
        {
            blogdetailholder= (  blogdetailholder) convertView.getTag();
        }
        if(clsdata!=null) {
             blogdetailholder.holdertextViewheading.setText(clsdata.getHeading());
            blogdetailholder.holdertextViewInsertDate.setText(clsdata.getInsertdate());
            blogdetailholder.holdertextViewBrief.setText(clsdata.getBrief() + "");
            //blogdetailholder.holdertextViewDesc.setText(Html.fromHtml(clsdata.getDescription()) + "");
            blogdetailholder.holdertextViewDesc.loadDataWithBaseURL("", "<HTML>" + clsdata.getDescription()+"</HTML>", "text/html", "UTF-8", "");
            }

        blogdetailscommentadapter objadapter= new blogdetailscommentadapter(context,outputcomment);
        blogdetailholder.listBlogComments.setAdapter(objadapter);

        return convertView;
    }
}

class blogdetailholder
{
    TextView holdertextViewheading;
    TextView holdertextViewInsertDate;
    TextView holdertextViewBrief;
  //  TextView holdertextViewDesc;
    WebView holdertextViewDesc;
    ListView listBlogComments;

}
*/