package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.prototype.Express.Adapter.MenuAdapter;
import com.prototype.Express.Adapter.ProfileAdapter;
import com.prototype.Express.R;

public class ProfileActivity extends AppCompatActivity
{
    // XML
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        /*

        // XML
        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);
        ProfileAdapter profileAdapter;
        profileAdapter = new ProfileAdapter(getApplicationContext(), mData);
        rv.setAdapter(profileAdapter);

        profileAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user_id", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");


         */

    }
}
