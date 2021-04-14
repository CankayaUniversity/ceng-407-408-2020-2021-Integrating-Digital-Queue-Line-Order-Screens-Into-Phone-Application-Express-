package com.prototype.Express.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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



public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.MyViewHolder>
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;
    Button button_approve;
    int price_total;
    int item_quantity;

    // VIEWHOLDERS
    MyViewHolder myViewHolder;

    public BasketAdapter(Context context, ArrayList<Item> mData, Button button_approve)
    {
        this.context = context;
        this.mData = mData;
        this.button_approve = button_approve;
        button_approve.setVisibility(View.INVISIBLE);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.basket_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return  myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position)
    {
        holder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(holder.image);
        holder.description.setText(mData.get(holder.getAdapterPosition()).getDescription());
        holder.price.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getPrice() * mData.get(holder.getAdapterPosition()).getQuantity()) + "₺");
        holder.quantity.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getQuantity()));
        item_quantity = mData.get(holder.getAdapterPosition()).getQuantity();

        price_total = price_total + (mData.get(holder.getAdapterPosition()).getPrice() * mData.get(holder.getAdapterPosition()).getQuantity());

        if(price_total != 0)
        {
            button_approve.setVisibility(View.VISIBLE);
            button_approve.setText("SİPARİŞİ ONAYLA: " + price_total + "₺");
        }
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
        TextView description, quantity;
        ImageView remove;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            quantity = itemView.findViewById(R.id.quantity);
            remove = itemView.findViewById(R.id.remove);
        }
    }
}
