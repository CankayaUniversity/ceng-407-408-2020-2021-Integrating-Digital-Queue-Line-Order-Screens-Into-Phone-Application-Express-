package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.Express.R;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;


public class LoginActivity extends AppCompatActivity
{
    // GLOBAL VARIABLES
    String userName;
    String userPassword;

    CheckBox checkBox_RememberMe;
    EditText editText_LoginPassword;
    ImageView imageView_Login;
    ImageView imageView_toRegister;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        // CASTING TO XML
        final EditText editText_LoginName = findViewById(R.id.editText_LoginName);
        editText_LoginPassword = (EditText) findViewById(R.id.editText_LoginPassword);
        imageView_Login = findViewById(R.id.imageView_Login);
        checkBox_RememberMe = findViewById(R.id.checkBox_RememberMe);
        textView4 = findViewById(R.id.textView4);


         // IF USER HAVE AN ALREADY ACCOUNT & WANT TO LOGIN UNTIL DATABASE INTEGRATION
        imageView_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // INPUT
                userPassword = editText_LoginPassword.getText().toString().trim();
                userName = editText_LoginName.getText().toString().trim();


                // LOGIN FIELD EMPTY CHECK
                if(TextUtils.isEmpty(editText_LoginName.getText().toString().trim()))
                {
                    Toast.makeText(LoginActivity.this, "User Name field cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(editText_LoginPassword.getText().toString().trim()))
                {
                    Toast.makeText(LoginActivity.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    loginUser();
                    open_HomePage();
                }
            }
        });


        // IF USER PRESS TO_REGISTER ARROW
        textView4.setOnClickListener(new View.OnClickListener()
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
        Intent intent4 = new Intent(this, RegisterActivity.class);
        startActivity(intent4);
    }

    // TO HOMEPAGE AFTER LOGIN
    public void open_HomePage()
    {
        Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
        // SEND LOGIN NAME TO MAIN PAGE
        intent5.putExtra("userName", userName);
        startActivity(intent5);
    }

    private void loginUser()
    {

    }
}
