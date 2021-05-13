package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.Express.Class.Restaurant;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity
{
    //  VARIABLES
    RequestQueue requestQueue;

    // XML VARIABLES
    TextView textView3, textView5;
    ImageView imageView2;
    Button button;

    // URL BASE
    final String URL_BASE = "http://104.248.207.133:5000/api/v1/restaurants/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        // CASTING XML VARIABLES
        textView3 = findViewById(R.id.textView3);
        imageView2 = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);
        textView5 = findViewById(R.id.textView5);

        // GET INTENT EXTRA
        String key = getIntent().getStringExtra("key");

        // REQUEST NEW VOLLEY
        requestQueue = Volley.newRequestQueue(this);
        jsonParse(key);
    }


    public void jsonParse(final String key)
    {
        final String url_restaurant = URL_BASE + key;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_restaurant, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONObject restaurant = response.getJSONObject("data");

                    // Restaurant Variables
                    String name = restaurant.getString("name");
                    String description = restaurant.getString("description");
                    String address = restaurant.getString("address");
                    String photo = restaurant.getString("photo");
                    //String website = restaurant.getString("website");
                    //String phone = restaurant.getString("phone");
                    //String email = restaurant.getString("email");
                    final String _id = restaurant.getString("_id");

                    textView5.setText(name);
                    textView3.append(description + "\n");
                    textView3.append(address);
                    Picasso.get().load(photo).into(imageView2);

                    button.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            // Show Menu of the Restaurant
                            Intent intent = new Intent(RestaurantsActivity.this, MenuActivity.class);
                            intent.putExtra("key", _id);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
