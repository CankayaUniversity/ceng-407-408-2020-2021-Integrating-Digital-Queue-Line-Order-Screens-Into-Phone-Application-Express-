package com.prototype.Express;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.StrictMode;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;



public class MainActivity extends AppCompatActivity
{
    // GLOBAL INITIALIZE
    ImageView profile_imageView;
    ImageView QR_imageView;
    TextView textView;
    TextView textView2;
    TextView textView3;
    Button button;
    RequestQueue requestQueue;

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


        // GETS PROFILE NAME FROM LOGIN PAGE
        String profilename = getIntent().getStringExtra("LoginName");
        textView_ProfileName.setText(profilename);


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
}

