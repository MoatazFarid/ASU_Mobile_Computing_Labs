package com.example.moatazfarid.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    //defining vars
    Button btn_login;
    EditText txt_email;
    EditText txt_pass;
    TextView txtview_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // get login ,pass and email ids
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_pass =  (EditText) findViewById(R.id.txt_pass);
        txtview_register = (TextView) findViewById(R.id.txtview_register);

        //set listners
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email
                String e = txt_email.getText().toString();
                //get pass
                String p = txt_pass.getText().toString();
                //check if email = admin@moataz.com and pass = admin

                if(e.contentEquals("admin@moataz.com") && p.contentEquals("admin"))
                {
                    //if passed go to main activity
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    //if failed show toast
                    Toast.makeText(LoginActivity.this, "Wrong email/Password !!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // if register here txt is clicked
        txtview_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to Registration activity
                Intent intent = new Intent(LoginActivity.this,RegisterationActivity.class);
                startActivity(intent);
            }
        });
    }


}
