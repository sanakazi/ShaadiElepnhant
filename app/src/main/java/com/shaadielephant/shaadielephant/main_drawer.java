package com.shaadielephant.shaadielephant;
//accessing from navigation drawer
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.shaadielephant.shaadielephant.bloglist.fragmentblog;
import com.shaadielephant.shaadielephant.designerspeaklist.fragmentdesignerspeak;
import com.shaadielephant.shaadielephant.login.fragment_Login;
import com.shaadielephant.shaadielephant.mypage.fragmentmypage;
import com.shaadielephant.shaadielephant.planning_page.frg_planning;
import com.shaadielephant.shaadielephant.planning_page.frg_webview;
import com.shaadielephant.shaadielephant.vendors.fragmentvendor;
import com.shaadielephant.shaadielepnhant.R;

import java.util.ArrayList;
import java.util.List;


public class main_drawer extends ActionBarActivity {
    /*declaration of layout objects*/
    private DrawerLayout drawerLayout;
    private ListView listView;
    // Action bar and anction bar listener declaration declaration /v7
    private static ActionBar actionBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //intent sequence
    String intentequenc, num;
    String isfacebookloggedin="0";
    SharedPreferences shared;
    public static String stringtitle;
    static TextView txtviewActionBar;
    public static final List<String> TITLE_LIST = new ArrayList<String>();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("OnActivityResult", "username is in drawer " + requestCode);
        if(requestCode==140)
        // Pass the activity result to the fragment, which will then pass the result to the TwitterAuthClient.
        { fragment_Login.twitterlogin.onActivityResult(requestCode, resultCode, data);}

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindrawer);
        Log.w("main_drawer", "true");
        Intent i = getIntent();
        intentequenc = getIntent().getStringExtra("FragSequence");
