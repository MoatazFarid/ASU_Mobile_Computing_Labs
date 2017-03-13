package com.example.moatazfarid.lab1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterationActivity extends AppCompatActivity {

    //define vars
    TextView txtView_login ;
    EditText txt_name;
    EditText txt_email;
    EditText txt_pass;
    EditText txt_passConfirm;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        // mapping layout
        txtView_login = (TextView) findViewById(R.id.txtView_login);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
        txt_passConfirm = (EditText) findViewById(R.id.txt_passConfirm);
        btn_register = (Button) findViewById(R.id.btn_register);

        // listeners
        txtView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_name.getText().toString();
                String email = txt_email.getText().toString();
                String pass = txt_pass.getText().toString();
                String passconfirm = txt_passConfirm.getText().toString();

                // view toast
                Toast.makeText(RegisterationActivity.this,"Thanks "+name+" , You have Registered !!",Toast.LENGTH_LONG).show();

                // redirect to login
                Intent intent = new Intent(RegisterationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
