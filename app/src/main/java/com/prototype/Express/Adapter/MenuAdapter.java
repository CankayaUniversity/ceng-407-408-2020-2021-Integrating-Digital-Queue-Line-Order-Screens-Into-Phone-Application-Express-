package com.prototype.Express.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;

    public MenuAdapter(Context context, ArrayList<Item> mData)
    {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.MyViewHolder holder, int position)
    {
        holder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        holder.description.setText(mData.get(holder.getAdapterPosition()).getDescription());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(holder.image);
        holder.price.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getPrice()));

        holder.descriptionLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = mData.get(holder.getAdapterPosition()).getName();
                userClick(name, v);
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        // VARIABLES OF ITEM
        TextView name;
        TextView price;
        ConstraintLayout descriptionLayout;
        ImageView image;
        TextView description;
        ImageView basket;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            basket = itemView.findViewById(R.id.basket);
        }
    }

    public void userClick(String name, final View v)
    {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }
}
