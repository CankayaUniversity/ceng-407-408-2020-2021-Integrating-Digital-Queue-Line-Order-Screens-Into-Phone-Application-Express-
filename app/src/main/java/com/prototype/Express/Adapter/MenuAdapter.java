package com.prototype.Express.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.prototype.Express.Activity.AddActivity;
import com.prototype.Express.Class.GlobalVariables;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;



public class MenuAdapter extends RecyclerView.Adapter
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;
    ImageView button_basket;

    // VIEWHOLDERS
    MyViewHolder myViewHolder;
    MyViewHolder2 myViewHolder2;

    public MenuAdapter(Context context, ArrayList<Item> mData, ImageView button_basket)
    {
        this.context = context;
        this.mData = mData;
        this.button_basket = button_basket;
    }

    // DECISION MAKER
    @Override
    public int getItemViewType(int position)
    {
        if(mData.get(position).getType().equals("separator"))
        {
            return 1;
        }

        else
        {
            return 0;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        // SEPARATOR
        if (viewType == 1)
        {
            view = LayoutInflater.from(context).inflate(R.layout.indicator, parent, false);

            return new MenuAdapter.MyViewHolder2(view);
        }

        // ITEM
        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MenuAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position)
    {

        // SEPARATOR
        if(getItemViewType(holder.getAdapterPosition()) == 1)
        {
            myViewHolder2 = (MenuAdapter.MyViewHolder2) holder;
            myViewHolder2.name.setText(mData.get(myViewHolder2.getAdapterPosition()).getName());
        }


        // ITEM
        if(getItemViewType(holder.getAdapterPosition()) == 0)
        {
            myViewHolder = (MenuAdapter.MyViewHolder) holder;

            myViewHolder.name.setText(mData.get(myViewHolder.getAdapterPosition()).getName());
            myViewHolder.description.setText(mData.get(myViewHolder.getAdapterPosition()).getDescription());
            Picasso.get().load(mData.get(myViewHolder.getAdapterPosition()).getPhoto()).into(myViewHolder.image);
            myViewHolder.price.setText(String.valueOf(mData.get(myViewHolder.getAdapterPosition()).getPrice()));

            myViewHolder.add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // INTENT
                    Intent intent = new Intent(context.getApplicationContext(), AddActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    //Bundle
                    intent.putExtra("photo", mData.get(holder.getAdapterPosition()).getPhoto());
                    intent.putExtra("name", mData.get(holder.getAdapterPosition()).getName());
                    intent.putExtra("price", mData.get(holder.getAdapterPosition()).getPrice());
                    intent.putExtra("description", mData.get(holder.getAdapterPosition()).getDescription());

                    context.getApplicationContext().startActivity(intent);
                }
            });
        }

        button_basket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, String.valueOf(GlobalVariables.getInstance().encounters.size()), Toast.LENGTH_SHORT).show();
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
        }
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder
    {
        // VARIABLES OF ITEM
        TextView name;

        public MyViewHolder2(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
        }
    }




    // FUNCTIONS
}
