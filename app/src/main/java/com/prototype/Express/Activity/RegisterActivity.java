package com.prototype.Express.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.stream.JsonReader;
import com.prototype.Express.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // INITIALIZING
        final EditText editText_RegisterName = (EditText) findViewById(R.id.editText_RegisterName);
        final EditText editText_RegisterEmail = (EditText) findViewById(R.id.editText_RegisterEmail);
        final EditText editText_RegisterUserName = (EditText) findViewById(R.id.editText_RegisterUserName);
        final CheckBox checkBox_Old = (CheckBox) findViewById(R.id.checkBox_Old);
        final CheckBox checkBox_TermsOfUse = (CheckBox) findViewById(R.id.checkBox_TermsOfUse);
        final ImageView imageView_Register = (ImageView) findViewById(R.id.imageView_Register);
        final EditText editText_RegisterPhone = (EditText) findViewById(R.id.editText_RegisterPhone);
        final EditText editText_RegisterPassword = (EditText) findViewById(R.id.editText_RegisterPassword);
        final Pattern EMAIL_ADRESS;
        final Pattern PASSWORD;


        // E-MAIL REGEX
        EMAIL_ADRESS = Pattern.compile(
          "[a-zA-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
          "\\@" +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
          "(" +
          "\\." +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
          ")+"
        );


        //PASSWORD REGEX
        PASSWORD = Pattern.compile(
            "^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{6,120}" +
            "$"
        );


        // INTENT TO LOGIN PAGE AFTER SUCCESSFUL REGISTER
        imageView_Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // INPUT
                String RegisterName = editText_RegisterName.getText().toString().trim();
                String RegisterEmail = editText_RegisterEmail.getText().toString().trim();
                String RegisterUserName = editText_RegisterUserName.getText().toString().trim();
                String RegisterPassword = editText_RegisterPassword.getText().toString().trim();
                String RegisterPhone = editText_RegisterPhone.getText().toString().trim();


                // CHECKING checkBox_Old, checkBox_TermsOfUse
                if(checkBox_Old.isChecked() && checkBox_TermsOfUse.isChecked())
                {
                    //CHECK REGISTER FIELDS
                    if(TextUtils.isEmpty(editText_RegisterName.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(editText_RegisterUserName.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "User Name field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if (TextUtils.isEmpty(editText_RegisterEmail.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "E-mail field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(editText_RegisterPassword.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    }


                    // NAVIGATION TO LOGIN PAGE
                    else
                    {
                        registerUser();
                        open_Login();
                    }
                }

                else
                {
                    Toast.makeText(RegisterActivity.this, "Check Boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void open_Login()
    {
        Intent intent6 = new Intent(this, LoginActivity.class);
        startActivity(intent6);
    }

    private void registerUser()
    {
        final String url = "http://104.248.207.133:5000/api/v1/auth/register";

        Map<String, String> params = new HashMap();
        params.put("name", "test7");
        params.put("password", "123456");
        params.put("email", "test7@hotmail.com");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                //TODO: handle success
                try
                {
                    String register = response.getString("success");
                    System.out.print("\n\n\n" + register + "\n\n\n");

                    String token = response.getString("token");
                    System.out.print("\n\n\n" + token + "\n\n\n");

                    Toast.makeText(RegisterActivity.this, token, Toast.LENGTH_SHORT).show();


                }catch (JSONException e){
                    System.out.print(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                System.out.print("\n\n\n\n\n");
                error.printStackTrace();
                System.out.print("\n\n\n\n\n");
                //TODO: handle failure
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
