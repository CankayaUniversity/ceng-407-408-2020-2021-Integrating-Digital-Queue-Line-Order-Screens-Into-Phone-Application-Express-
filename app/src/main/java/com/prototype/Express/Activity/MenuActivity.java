package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.Express.Adapter.MenuAdapter;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity
{
    // XML VARIABLES
    RecyclerView recyclerView;
    ImageView button_profile, button_scan, button_basket;
    TextView itemcounter;

    // VOLLEY
    RequestQueue requestQueue_special;
    RequestQueue requestQueue_singleitem;
    RequestQueue requestQueue_menuitem;
    RequestQueue requestQueue_drinks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // XML INITIALIZING
        recyclerView = findViewById(R.id.recylerview);
        button_profile = findViewById(R.id.button_profile);
        button_scan = findViewById(R.id.button_scan);
        button_basket = findViewById(R.id.button_basket);
        itemcounter = findViewById(R.id.itemcounter);

        // RESTAURANT KEY
        String key = getIntent().getStringExtra("key");

        // PARSE
        jsonParse(key);

        // BUTTON LISTENERS
        button_profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                open_ProfileActivity();
            }
        });

        button_scan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                open_QRActivity();
            }
        });
    }

    public void jsonParse(String key)
    {
        // URL ADDRESSES
        final String url_special = "http://104.248.207.133:5000/api/v1/restaurants/" + key + "/menus?type=special";
        final String url_singleitem = "http://104.248.207.133:5000/api/v1/restaurants/" + key + "/menuitems?type=singleitem";
        final String url_menuitem = "http://104.248.207.133:5000/api/v1/restaurants/" + key + "/menuitems?type=menuitem";
        final String url_drinks = "http://104.248.207.133:5000/api/v1/restaurants/" + key + "/menuitems?type=drink";

        // TOTAL DATA SET
        final ArrayList<Item> mData;
        mData = new ArrayList<>();


        if(true)
        {
            // SPECIAL
            requestQueue_special = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_special, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            if(i == 0)
                            {
                                Item indicator_special = new Item();
                                indicator_special.setType("separator");
                                indicator_special.setName("ÖZEL MENÜLER");
                                mData.add(indicator_special);
                            }

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Item item = new Item();

                            String photo = jsonObject.getString("photo");
                            String _id = jsonObject.getString("_id");
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String restaurant = jsonObject.getString("restaurant");
                            //int price = jsonObject.getInt("price");
                            String type = jsonObject.getString("type");
                            String createdAt = jsonObject.getString("createdAt");
                            String updatedAt = jsonObject.getString("updatedAt");
                            int __v = jsonObject.getInt("__v");

                            item.setPhoto(photo);
                            item.set_id(_id);
                            item.setName(name);
                            item.setDescription(description);
                            item.setRestaurant(restaurant);
                            //item.setPrice(price);
                            item.setType(type);
                            item.setCreatedAt(createdAt);
                            item.setUpdatedAt(updatedAt);
                            item.set__v(__v);

                            mData.add(item);

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
            requestQueue_special.add(jsonObjectRequest);


            // SINGLEITEM
            requestQueue_singleitem = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url_singleitem, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            if(i == 0)
                            {
                                Item indicator_singleitem = new Item();
                                indicator_singleitem.setType("separator");
                                indicator_singleitem.setName("ÜRÜNLER");
                                mData.add(indicator_singleitem);
                            }

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Item item = new Item();

                            String photo = jsonObject.getString("photo");
                            String _id = jsonObject.getString("_id");
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String restaurant = jsonObject.getString("restaurant");
                            int price = jsonObject.getInt("price");
                            String type = jsonObject.getString("type");
                            String createdAt = jsonObject.getString("createdAt");
                            String updatedAt = jsonObject.getString("updatedAt");
                            int __v = jsonObject.getInt("__v");

                            item.setPhoto(photo);
                            item.set_id(_id);
                            item.setName(name);
                            item.setDescription(description);
                            item.setRestaurant(restaurant);
                            item.setPrice(price);
                            item.setType(type);
                            item.setCreatedAt(createdAt);
                            item.setUpdatedAt(updatedAt);
                            item.set__v(__v);

                            mData.add(item);
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
            requestQueue_singleitem.add(jsonObjectRequest2);


            // MENUITEM
            requestQueue_menuitem = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, url_menuitem, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            if(i == 0)
                            {
                                Item indicator_menuitem = new Item();
                                indicator_menuitem.setType("separator");
                                indicator_menuitem.setName("MENÜLER");
                                mData.add(indicator_menuitem);
                            }

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Item item = new Item();

                            String photo = jsonObject.getString("photo");
                            String _id = jsonObject.getString("_id");
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String restaurant = jsonObject.getString("restaurant");
                            int price = jsonObject.getInt("price");
                            String type = jsonObject.getString("type");
                            String createdAt = jsonObject.getString("createdAt");
                            String updatedAt = jsonObject.getString("updatedAt");
                            int __v = jsonObject.getInt("__v");

                            item.setPhoto(photo);
                            item.set_id(_id);
                            item.setName(name);
                            item.setDescription(description);
                            item.setRestaurant(restaurant);
                            item.setPrice(price);
                            item.setType(type);
                            item.setCreatedAt(createdAt);
                            item.setUpdatedAt(updatedAt);
                            item.set__v(__v);

                            mData.add(item);
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
            requestQueue_menuitem.add(jsonObjectRequest3);



            // DRINKS
            requestQueue_drinks = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.GET, url_drinks, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            if(i == 0)
                            {
                                Item indicator_drinks = new Item();
                                indicator_drinks.setType("separator");
                                indicator_drinks.setName("İÇECEKLER");
                                mData.add(indicator_drinks);
                            }

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Item item = new Item();

                            String photo = jsonObject.getString("photo");
                            String _id = jsonObject.getString("_id");
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String restaurant = jsonObject.getString("restaurant");
                            int price = jsonObject.getInt("price");
                            String type = jsonObject.getString("type");
                            String createdAt = jsonObject.getString("createdAt");
                            String updatedAt = jsonObject.getString("updatedAt");
                            int __v = jsonObject.getInt("__v");

                            item.setPhoto(photo);
                            item.set_id(_id);
                            item.setName(name);
                            item.setDescription(description);
                            item.setRestaurant(restaurant);
                            item.setPrice(price);
                            item.setType(type);
                            item.setCreatedAt(createdAt);
                            item.setUpdatedAt(updatedAt);
                            item.set__v(__v);

                            mData.add(item);
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
            requestQueue_drinks.add(jsonObjectRequest4);
        }

        // WAIT FOR ASYNC VOLLEY TO FINISH
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                display(mData);
            }
        }, 5000);   // 1 second
    }


    // FUNCTIONS

    public void display(ArrayList<Item> mData)
    {
        // BASKET LIST
        ArrayList<Item> mBasket;
        mBasket = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MenuAdapter menuAdapter;

        menuAdapter = new MenuAdapter(getApplicationContext(), mData, mBasket, button_basket, itemcounter);
        recyclerView.setAdapter(menuAdapter);

        menuAdapter.notifyDataSetChanged();
    }

    public void open_QRActivity()
    {
        Intent intent = new Intent(MenuActivity.this, QRActivity.class);
        startActivity(intent);
    }

    public void open_ProfileActivity()
    {

        Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
