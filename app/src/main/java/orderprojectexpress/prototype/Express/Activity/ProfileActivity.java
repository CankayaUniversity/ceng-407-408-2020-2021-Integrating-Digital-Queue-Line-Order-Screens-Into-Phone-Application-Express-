package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import orderprojectexpress.prototype.Express.Adapter.ProfileAdapter;
import orderprojectexpress.prototype.Express.Class.Item;

import com.prototype.Express.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileActivity extends AppCompatActivity
{
    // XML
    RecyclerView rv;
    ProgressBar progressBar;
    TextView message;
    ImageView button_basket, button_profile, button_scan;

    // VARIABLES
    String url_base = "http://104.248.207.133:5000/api/v1/orders?user=";
    String url;

    // VOLLEY
    RequestQueue requestQueue_orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        // XML
        rv = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progressBar);
        message = findViewById(R.id.message);
        button_basket = findViewById(R.id.button_basket);
        button_profile = findViewById(R.id.button_profile);
        button_scan = findViewById(R.id.button_scan);

        // XML INITIALIZE
        message.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // CLICK LISTENERS
        button_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_BasketActivity();
            }
        });

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ProfileActivity();
            }
        });

        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_QRActivity();
            }
        });

        // GET USER ID
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user_id", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        // COMPLETE URL
        url = url_base + id;

        // RETRIEVE DATA
        retrieveData();
    }

    // INTENTS
    private void open_BasketActivity()
    {
        Intent intent = new Intent(ProfileActivity.this, BasketActivity.class);
        startActivity(intent);
    }

    private void open_ProfileActivity()
    {
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void open_QRActivity()
    {
        Intent intent = new Intent(ProfileActivity.this, QRActivity.class);
        startActivity(intent);
    }

    private void retrieveData()
    {
        final ArrayList<Item> mData;
        mData = new ArrayList<>();

        // ORDER HISTORY
        requestQueue_orderHistory = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject menuItem = jsonObject.getJSONObject("menuItem");

                        Item item = new Item();

                        String photo = menuItem.getString("photo");
                        String _id = menuItem.getString("_id");
                        String name = menuItem.getString("name");
                        double price = menuItem.getDouble("price");
                        String description = menuItem.getString("description");
                        String restaurant = menuItem.getString("restaurant");
                        String type = menuItem.getString("type");
                        String createdAt = menuItem.getString("createdAt");
                        String updatedAt = menuItem.getString("updatedAt");

                        item.setPhoto(photo);
                        item.set_id(_id);
                        item.setName(name);
                        item.setPrice(price);
                        item.setDescription(description);
                        item.setRestaurant(restaurant);
                        item.setType(type);
                        item.setCreatedAt(createdAt);
                        item.setUpdatedAt(updatedAt);

                        if(item.getName() != null)
                        {
                            mData.add(item);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue_orderHistory.add(jsonObjectRequest);

        // WAIT FOR ASYNC VOLLEY TO FINISH
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                // XML INITIALIZE
                progressBar.setVisibility(View.INVISIBLE);
                message.setVisibility(View.INVISIBLE);

                Collections.reverse(mData);
                rv.setHasFixedSize(true);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(linearLayoutManager);
                ProfileAdapter profileAdapter;
                profileAdapter = new ProfileAdapter(getApplicationContext(), mData);
                rv.setAdapter(profileAdapter);
                profileAdapter.notifyDataSetChanged();
            }
        }, 5000);   // 5 second
    }
}
