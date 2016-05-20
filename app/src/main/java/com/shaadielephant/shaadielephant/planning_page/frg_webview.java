package com.shaadielephant.shaadielephant.planning_page;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

/**
 * Created by Callndata on 2/19/2016.
 */
public class frg_webview extends Fragment {
    private WebView webView;
    SharedPreferences shared;
    View v;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_webview, container, false);
        webView = (WebView)v.findViewById(R.id.webView1);

        LinearLayout l1 = (LinearLayout)v.findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Donot" , "reload fragment webview l1");
            }
        });
        shared = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        String email = (shared.getString("Email","null"));
        String num = (shared.getString("num","0"));
        Bundle inputargs = getArguments();
        String pagename = inputargs.getString("pagename");
        String pageheader = inputargs.getString("pageheader");
        Log.w("email is", email + " and num is " + num + " pagename is " + pagename);

        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Planning > "+ pageheader);
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://shaadielephant.com/SSOPageRedirect.aspx?userId=" + num + "&emailID=" + email + "&pageName=" + pagename);
        Log.w("url is ","http://shaadielephant.com/SSOPageRedirect.aspx?userId=" + num + "&emailID=" + email + "&pageName=" + pagename);
        webView.getSettings().setJavaScriptEnabled(true);

        if (progressDialog != null) {
            progressDialog.dismiss();

        }
        return v;
    }


}
