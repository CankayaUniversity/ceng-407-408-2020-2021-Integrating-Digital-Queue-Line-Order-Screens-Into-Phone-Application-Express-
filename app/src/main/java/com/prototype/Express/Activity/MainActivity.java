package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prototype.Express.R;


public class MainActivity extends AppCompatActivity
{
    // GLOBAL INITIALIZE
    ImageView profile_imageView;
    ImageView QR_imageView;
    TextView textView;
    TextView textView2;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CASTING TO XML
        profile_imageView = (ImageView) findViewById(R.id.Account_imageView);
        QR_imageView = (ImageView) findViewById(R.id.QR_imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView_ProfileName = findViewById(R.id.textView_ProfileName);
        logout = findViewById(R.id.logout);

        // OPENS PROFILE PAGE
        profile_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                  openProfilePage();
            }
        });


        // OPENS SCANNER PAGE
        QR_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openQR_Activity();
            }
        });

        // USER DISCONNECT
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = preferences.edit();
                editor2.putBoolean("status_type", false);
                editor2.apply();

                open_LoginActivity();
            }
        });
    }


    public void openProfilePage()
    {
        Intent intent3 = new Intent(this, ProfileActivity.class);
        startActivity(intent3);
    }

    public void openQR_Activity()
    {
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    private void open_LoginActivity()
    {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

