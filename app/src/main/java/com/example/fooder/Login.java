package com.example.fooder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Login extends AppCompatActivity
{
    // GLOBAL VARIABLES
    String LoginName;
    String LoginPassword;

    CheckBox checkBox_RememberMe;
    EditText editText_LoginPassword;
    ImageView imageView_Login;
    ImageView imageView_toRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // CASTING TO XML
        final EditText editText_LoginName = findViewById(R.id.editText_LoginName);
        editText_LoginPassword = (EditText) findViewById(R.id.editText_LoginPassword);
        imageView_Login = findViewById(R.id.imageView_Login);
        imageView_toRegister = (ImageView) findViewById(R.id.imageView_toRegister);
        checkBox_RememberMe = findViewById(R.id.checkBox_RememberMe);


         // IF USER HAVE AN ALREADY ACCOUNT & WANT TO LOGIN UNTIL DATABASE INTEGRATION
        imageView_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // INPUT
                LoginPassword = editText_LoginPassword.getText().toString().trim();
                LoginName = editText_LoginName.getText().toString().trim();


                // LOGIN FIELD EMPTY CHECK

                if(TextUtils.isEmpty(editText_LoginName.getText().toString().trim()))
                {
                    Toast.makeText(Login.this, "User Name field cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(editText_LoginPassword.getText().toString().trim()))
                {
                    Toast.makeText(Login.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    open_HomePage();
                }
            }
        });


        // IF USER PRESS TO_REGISTER ARROW
        imageView_toRegister.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View v)
           {
               openActivity_Register();
           }
        });
    }

    // TO_REGISTER ARROW INTENT
    public void openActivity_Register()
    {
        Intent intent4 = new Intent(this, Register.class);
        startActivity(intent4);
    }

    // TO HOMEPAGE AFTER LOGIN
    public void open_HomePage()
    {
        Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
        // SEND LOGINNAME TO MAIN PAGE
        intent5.putExtra("LoginName", LoginName);
        startActivity(intent5);
    }
}
