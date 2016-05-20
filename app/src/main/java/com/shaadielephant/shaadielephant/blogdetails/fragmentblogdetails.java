package com.shaadielephant.shaadielephant.blogdetails;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaadielephant.shaadielephant.vendorsdetails.asyntask4;
import com.shaadielephant.shaadielephant.vendorsdetails.networkresponse4;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;


public class fragmentblogdetails extends Fragment implements networkresponse1 {
ListView listBlogDetails;
    customclassblogdetailscombine objblogdetils;
    ArrayList<customclassblogdetails>objArraylist;
    ArrayList<customclassblogdetailscomment> arraycomment;
    SharedPreferences shared;
    int blogID;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragmentblogdetails, container, false);
        listBlogDetails= (ListView)v.findViewById(R.id.listBlogDetails);
        Bundle getdata=getArguments();
        blogID= getdata.getInt("num");
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Blog");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        asyntask1 objasyntask= new asyntask1(getActivity());
        asyntask1.setListner(fragmentblogdetails.this);
        String[] strinputarray={"http://webservices.shaadielephant.com/blog.asmx/blogDetails","blogID="+blogID};
        objasyntask.execute(strinputarray);
        return v;
    }

    @Override
    public void getResponse1(String strinput) {
       blogdetailsjsonparser objblogdetialsjsonparser = new  blogdetailsjsonparser();
        objblogdetils= objblogdetialsjsonparser.parser(strinput);
        objArraylist = objblogdetils.getCustomclassblogdetails();
        blogdetailsadapter objblogadapter= new  blogdetailsadapter(getActivity(),objblogdetils);
        listBlogDetails.setAdapter(objblogadapter);
    }


    //<---------------adapter class-------------------------------->

    public class blogdetailsadapter extends BaseAdapter implements networkresponse4 {
        Context context;
        Dialog dialog;

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
                blogdetailholder.text_review=(TextView)convertView.findViewById(R.id.text_review);
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
            blogdetailholder.listBlogComments.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, outputcomment.size()*200));

            blogdetailholder.listBlogComments.setAdapter(objadapter);

            blogdetailholder.text_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shared.getString("num", "0") != "0")
                    {
                        showDialogToPostComments();
                    }
                    else{
                        Toast.makeText(context, "You need to login first to comment", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            return convertView;
        }

        //popup dialog to show comments
        private void showDialogToPostComments() {

            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_comments);
            dialog.setCancelable(false);
            Button comment_submit = (Button) dialog.findViewById(R.id.comment_submit);
            final EditText comments_text = (EditText) dialog.findViewById(R.id.comments_text);


            ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
            dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });


            comment_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comments_text.getText().toString().length() == 0) {
                        Toast.makeText(getActivity(), "Please insert all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        asyntask4 objasyntask4 = new asyntask4(getActivity());
                        asyntask4.setListner(blogdetailsadapter.this);
                        String[] strinputarray = {"http://webservices.shaadielephant.com/RatingAndComments.asmx/AddRatinAndComments", "sectionName=" + "master_Blog",
                                "ID=" + clsdata.getNum() , "rateVal="+ "5" , "givencomments="+comments_text.getText().toString(),
                                "userID=" +(shared.getString("num","0")) };
                        objasyntask4.execute(strinputarray);

                    }

                }
            });
            dialog.show();

        }

        //to post comments

        @Override
        public void getResponse4(String strinput) {
            Log.w("str output" , strinput);
            comments_jsonparser ob = new comments_jsonparser();
            dialog.dismiss();
            Toast.makeText(context,"Your comment has been sent for approval" , Toast.LENGTH_SHORT).show();

            if (ob.arrayList != null)
            {
                String status_report = ob.arrayList.get(0);
                if (status_report.compareTo("1") == 0) {
                }
            }
        }
    }

    class blogdetailholder
    {
        TextView holdertextViewheading;
        TextView holdertextViewInsertDate;
        TextView holdertextViewBrief,text_review;
        //  TextView holdertextViewDesc;
        WebView holdertextViewDesc;
        ListView listBlogComments;

    }

}
