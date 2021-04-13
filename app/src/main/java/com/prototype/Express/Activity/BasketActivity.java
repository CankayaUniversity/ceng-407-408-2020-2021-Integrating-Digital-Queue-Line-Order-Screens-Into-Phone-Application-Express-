package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.prototype.Express.Adapter.BasketAdapter;
import com.prototype.Express.Adapter.MenuAdapter;
import com.prototype.Express.Class.GlobalVariables;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity
{
    ArrayList<Item> mData;

    // XML VARIABLES
    RecyclerView rw;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        // XML INITIALIZING
        rw = findViewById(R.id.rw);

        rw.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rw.setLayoutManager(linearLayoutManager);

        BasketAdapter basketAdapter;
        mData = new ArrayList<>();
        mData.addAll(GlobalVariables.getInstance().encounters);

        basketAdapter = new BasketAdapter(getApplicationContext(), mData);
        rw.setAdapter(basketAdapter);

        basketAdapter.notifyDataSetChanged();

    }
}