//        Log.d("PS", "sequence===" + intentequenc);
        stringtitle = getIntent().getStringExtra("Title");

        shared = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        num = (shared.getString("num", "0"));
        isfacebookloggedin = (shared.getString("isFacebookLoggedin", "0"));
        TITLE_LIST.add(" ");

        // converting xml view to java object
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new navListAdapter(this));


        //initialization of action bar
        actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.customnavigation);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ed0b7f")));

        //finding text view from custom action bar
        txtviewActionBar = (TextView) actionBar.getCustomView().findViewById(R.id.txtList);
        txtviewActionBar.setText(stringtitle);
        // txtviewActionBar.setText(this.getTitle());

        //listView.setOnItemClickListener(new listclicklistner(this,txtviewActionBar,drawerLayout,listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                singleRow tv = (singleRow) parent.getItemAtPosition(position);
                txtviewActionBar.setText(tv.linkName);
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawer(listView);
                }

                // to open from drawer
                switch (position) {
                    case 0:
                        Log.w("Home menu", "true");
                        Intent intent = new Intent(main_drawer.this, MainActvity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        break;

                    case 1:
                        //loading fragment login
                        if (num == "0") {// no user logged in (for login option)
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            fragment_Login frgLogin = new fragment_Login();
                            Bundle bundle = new Bundle();
                            bundle.putString("main_FragSequence", "v");
                            frgLogin.setArguments(bundle);
                            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            fragmentTransaction.replace(R.id.mainContent, frgLogin, "FragmentLogin");
                            fragmentTransaction.commit();

                        }
                        if (num != "0")  // (for logout option)
                        {
                            //for fb logout
                            if((shared.getString("isFacebookLoggedin", "0")).compareTo("1")==0)
                            { FacebookSdk.sdkInitialize(main_drawer.this);
                                LoginManager.getInstance().logOut();}
                            shared.edit().clear().commit();


                            Intent intent1 = new Intent(main_drawer.this, MainActvity.class);
                            startActivity(intent1);
                            finish();
                        }


                        break;
                    case 2:
                        //loading fragment vendor
                        Log.w("Inside vendor frag", "true");
                        FragmentManager fragvendor = getSupportFragmentManager();
                        FragmentTransaction ftvendor = fragvendor.beginTransaction();
                        fragmentvendor frgvendor = new fragmentvendor();
                        fragvendor.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ftvendor.replace(R.id.mainContent, frgvendor, "FragmentVendor");
                        ftvendor.commit();


                        break;

                    case 3:
                        //loading fragment plan
                        if (num == "0") {
                            Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment_Login fc = new fragment_Login();
                            Bundle bundle = new Bundle();
                            bundle.putString("main_FragSequence", "p");
                            fc.setArguments(bundle);
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.mainContent, fc, "dsff");
                            ft.commit();
                        } else {
                            FragmentManager fragplan = getSupportFragmentManager();
                            FragmentTransaction ftplan = fragplan.beginTransaction();
                            frg_planning frgplan = new frg_planning();
                            fragplan.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ftplan.replace(R.id.mainContent, frgplan, "FragmentPlanning");
                            ftplan.commit();
                        }
                        break;

                    case 4:
                        //loading fragment blog
                        FragmentManager fmgblog = getSupportFragmentManager();
                        FragmentTransaction ftbog = fmgblog.beginTransaction();
                        fragmentblog fragblog = new fragmentblog();
                        fmgblog.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ftbog.replace(R.id.mainContent, fragblog, "FragmentBlog");
                        ftbog.commit();
                        break;
                    case 5:
                        //loading fragment designerspeak


                       /* if (num == "0") {
                            Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment_Login fc = new fragment_Login();
                            Bundle bundle = new Bundle();
                            bundle.putString("main_FragSequence", "d");
                            fc.setArguments(bundle);
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.mainContent, fc, "dsff");
                            ft.commit();
                        } else {
                        */
                            FragmentManager fmgdesignerspeak = getSupportFragmentManager();
                            FragmentTransaction ftdesignerspeak = fmgdesignerspeak.beginTransaction();
                            fragmentdesignerspeak fragdesignerspeak = new fragmentdesignerspeak();
                            fmgdesignerspeak.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ftdesignerspeak.replace(R.id.mainContent, fragdesignerspeak, "FragmentDesignerSpeack");
                            ftdesignerspeak.commit();
                       // }
                        break;


                    // loading featured wedding
                    case 6:

                        FragmentManager fm1 = getSupportFragmentManager();
                        FragmentTransaction ft1 = fm1.beginTransaction();
                        frg_webview fw = new frg_webview();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("pagename", "mobileFeaturesWedding.aspx");
                        bundle1.putString("pageheader", "Featured Wedding");
                        fw.setArguments(bundle1);
                        fm1.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ft1.replace(R.id.mainContent, fw, "dsff");
                        ft1.commit();
                        break;


                    case 7:
                        //loading fragment mypage

                        if (num == "0") {
                            Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment_Login fc = new fragment_Login();
                            Bundle bundle = new Bundle();
                            bundle.putString("main_FragSequence", "m");
                            fc.setArguments(bundle);
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.mainContent, fc, "dsff");
                            ft.commit();
                        } else {
                            FragmentManager fmgmypage = getSupportFragmentManager();
                            FragmentTransaction ftmypage = fmgmypage.beginTransaction();
                            fragmentmypage fragmypage = new fragmentmypage();
                            fmgmypage.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ftmypage.replace(R.id.mainContent, fragmypage, "FragmentDesignerSpeack");
                            ftmypage.commit();
                        }
                        break;

                    case 8:
                        //loading send einvites
                        if (num == "0") {
                            Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment_Login fc = new fragment_Login();
                            Bundle bundle = new Bundle();
                            bundle.putString("main_FragSequence", "e");
                            fc.setArguments(bundle);
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.mainContent, fc, "dsff");
                            ft.commit();
                        } else {

                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            frg_webview fc = new frg_webview();
                            Bundle bundle = new Bundle();
                            bundle.putString("pagename", "mobileuserEinvite.aspx");
                            bundle.putString("pageheader", "Send E-invites");
                            fc.setArguments(bundle);
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.mainContent, fc, "dsff");
                            ft.commit();
                        }
                        break;

                    case 9:
                        //loading fragment contacts
                        FragmentManager fmgcontact = getSupportFragmentManager();
                        FragmentTransaction ftcontact = fmgcontact.beginTransaction();
                        fragment_contactus fragmypage = new fragment_contactus();
                        fmgcontact.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ftcontact.replace(R.id.mainContent, fragmypage, "FragmentDesignerSpeack");
                        ftcontact.commit();
                        break;

                }
            }
        });
        //finding menu image line for click  event
        ImageView imageV = (ImageView) actionBar.getCustomView().findViewById(R.id.imgMenuLines);

        //setting click listner to menulines button to show hide drawer
        imageV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setting open or drawer close
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawer(listView);
                } else {
                    drawerLayout.openDrawer(listView);
                }
            }
        });
        // to open directly
        //  to open send login
        if (intentequenc.equals("l")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragment_Login frgLogin = new fragment_Login();
            Bundle bundle = new Bundle();
            bundle.putString("main_FragSequence", "v");
            frgLogin.setArguments(bundle);
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.mainContent, frgLogin, "FragmentLogin");
            fragmentTransaction.commit();
        }

        //  to open send vendors
        else if (intentequenc.equals("v")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentvendor frgvendor = new fragmentvendor();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.mainContent, frgvendor, "FragmentVendor");
            fragmentTransaction.commit();
        }

        //  to open send planning
        else if (intentequenc.equals("p")) {

            if (num == "0") {
                Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_Login fc = new fragment_Login();
                Bundle bundle = new Bundle();
                bundle.putString("main_FragSequence", "p");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                frg_planning frgplan = new frg_planning();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.mainContent, frgplan, "FragmentPlanning");
                fragmentTransaction.commit();
            }
        }

        // to open send einvites
        else if (intentequenc.equals("e")) {
            if (num == "0") {
                Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_Login fc = new fragment_Login();
                Bundle bundle = new Bundle();
                bundle.putString("main_FragSequence", "e");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frg_webview fc = new frg_webview();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "mobileuserEinvite.aspx");
                bundle.putString("pageheader", "Send E-invites");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
                // finish();
            }
        }
        // to open blogs
        else if (intentequenc.equals("c")) {
            FragmentManager fmgblog = getSupportFragmentManager();
            FragmentTransaction ftbog = fmgblog.beginTransaction();

            fragmentblog fragblog = new fragmentblog();
            fmgblog.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ftbog.replace(R.id.mainContent, fragblog, "FragmentBlog");
            ftbog.commit();
        }
        // to open mypage
        else if (intentequenc.equals("m")) {
            if (num == "0") {
                Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_Login fc = new fragment_Login();
                Bundle bundle = new Bundle();
                bundle.putString("main_FragSequence", "m");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
            } else {
                FragmentManager fmgmypage = getSupportFragmentManager();
                FragmentTransaction ftmypage = fmgmypage.beginTransaction();
                fragmentmypage fragmypage = new fragmentmypage();
                fmgmypage.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ftmypage.replace(R.id.mainContent, fragmypage, "FragmentDesignerSpeack");
                ftmypage.commit();
            }

        }

        //to open designer speak
        else if (intentequenc.equals("d")) {
            if (num == "0") {
                Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_Login fc = new fragment_Login();
                Bundle bundle = new Bundle();
                bundle.putString("main_FragSequence", "d");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
            } else {
                FragmentManager fmgdesignerspeak = getSupportFragmentManager();
                FragmentTransaction ftdesignerspeak = fmgdesignerspeak.beginTransaction();
                fragmentdesignerspeak fragdesignerspeak = new fragmentdesignerspeak();
                fmgdesignerspeak.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ftdesignerspeak.replace(R.id.mainContent, fragdesignerspeak, "FragmentDesignerSpeack");
                ftdesignerspeak.commit();
            }
        }

        // to open send wedding website
        else if (intentequenc.equals("w")) {
            if (num == "0") {
                Toast.makeText(main_drawer.this, "You need to login first", Toast.LENGTH_LONG).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment_Login fc = new fragment_Login();
                Bundle bundle = new Bundle();
                bundle.putString("main_FragSequence", "w");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frg_webview fc = new frg_webview();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "mobileuserWebsite.aspx");
                bundle.putString("pageheader", "Website");
                fc.setArguments(bundle);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.replace(R.id.mainContent, fc, "dsff");
                ft.commit();
                // finish();
            }
        }

        else if(intentequenc.equals("t"))
        {
            //loading fragment contacts
            FragmentManager fmgcontact = getSupportFragmentManager();
            FragmentTransaction ftcontact = fmgcontact.beginTransaction();
            fragment_contactus fragmypage = new fragment_contactus();
            fmgcontact.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ftcontact.replace(R.id.mainContent, fragmypage, "FragmentDesignerSpeack");
            ftcontact.commit();
        }

    }
    // Change Title on ActionBar by calling this methid inside fragment

    public void setbartitle(Context context, String strtitle) {
        //  actionBar.setTitle(strtitle);
        final TextView txtviewActionBar = (TextView) actionBar.getCustomView().findViewById(R.id.txtList);
        stringtitle = strtitle;
        // txtviewActionBar.setText(strtitle);

        txtviewActionBar.setText(stringtitle);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("called", "onresume");
        Log.w("String title is ", stringtitle.toString());
        txtviewActionBar.setText(stringtitle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TITLE_LIST.remove(TITLE_LIST.size() - 1);
        Log.w("last elemet is ", TITLE_LIST.get(TITLE_LIST.size() - 1));
        Log.w("Back button", "true");
        for (int i = 0; i < TITLE_LIST.size(); i++) {
            Log.w("Element: ", TITLE_LIST.get(i));
        }

        setbartitle(main_drawer.this, TITLE_LIST.get(TITLE_LIST.size() - 1));

        MainActvity main = new MainActvity();
        main.visibilityLogin((shared.getString("num", "0")));


    }


    //class listclicklistner implements AdapterView.OnItemClickListener
//{
//    Context c;
//    TextView TT;
//    DrawerLayout DW;
//    ListView LV;
//    FragmentManager FM;
//    listclicklistner(Context context, TextView T, DrawerLayout dw, ListView lv, FragmentManager fm)
//    {
//        this.c=context;
//        this.TT=T;
//        this.DW = dw;
//        this.LV=lv;
//        this.FM=fm;
//    }
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        singleRow tv=  (singleRow) parent.getItemAtPosition(position);
//        TT.setText(tv.linkName);
//        if (DW.isDrawerOpen(LV)) {
//            DW.closeDrawer(LV);
//        }
//        switch (position)
//        {
//            case    0:
//                break;
//            case    1:
//                //loading fragment
//                 FM=getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                if(intentequenc=="1") {
//                    fragmentBlog frgBlog = new fragmentBlog();
//                    fragmentTransaction.replace(R.id.mainContent, frgBlog, "FragmentBlog");
//                }
//                fragmentTransaction.commit();
//
//                break;
//        }
////        Toast.makeText(c,, Toast.LENGTH_SHORT).show();
//    }
//}
    class singleRow {
        public String linkName;
        public int linkIcon;

        singleRow(String strname, int iconid) {
            this.linkName = strname;
            this.linkIcon = iconid;
        }


    }

    class navListAdapter extends BaseAdapter {
        Context context;
        Resources res;
        ArrayList<singleRow> listsinglerow;
        String num;
        SharedPreferences shared;

/*
        int[] imagesArray = {R.mipmap.home_icon, R.mipmap.login_icon, R.mipmap.vendor_icon, R.mipmap.planning_icon, R.mipmap.blog_icon, R.mipmap.designer_icon, R.mipmap.featured, R.mipmap.mypage_icon, R.mipmap.einvite_icon, R.mipmap.contact_icon};
        int[] imagesArraylogout = {R.mipmap.home_icon, R.mipmap.logout_icon, R.mipmap.vendor_icon, R.mipmap.planning_icon, R.mipmap.blog_icon, R.mipmap.designer_icon, R.mipmap.featured, R.mipmap.mypage_icon, R.mipmap.einvite_icon, R.mipmap.contact_icon};
*/
int[] imagesArray = {R.drawable.home_icon, R.drawable.login_icon, R.drawable.vendor_icon, R.drawable.planning_icon, R.drawable.blog_icon, R.drawable.designer_icon, R.drawable.featured, R.drawable.mypage_icon, R.drawable.einvite_icon, R.drawable.contact_icon};
        int[] imagesArraylogout = {R.drawable.home_icon, R.drawable.logout_icon, R.drawable.vendor_icon, R.drawable.planning_icon, R.drawable.blog_icon, R.drawable.designer_icon, R.drawable.featured, R.drawable.mypage_icon, R.drawable.einvite_icon, R.drawable.contact_icon};

        navListAdapter(Context c) {

            this.context = c;
            listsinglerow = new ArrayList<singleRow>();
            res = c.getResources();
            String[] link = res.getStringArray(R.array.navlist);
            String[] linklogout = res.getStringArray(R.array.navlistlogout);
            // int[] icon= res.getIntArray(R.array.navlisticon);

            for (int i = 0; i < link.length; i++)

            {
                listsinglerow.add(new singleRow(link[i], imagesArray[i]));
            }

        }

        @Override
        public int getCount() {
            return listsinglerow.size();
        }

        @Override
        public Object getItem(int position) {
            return listsinglerow.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.drawerlist, parent, false);
            ImageView imgicon = (ImageView) row.findViewById(R.id.navListIcon);
            TextView txtLink = (TextView) row.findViewById(R.id.txtNavListLink);
            singleRow singleRowtemp = listsinglerow.get(position);
            txtLink.setText(singleRowtemp.linkName);

//        Log.d("PS", "Image Name == "+singleRowtemp.linkIcon);
            imgicon.setImageResource(singleRowtemp.linkIcon);
//        imgicon.setImageDrawable(context.getResources().getDrawable(R.drawable.shaddi_logo));

            shared = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
            num = (shared.getString("num", "0"));


            listsinglerow = new ArrayList<singleRow>();
            res = context.getResources();
            String[] link = res.getStringArray(R.array.navlist);
            String[] linklogout = res.getStringArray(R.array.navlistlogout);
            // int[] icon= res.getIntArray(R.array.navlisticon);

            if (num != "0") {
                for (int i = 0; i < linklogout.length; i++)

                {
                    listsinglerow.add(new singleRow(linklogout[i], imagesArraylogout[i]));
                }

            } else {
                for (int i = 0; i < link.length; i++)

                {
                    listsinglerow.add(new singleRow(link[i], imagesArray[i]));
                }
            }

            return row;
        }


    }
}