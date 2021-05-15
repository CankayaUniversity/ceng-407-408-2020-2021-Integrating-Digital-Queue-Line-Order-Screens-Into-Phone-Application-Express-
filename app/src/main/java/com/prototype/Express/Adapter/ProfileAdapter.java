package com.prototype.Express.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.prototype.Express.Class.GlobalVariables;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder>
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;


    public ProfileAdapter(Context context, ArrayList<Item> mData)
    {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);

        ProfileAdapter.MyViewHolder myViewHolder = new ProfileAdapter.MyViewHolder(view);

        return  myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.MyViewHolder holder, final int position)
    {
        holder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(holder.image);
        holder.price.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getPrice()) + "₺");

        holder.repeat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Item item = new Item();
                item.set_id(mData.get(holder.getAdapterPosition()).get_id());
                item.setPhoto(mData.get(holder.getAdapterPosition()).getPhoto());
                item.setName(mData.get(holder.getAdapterPosition()).getName());
                item.setPrice(mData.get(holder.getAdapterPosition()).getPrice());
                item.setDescription(mData.get(holder.getAdapterPosition()).getDescription());
                item.setRestaurant(mData.get(holder.getAdapterPosition()).getRestaurant());
                item.setType(mData.get(holder.getAdapterPosition()).getType());
                item.setQuantity(1);

                GlobalVariables.getInstance().encounters.add(item);
                Toast.makeText(context, "Ürün Sepetinize Eklendi!", Toast.LENGTH_SHORT).show();
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
        RatingBar ratingBar;
        ImageView repeat;
        EditText comment;
        Button send;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            image = itemView.findViewById(R.id.image);
            repeat = itemView.findViewById(R.id.repeat);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            comment = itemView.findViewById(R.id.comment);
            send = itemView.findViewById(R.id.send);
        }
    }






}
