package com.shaadielephant.shaadielephant.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielephant.registration.customclass_register;
import com.shaadielephant.shaadielephant.registration.register_jsonparser;
import com.shaadielephant.shaadielephant.registration.registration_Activity;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class fragment_Login extends Fragment implements networkresponse1,networkresponse2,networkresponse3,networkresponse4,networkresponse5 {
    View v;
    EditText editTextLogin,editTextPassword;
    Button login;
    ArrayList<String> objArraylist,obForgotPassArray;
    SharedPreferences  sharedpreferences;
    String segment;
    LoginButton facebookLogin;
    CallbackManager callbackManager;
    TextView register,forgot_password;
    private static Dialog dialog ;
    public static TwitterLoginButton twitterlogin;
    loginjsonparser objloLoginjsonparser;

   // for twitter
    long twitter_id;
    String twitter_name;

    //for facebook
    String fb_id, fb_name, fb_email;

    int choose = 123;
    ArrayList<customclass_register> objArrayList;

    private static String isFacebookLoggedin="0";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        FacebookSdk.sdkInitialize(this.getActivity());
        v = inflater.inflate(R.layout.fragment_login,container,false);
        editTextLogin = (EditText)v.findViewById(R.id.editTextLogin);
        editTextPassword = (EditText)v.findViewById(R.id.editTextPassword);
        login = (Button)v.findViewById(R.id.login);
        register = (TextView)v.findViewById(R.id.login_forgot_password_container__register_now_text);
        forgot_password = (TextView)v.findViewById(R.id.login_forgot_password_container_text);

       // ((main_drawer) getActivity()).setbartitle(getActivity(), "Login");
        main_drawer call = new main_drawer();
        call.TITLE_LIST.add("Login");
        ((main_drawer) getActivity()).setbartitle(getActivity(), call.TITLE_LIST.get(call.TITLE_LIST.size() - 1));

        Bundle inputargs= getArguments();
         segment = inputargs.getString("main_FragSequence");

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForForgotPassword();
            }
        });


        initTwitter();
        initFacebook();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( facebookLogin.getText().toString().compareTo("Log out")==0)
                {
                    Toast.makeText(getContext(), "Please log out of Facebook first", Toast.LENGTH_LONG).show();
                    return;
                }


                if (editTextLogin.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter valid username", Toast.LENGTH_LONG).show();
                    return;
                }

                if (editTextPassword.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter a valid password", Toast.LENGTH_LONG).show();
                    return;
                }


                //for normal login
                else {
                    asyntask1 objasyntask = new asyntask1(getActivity());
                    asyntask1.setListner(fragment_Login.this);
                    // objasyntask.execute("http://webservices.shaadielephant.com/members.asmx/memberLogin", "emailID=" + editTextLogin.toString(), "upassword=" + editTextPassword.toString());
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberLogin", "emailID=" + editTextLogin.getText().toString(), "upassword=" + editTextPassword.getText().toString()};
                    //  String[] strinputarray={"http://webservices.shaadielephant.com/members.asmx/memberLogin", "emailID=" + "shital@callndata.com", "upassword=" +"123"};

                    Log.w("string login", strinputarray + "");
                    objasyntask.execute(strinputarray);
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), registration_Activity.class);
                startActivity(intent);
            }
        });



        return v;
    }



    /**
     * initialization of twitter
     */
    public void initTwitter(){
        twitterlogin = (TwitterLoginButton) v.findViewById(R.id.login_twitter);
        twitterlogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                String username = result.data.getUserName();
                long userId = result.data.getUserId();
                Log.d("Success", "UserName is" + username);
                Log.d("twitter ", "Id" + userId);
                //for twitter u ll get user id here , same like fb integrate wid WS and open new fragment


                twitter_id = userId;
                twitter_name = username;
                //for twitter login
                asyntask4 objasyntask4 = new asyntask4(getActivity());
                asyntask4.setListner(fragment_Login.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberTwitterID", "twitterID=" + userId};
                objasyntask4.execute(strinputarray);

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * initialization of facebook account
     */
    private void initFacebook(){
        //facebookLogin.setReadPermissions(Arrays.asList("public_profile", "user_friends"));
        facebookLogin = (LoginButton) v.findViewById(R.id.login_facebook);
        callbackManager = CallbackManager.Factory.create();
        facebookLogin.setReadPermissions(Arrays.asList("public_profile", "user_friends"));
        facebookLogin.setFragment(this);

        facebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // login successful
                isFacebookLoggedin = "1";
                Log.d("Facebook", "onSuccess");
                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();
                }
            }

            @Override
            public void onCancel() {
                // login cancelled
                Log.d("Facebook", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // login error
                Log.d("Facebook", "Facebook exception-" + exception);
                Toast.makeText(getActivity(),exception.toString() + " ", Toast.LENGTH_SHORT).show();
            }
        });


    }



    /**
     * get personal data of user from facebook
     */
    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                JSONObject json = response.getJSONObject();

                Log.d("Launcher","Facebook response-"+json.toString());
                try {
                    String Email = "";
                    if(json.has("email")){
                        Email = json.getString("email");
                    }
                    if(json != null){
                        //sendFaceBookData(json.getString("name"), Email, json.getString("id"), "");
                        String facebookId = json.getString("id");
                        Log.d("Facebook", "Facebook userId" + facebookId);
                        //here ur getting fb user id pass it to web service and open whiever fragment u want from here



                        fb_id=facebookId;
                        fb_name=json.getString("name");
                        fb_email=Email;
                        Log.w("facebook name, " , fb_name + " fb email " + fb_email);
                        //for facebook login
                        asyntask3 objasyntask3 = new asyntask3(getActivity());
                        asyntask3.setListner(fragment_Login.this);
                        String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberfacebookID", "facebookID=" + facebookId};
                        objasyntask3.execute(strinputarray);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        twitterlogin.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((main_drawer) getActivity()).setbartitle(getActivity(), "Login");

    }

    //popup dialog for forgot password
    private void showDialogForForgotPassword() {

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.setCancelable(false);
        Button dialog_send_email = (Button) dialog.findViewById(R.id.dialog_send_email);
        final EditText enter_email = (EditText) dialog.findViewById(R.id.enter_email);
        ImageView dialog_cancel = (ImageView)dialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        dialog_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registration_Activity m = new registration_Activity();
                if (!m.isValidEmail(enter_email.getText().toString()) || enter_email.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please enter a valid email ID", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    //for forgot password
                    asyntask2 objasyntask2 = new asyntask2(getActivity());
                    asyntask2.setListner(fragment_Login.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberRetrivePassword", "emailID=" + enter_email.getText().toString()};
                    objasyntask2.execute(strinputarray);

                }

            }
        });
        dialog.show();

    }

    public void login_details()
    {
        int size=objloLoginjsonparser.arrayList.size();
        Log.w("size is ", size + " ");
        String[] valuesOflogin = new String[size];
        for(int i=0;i<size;i++)
        {
            valuesOflogin[i]= objloLoginjsonparser.arrayList.get(i);
            //    Log.w("Values ", "  "+ i + "=" + valuesOflogin[i]);// valuesOflogin[0] = num, valuesOflogin[1]=Name,  valuesOflogin[2]=MobileNo,  valuesOflogin[3]=gender,  valuesOflogin[4]=userpicture;
        }
        String status = valuesOflogin[0];
        if(status.equals("1"))
        {

            Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
            String num= valuesOflogin[1];
            String Name=valuesOflogin[2];
            String MobileNo= valuesOflogin[3];
            String gender = valuesOflogin[4];
           // String userpicture = valuesOflogin[5];
            String emailID = valuesOflogin[6];



            sharedpreferences = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Email", emailID);
            editor.putString("num", num);
            editor.putString("name",Name);
            Log.w("userID is ", num + " ");
            Log.w("emailId is " , emailID  + " ");
            if(isFacebookLoggedin.compareTo("1")==0)
            { editor.putString("isFacebookLoggedin", isFacebookLoggedin);
               }
            editor.commit();
            getActivity().finish();

            Intent intVendor = new Intent(getActivity(), main_drawer.class);
            intVendor.putExtra("FragSequence", segment);
            startActivity(intVendor);


        }
        else if(status.equals("0"))
        {
            String msg= valuesOflogin[1];


            if(msg.compareTo("Twitter ID not found")==0)
            {
                choose=2;
                asyntask5 objasyntask5 = new asyntask5(getActivity());
                asyntask5.setListner(fragment_Login.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/MemberRegistration",
                        "Name=" + twitter_name,
                        "emailID=" + twitter_name+"@shaadielephant.com", "Password=" + "123",
                        "PhoneNo=" + "", "gender=" + "",
                        "Address=" +"", "country=" +"",
                        "city=" + "","facebookID="+"","twitterID="+twitter_id,"androidRegID="+"",
                        "iPhoneRegID="+""};
                objasyntask5.execute(strinputarray);
            }

            else  if(msg.compareTo("Facebook ID not found")==0)
            {

                choose=1;
                asyntask5 objasyntask5 = new asyntask5(getActivity());
                asyntask5.setListner(fragment_Login.this);
                String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/MemberRegistration",
                        "Name=" + fb_name,
                        "emailID=" + fb_id+"@shaadielephant.com", "Password=" + "123",
                        "PhoneNo=" + "", "gender=" + "",
                        "Address=" +"", "country=" +"",
                        "city=" + "","facebookID="+fb_id,"twitterID="+" ","androidRegID="+"",
                        "iPhoneRegID="+""};
                objasyntask5.execute(strinputarray);
            }
            else
            {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        }
    }

        // Response for Login
    @Override
    public void getResponse1(String strinput) {


            Log.w("string login", strinput + "");
            objloLoginjsonparser = new loginjsonparser();
            objArraylist = objloLoginjsonparser.parser(strinput);
            login_details();


    }

    //response for forgot password

    @Override
    public void getResponse2(String strinput) {

            forgot_password_jsonparser ob = new forgot_password_jsonparser();
            obForgotPassArray = ob.parser(strinput);
            dialog.dismiss();

            String status_report = ob.arrayList.get(0);
            Toast.makeText(getActivity(), ob.arrayList.get(1), Toast.LENGTH_SHORT).show();



    }

    //response for facebook login
    @Override
    public void getResponse3(String strinput) {

            Log.w("string login", strinput + "");
            objloLoginjsonparser = new loginjsonparser();
            objArraylist = objloLoginjsonparser.parser(strinput);
            login_details();

    }

    //response for twitter login

    @Override
    public void getResponse4(String strinput) {

            Log.w("string login", strinput + "");
            objloLoginjsonparser = new loginjsonparser();
            objArraylist = objloLoginjsonparser.parser(strinput);
            login_details();


    }

    //response for fb and twitter registration

    @Override
    public void getResponse5(String strinput) {


            register_jsonparser obj = new register_jsonparser();
            objArrayList = obj.parser(strinput);

            customclass_register test = objArrayList.get(0);
            int status = test.getStatus();

            if (status == 0) {
                Toast.makeText(getActivity(), test.getMsg(), Toast.LENGTH_SHORT).show();
            }

            if (status == 1) {
                Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
                //for fb
                if (choose == 1) {
                    //for facebook login
                    asyntask3 objasyntask3 = new asyntask3(getActivity());
                    asyntask3.setListner(fragment_Login.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberfacebookID", "facebookID=" + fb_id};
                    objasyntask3.execute(strinputarray);
                }
                //for twitter
                if (choose == 2) {
                    //for twitter login
                    asyntask4 objasyntask4 = new asyntask4(getActivity());
                    asyntask4.setListner(fragment_Login.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/memberTwitterID", "twitterID=" + twitter_id};
                    objasyntask4.execute(strinputarray);
                }


        }

    }

}
