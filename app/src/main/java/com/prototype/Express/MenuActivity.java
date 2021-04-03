package com.prototype.Express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity
{
    // XML VARIABLES
    TextView textView6;

    // VARIABLES
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // XML VARIABLES CASTING
        textView6 = findViewById(R.id.textView6);

        // Restaurant Key
        String key = getIntent().getStringExtra("key");
        requestQueue = Volley.newRequestQueue(this);
        jsonParse(key);
    }

    public void jsonParse(final String key)
    {
        String url_address = "http://104.248.207.133:5000/api/v1/restaurants";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_address, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject restaurant = jsonArray.getJSONObject(i);
                        final String _id = restaurant.getString("_id");

                        if(key.equals(_id))
                        {
                            JSONArray jsonArrayMenu = restaurant.getJSONArray("menus");
                            for(int j = 0; j < jsonArrayMenu.length(); j++)
                            {
                                JSONObject menu = jsonArrayMenu.getJSONObject(j);
                                String title = menu.getString("title");
                                String description = menu.getString("description");
                                textView6.setText(title);
                            }
                        }
                    }

                } catch (JSONException e) {
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
