package com.shaadielephant.shaadielephant.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;

import com.shaadielephant.shaadielephant.noInternetConnection_fragment;
import com.shaadielephant.shaadielepnhant.R;
import com.shaadielephant.shaadielephant.main_drawer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Callndata on 3/8/2016.
 */
public class registration_Activity extends FragmentActivity implements networkresponse1 {

    EditText name, address, country, city, phoneNo, emailID, password,repassword;
    Spinner gender;
    Button signUp;
    ArrayList<customclass_register> objArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.name);
        gender = (Spinner) findViewById(R.id.gender);
        address = (EditText) findViewById(R.id.address);
        country = (EditText) findViewById(R.id.country);
        city = (EditText) findViewById(R.id.city);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        emailID = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signUp = (Button) findViewById(R.id.signUp);

        spinnermethod();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.w("Values are ", name.getText().toString() + " " + emailID.getText().toString() + " " + password.getText().toString()
                        + " " + phoneNo.getText().toString() + " " + gender.getSelectedItem().toString() + " " +
                        address.getText().toString() + " " + country.getText().toString() + " " + city.getText().toString());

                if(name.getText().toString().length()==0 || address.getText().toString().length()==0 || country.getText().toString().length()==0 || city.getText().toString().length()==0
                        || emailID.getText().toString().length()==0 || password.getText().toString().length()==0 || repassword.getText().toString().length()==0)
                {
                    Toast.makeText(registration_Activity.this,"Please insert all the fields",Toast.LENGTH_SHORT).show();

                } else if (!isValidMobile(phoneNo.getText().toString())) {
                    Toast.makeText(registration_Activity.this,"Please enter a valid mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (!isValidEmail(emailID.getText().toString())) {
                    Toast.makeText(registration_Activity.this,"Please enter a valid email ID",Toast.LENGTH_SHORT).show();
                    return;
                }


               else  if(password.getText().toString().equals(repassword.getText().toString()))
                {
                    asyntask1 objasyntask1 = new asyntask1(registration_Activity.this);
                    asyntask1.setListner(registration_Activity.this);
                    String[] strinputarray = {"http://webservices.shaadielephant.com/members.asmx/MemberRegistration",
                            "Name=" + name.getText().toString(),
                            "emailID=" + emailID.getText().toString(), "Password=" + password.getText().toString(),
                            "PhoneNo=" + phoneNo.getText().toString(), "gender=" + gender.getSelectedItem().toString(),
                            "Address=" + address.getText().toString(), "country=" + country.getText().toString(),
                            "city=" + city.getText().toString(), "facebookID=" + " ", "twitterID=" + " ", "androidRegID=" + " ",
                            "iPhoneRegID=" + " "};
                    objasyntask1.execute(strinputarray);

                }


                else {
                    Toast.makeText(registration_Activity.this,"Passwords donot match",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }

    // validating email id
    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
   //validating phone no.
    public boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public void spinnermethod()

    {
        String[] categories = new String[2];
        categories[0] = "Male";
        categories[1] = "Female";


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender.setAdapter(dataAdapter);

    }

    @Override
    public void getResponse1(String strinput) {
        if (strinput == "noInternet") {
          Toast.makeText(registration_Activity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();

        } else {
            register_jsonparser obj = new register_jsonparser();
            objArrayList = obj.parser(strinput);

            customclass_register test = objArrayList.get(0);
            int status = test.getStatus();

            if (status == 0) {
                Toast.makeText(registration_Activity.this, test.getMsg(), Toast.LENGTH_SHORT).show();
            }

            if (status == 1) {
                Toast.makeText(registration_Activity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intLogin = new Intent(getApplicationContext(), main_drawer.class);
                intLogin.putExtra("FragSequence", "l");
                intLogin.putExtra("Title", "Login");
                intLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intLogin);
            }

        }
    }
}



