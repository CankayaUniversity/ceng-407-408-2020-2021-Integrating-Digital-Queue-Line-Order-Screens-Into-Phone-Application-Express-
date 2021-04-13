package com.prototype.Express.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.prototype.Express.Activity.AddActivity;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;



public class BasketAdapter extends RecyclerView.Adapter
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;

    // VIEWHOLDERS
    MyViewHolder myViewHolder;

    public BasketAdapter(Context context, ArrayList<Item> mData)
    {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new BasketAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position)
    {
        myViewHolder = (BasketAdapter.MyViewHolder) holder;

        myViewHolder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(myViewHolder.image);
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
        ImageView add;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            add = itemView.findViewById(R.id.add);

            add.setVisibility(View.INVISIBLE);
        }
    }
}
