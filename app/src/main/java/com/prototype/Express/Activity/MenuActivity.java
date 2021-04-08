package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
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

        // RESTAURANT KEY
        String key = getIntent().getStringExtra("key");

        jsonParse(key);
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
                        for (int i = 0; i < jsonArray.length(); i++) {
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
            requestQueue_special.add(jsonObjectRequest);


            // SINGLEITEM
            requestQueue_singleitem = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url_singleitem, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
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
                        for (int i = 0; i < jsonArray.length(); i++) {
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
                        for (int i = 0; i < jsonArray.length(); i++) {
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

    public void display(ArrayList<Item> mData)
    {
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MenuAdapter menuAdapter;

        menuAdapter = new MenuAdapter(getApplicationContext(), mData);
        recyclerView.setAdapter(menuAdapter);

        menuAdapter.notifyDataSetChanged();
    }
}
