package com.prototype.Express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class Register extends AppCompatActivity
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
                        Toast.makeText(Register.this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(editText_RegisterUserName.getText().toString()))
                    {
                        Toast.makeText(Register.this, "User Name field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if (TextUtils.isEmpty(editText_RegisterEmail.getText().toString()))
                    {
                        Toast.makeText(Register.this, "E-mail field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(editText_RegisterPassword.getText().toString()))
                    {
                        Toast.makeText(Register.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    // REGISTER PROPER CHECK
                    else if(editText_RegisterName.length() <=2 || editText_RegisterName.length() >= 16)
                    {
                        Toast.makeText(Register.this, "Name should between 2 and 16 character", Toast.LENGTH_SHORT).show();
                    }

                    else if(editText_RegisterUserName.length() <=2 || editText_RegisterUserName.length() >= 16)
                    {
                        Toast.makeText(Register.this, "User Name should between 2 and 16 character", Toast.LENGTH_SHORT).show();
                    }

                    else if (editText_RegisterPhone.length() != 14)
                    {
                        Toast.makeText(Register.this, "Enter your phone number without region code", Toast.LENGTH_SHORT).show();
                    }


                    // REGEX CHECK
                    else if(!Patterns.EMAIL_ADDRESS.matcher(RegisterEmail).matches())
                    {
                        Toast.makeText(Register.this, "Enter valid E-Mail", Toast.LENGTH_SHORT).show();
                    }

                    else if (!PASSWORD.matcher(RegisterPassword).matches())
                    {
                        Toast.makeText(Register.this, "Password should contain at least one: digit, lower case, upper case, special character without blank by having 6 or more character", Toast.LENGTH_LONG).show();
                    }


                    // NAVIGATION TO LOGIN PAGE
                    else
                    {
                        open_Login();
                    }
                }

                else
                {
                    Toast.makeText(Register.this, "Check Boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void open_Login()
    {
        Intent intent6 = new Intent(this, Login.class);
        startActivity(intent6);
    }

}
